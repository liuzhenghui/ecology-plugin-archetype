package com.weaver.mapper.ecplugin.mingmei;

import com.ecplugin.mingmei.pojo.User;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List <User> getUserList(@Param("lastname") String lastname, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    Long getUserCount(@Param("lastname") String lastname);

    User getUserById(Integer id);

    User getUserByLoginId(String loginId);
}
