package com.gantch.nbiotmanagement.controller;

import com.gantch.nbiotmanagement.common.CommonPage;
import com.gantch.nbiotmanagement.common.CommonResult;
import com.gantch.nbiotmanagement.dto.UserAdminLoginParam;
import com.gantch.nbiotmanagement.dto.UserAdminParam;
import com.gantch.nbiotmanagement.pojo.UserAdmin;
import com.gantch.nbiotmanagement.pojo.UserMember;
import com.gantch.nbiotmanagement.pojo.UserRole;
import com.gantch.nbiotmanagement.service.UserAdminService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcw332
 * Date 2019-12-26-11:41
 * Description:  nbiot-management , com.gantch.nbiotmanagement.controller
 * 管理员接口
 **/
@RestController
@Api(tags = "NBIoTAdminController",description = "用户模块")
@RequestMapping("/api/v1/nbiot/management/admin")
public class NBIoTAdminController {

    @Autowired
    private UserAdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @ApiOperation(value = "注册用户")
    @RequestMapping(value ="/register",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UserAdmin> register(@RequestBody UserAdminParam userAdminParam, BindingResult result){
        UserAdmin userAdmin = adminService.register(userAdminParam);
        if (userAdmin == null){
            CommonResult.failed();
        }
        return CommonResult.success(userAdmin);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UserAdminLoginParam userAdminLoginParam, BindingResult result){
        String token=adminService.login(userAdminLoginParam.getUsername(),userAdminLoginParam.getPassword());
        if (token == null){
            return CommonResult.validateFailed("Incorrect user name or password");
        }
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新Token")
    @RequestMapping(value = "/refreshToken",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("Token expired!");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }


    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal){
        String username = principal.getName();
        UserAdmin userAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", userAdmin.getUsername());
        data.put("roles", new String[]{"TEST"});
        return CommonResult.success(data);
    }

    @ApiOperation("根据user_admin的Username获取租户Id下的前台用户分页查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UserMember>> list(@RequestParam(value = "username", required = false) String username,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UserMember> adminList = adminService.list(username, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }


    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UserRole>> getRoleList(@PathVariable Long adminId) {
        List<UserRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }

    @ApiOperation("根据手机号查找并修改前台用户租户Id")
    @RequestMapping(value = "/updateUserMemberTenantId/{phone}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateUserMemberTenantId(@PathVariable String phone,@RequestBody String info){
        JsonObject jsonObject = (JsonObject)new JsonParser().parse(info);
        String username = jsonObject.get("username").getAsString();
        int count=adminService.updateUserMemberTenantId(username,phone);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据Id删除指定前台用户信息")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(@PathVariable Integer id){
        int count = adminService.delete(id);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据TenantId获取前台用户总数")
    @RequestMapping(value = "/getMemberAccountCount/{tenantId}",method = RequestMethod.GET)
    public CommonResult getMemberAccountCount(@PathVariable Integer tenantId){
        return CommonResult.failed();
    }

}
