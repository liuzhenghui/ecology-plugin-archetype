#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.ecplugin.${packageName}.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Page", description = "分页信息")
public class Page<T> {

    @ApiModelProperty(value = "页码")
    Integer pageNum;
    @ApiModelProperty(value = "每页数量")
    Integer pageSize;
    @ApiModelProperty(value = "总数")
    Long total;
    List <T> list;
}
