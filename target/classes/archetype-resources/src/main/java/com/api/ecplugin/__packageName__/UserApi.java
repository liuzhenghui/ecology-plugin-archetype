#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.api.ecplugin.${packageName};

import com.ecplugin.common.dto.ApiResult;
import com.ecplugin.common.exception.ValidationException;
import com.ecplugin.common.util.PropUtil;
import com.ecplugin.${packageName}.dto.Page;
import com.ecplugin.${packageName}.dto.UserDto;
import com.ecplugin.${packageName}.pojo.User;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import static com.ecplugin.${packageName}.service.UserService.userService;

@Slf4j
@Api(value = "user", tags = "用户")
@Path("/${packageName}/user")
public class UserApi {


    @ApiOperation(value = "ecologyHome", notes = "ecologyHome", httpMethod = "GET")
    @GET
    @Path("/getEcologyHome")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResult <String> getEcologyHome() {
        ApiResult <String> result = new ApiResult <>();

        try {

            result.success(PropUtil.getPropValue("${packageName}", "ecology.home"));

        } catch (ValidationException e) {
            // 入参异常
            log.warn(e.getMessage());
            result.fail(e.getMessage());
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            log.error(sw.toString());
            result.fail(e.getMessage());
        }

        return result;
    }

    @ApiOperation(value = "用户信息", notes = "用户信息", httpMethod = "GET")
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResult <UserDto> get(
            @ApiParam(value = "用户ID", required = true) @QueryParam("userId") Integer userId
    ) {
        ApiResult <UserDto> result = new ApiResult <>();
        log.info("查询用户信息,userId[{}]", userId);

        try {

            if (userId == null) {
                throw new ValidationException("[userId]不能为空");
            }

            User user = userService.getUserById(userId);
            ModelMapper modelMapper = new ModelMapper();
            result.success(modelMapper.map(user, UserDto.class));

        } catch (ValidationException e) {
            // 入参异常
            log.warn(e.getMessage());
            result.fail(e.getMessage());
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            log.error(sw.toString());
            result.fail(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "用户列表", notes = "用户列表", httpMethod = "GET")
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResult <Page <UserDto>> list(
            @ApiParam(value = "姓名") @QueryParam("name") String name,
            @ApiParam(value = "当前页", required = true) @QueryParam("pageNum") Integer pageNum,
            @ApiParam(value = "每页数量", required = true) @QueryParam("pageSize") Integer pageSize
    ) {
        ApiResult <Page <UserDto>> result = new ApiResult <>();
        log.info("用户列表");
        try {


            List <User> list = userService.getUserList(name, (pageNum - 1) * pageSize, pageSize);
            Long total = userService.getUserCount(name);

            ModelMapper modelMapper = new ModelMapper();
            List <UserDto> listDto = modelMapper.map(list, new TypeToken <List <UserDto>>() {
            }.getType());
            Page <UserDto> page = new Page <>();
            page.setTotal(total);
            page.setList(listDto);
            result.success(page);

        } catch (ValidationException e) {
            // 入参异常
            log.warn(e.getMessage());
            result.fail(e.getMessage());
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            log.error(sw.toString());
            result.fail(e.getMessage());
        }
        return result;
    }
}
