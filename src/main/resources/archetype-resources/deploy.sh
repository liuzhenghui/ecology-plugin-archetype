#!/bin/bash

PROG_NAME=$0
ACTION=$1

echo '\r---------------- 环境参数 -----------------'
echo "\r - ACTION : ${ACTION}"
echo "\r - DEPLOY_HOME : ${DEPLOY_HOME}"
echo "\r - ECOLOGY_PORT : ${ECOLOGY_PORT}"
echo "\r - ECOLOGY_HOME : ${ECOLOGY_HOME}"
echo "\r - JDK_HOME : ${JDK_HOME}"
echo "\r - MIDDLEWARE_HOME : ${MIDDLEWARE_HOME}"
echo "\r - START_CMD : ${START_CMD}"
echo "\r - PACKAGE_NAME : ${PACKAGE_NAME}"
echo "\r - START_TIMEOUT : ${START_TIMEOUT}"
echo "\r - HEALTH_CHECK_URL : ${HEALTH_CHECK_URL}"
echo '\r------------------------------------------'

# 拷贝文件
copy_files() {
  #删除
  rm -rf ${ECOLOGY_HOME}/WEB-INF/lib/${PACKAGE_NAME}-release.jar
  #解压
  unzip -o ${DEPLOY_HOME}/${PACKAGE_NAME}-package.zip -d ${ECOLOGY_HOME}/../
}

usage() {
  echo "Usage: $PROG_NAME {start|stop|restart}"
  exit 2
}

health_check() {
  exptime=0
  echo "checking ${HEALTH_CHECK_URL}"
  while true; do
    status_code=$(/usr/bin/curl -L -o /dev/null --connect-timeout 5 -s -w %{http_code} ${HEALTH_CHECK_URL})
    if [ "$?" != "0" ]; then
      echo -n -e "\rapplication not started"
    else
      echo "code is $status_code"
      if [ "$status_code" == "200" ]; then
        break
      fi
    fi
    sleep 1
    ((exptime++))

    echo -e "\rWait app to pass health check: $exptime..."

    if [ $exptime -gt ${START_TIMEOUT} ]; then
      echo 'app start failed'
      exit 1
    fi
  done
  echo "check ${HEALTH_CHECK_URL} success"
}

start_application() {
  echo "starting java process"
  eval ${START_CMD} 2>&1 &
  echo "started java process"
}

stop_application() {
  checkjavapid=$(ps -ef | grep java | grep "${MIDDLEWARE_HOME}" | grep -v grep | awk '{print$2}')

  if [[ ! $checkjavapid ]]; then
    echo -e "\rno java process"
    return
  fi

  echo "stop java process"
  times=60
  for e in $(seq 60); do
    sleep 1
    COSTTIME=$(($times - $e))
    checkjavapid=$(ps -ef | grep java | grep "${MIDDLEWARE_HOME}" | grep -v grep | awk '{print$2}')
    if [[ $checkjavapid ]]; then
      kill -9 $checkjavapid
      echo -e "\r        -- stopping java lasts $(expr $COSTTIME) seconds."
    else
      echo -e "\rjava process has exited"
      break
    fi
  done
  echo ""
}

start() {
  start_application
  health_check
}

stop() {
  stop_application
}

case "$ACTION" in
start)
  start
  ;;
stop)
  stop
  ;;
restart)
  stop
  copy_files
  start
  ;;
*)
  usage
  ;;
esac
