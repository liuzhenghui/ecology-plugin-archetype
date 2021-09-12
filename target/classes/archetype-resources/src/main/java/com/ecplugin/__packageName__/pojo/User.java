#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.ecplugin.${packageName}.pojo;

import lombok.Data;

@Data
public class User {

    private int id;
    private String loginid;
    private String password;
    private String lastname;
    private String workcode;
    private Department department;
    private Integer seclevel;
}
