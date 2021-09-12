#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.ecplugin.${packageName}.service;

import com.ecplugin.${packageName}.pojo.User;
import com.weaver.mapper.ecplugin.${packageName}.UserMapper;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static weaver.conn.mybatis.MyBatisFactory.sqlSessionFactory;

public class UserService {

    public static final UserService userService = new UserService();

    private UserService() {
    }

    public List <User> getUserList(String lastname, Integer offset, Integer pageSize) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.getMapper(UserMapper.class).getUserList(lastname, offset, pageSize);
        }
    }

    public Long getUserCount(String lastname) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.getMapper(UserMapper.class).getUserCount(lastname);
        }
    }

    public User getUserById(Integer id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.getMapper(UserMapper.class).getUserById(id);
        }
    }

    public User getUserByLoginId(String loginId) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.getMapper(UserMapper.class).getUserByLoginId(loginId);
        }
    }
}
