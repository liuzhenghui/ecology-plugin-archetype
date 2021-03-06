#!/bin/bash

# ------------ 流水线上配置以下脚本 ------------
# # 部署解压文件等操作目录，需要与下载路径一致
# DEPLOY_HOME=/home/yunxiao/${packageName}
# mkdir -p ${DEPLOY_HOME}
# tar zxvf ${DEPLOY_HOME}/package.tgz -C ${DEPLOY_HOME}/
# sh ${DEPLOY_HOME}/deploy_47_8081.sh restart ${DEPLOY_HOME}
# --------------------------------------------

# ============ ecology 相关配置项  ============

DEPLOY_HOME=$2                                                        # 部署解压文件等操作目录
ECOLOGY_PORT=8081                                                     # 应用端口
ECOLOGY_HOME=/data/weaver/ecology                                     # ecology 目录
JDK_HOME=/data/weaver/jdk1.8.0_151                                    # jdk 目录
MIDDLEWARE_HOME=/data/weaver/TongWeb7.0.4.1                           # 中间件 目录
START_CMD="cd ${MIDDLEWARE_HOME}/bin && nohup sh startservernohup.sh" # 启动命令
PACKAGE_NAME=_ecology-${packageName}-maven-1.0.0                      # 包名(不含后缀)
START_TIMEOUT=360                                                     # 启动超时时间(秒)
HEALTH_CHECK_URL=http://127.0.0.1:${ECOLOGY_PORT}/api/ecode/sync      # 应用健康检查URL

# ===========================================

source ${DEPLOY_HOME}/deploy.sh
