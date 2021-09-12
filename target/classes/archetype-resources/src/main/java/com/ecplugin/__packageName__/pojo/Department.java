#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.ecplugin.${packageName}.pojo;

import lombok.Data;

@Data
public class Department {
    private int id;
    private String departmentmark;
    private String departmentname;
}
