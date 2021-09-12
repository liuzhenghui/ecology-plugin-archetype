package com.ecplugin.mingmei.dto;

import com.ecplugin.mingmei.pojo.Department;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User", description = "用户信息")
public class UserDto {

    @ApiModelProperty(value = "id", required = true)
    private int uid;
    @ApiModelProperty(value = "登录名")
    String loginid = "";
    @ApiModelProperty(value = "名字")
    private String lastname;
    @ApiModelProperty(value = "编号")
    private String workcode;
    @ApiModelProperty(value = "所属部门")
    private Department department;
}
