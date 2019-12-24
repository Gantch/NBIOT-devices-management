package com.gantch.nbiotdevicesmanagement.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gantch.nbiotdevicesmanagement.common.CommonResult;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageData;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageLink;
import com.gantch.nbiotdevicesmanagement.model.Device;
import com.gantch.nbiotdevicesmanagement.model.Group;
import com.gantch.nbiotdevicesmanagement.service.DeviceService;
import com.gantch.nbiotdevicesmanagement.service.GroupService;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author lcw332
 * Date 2019-12-20-13:11
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.controller
 * 设备管理接口
 **/
@Controller
@Api(tags = "NBIoTDeviceController",description = "设备管理接口")
@RequestMapping("/nbiot/device")
public class NBIoTDeviceController extends BaseController{

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private GroupService groupService;

    @ApiOperation(value = "新建设备")
    @RequestMapping(value = "/createDevice",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createDevice(@RequestBody String device){
        //将提交表单的形式转为json格式提交
        try{
            Device device1 = JSONObject.parseObject(device,Device.class);
            Device sendDevice =checkNotNull(deviceService.saveDevice(device1));
            return CommonResult.success(sendDevice.toString());
        }catch (Exception e){
            return CommonResult.failed(e.toString());
        }
    }

    @ApiOperation(value = "分配设备到设备组")
    @RequestMapping(value ="/group/assignDevice2Group/",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult assignDevice2Group(){
        //把某一设备分配到设备组
        try{
            return CommonResult.success("");
        }catch (Exception e) {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "新建设备组")
    @RequestMapping(value = "/group/createDeviceGroup")
    @ResponseBody
    public CommonResult createDeviceGroup(@RequestBody String group){
        //创建设备组
        try{
            //将提交表单的形式转为json格式提交
            Group group1 = JSON.parseObject(group,Group.class);
            Group savedGroup =checkNotNull(groupService.saveGroup(group1));
            return CommonResult.success(savedGroup.toString());
        }catch (Exception e) {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "将某个设备分配到设备组")
    @RequestMapping(value ="/group/assignDeviceGroup",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult assignDeviceGroup(){

        return CommonResult.failed();
    }


    @ApiOperation(value = "将某个设备从设备组里删除")
    @RequestMapping(value ="/group/unassignDevice",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult unassignDevice(){

        return CommonResult.failed();
    }


    @ApiOperation(value = "获取租户下所有的设备")
    @RequestMapping(value = "/getTenantAllDevice/{tenantId}",params = {"limit"},method = RequestMethod.GET)
    public CommonResult getTenantAllDevice(@PathVariable("tenantId")Integer tenantId,
                                           @RequestParam int limit,
                                           @RequestParam(required = false) String type,
                                           @RequestParam(required = false) String textSearch,
                                           @RequestParam(required = false) String idOffset,
                                           @RequestParam(required = false) String textOffset) throws Exception {
        try{
            TextPageLink pageLink = new TextPageLink(limit, textSearch, idOffset==null?null:toUUID(idOffset), textOffset);
            TextPageData deviceResult = deviceService.findDevicesByTenantId(tenantId,pageLink);
            return CommonResult.success(deviceResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取租户下所有的设备组")
    @RequestMapping(value = "/group/getTenantAllDeviceGroup/{tenantId}",params = {"limit"},method = RequestMethod.GET)
    public CommonResult getTenantAllDeviceGroup(@PathVariable("tenantId")Integer tenantId,
                                                @RequestParam int limit,
                                                @RequestParam(required = false) String type,
                                                @RequestParam(required = false) String textSearch,
                                                @RequestParam(required = false) String idOffset,
                                                @RequestParam(required = false) String textOffset) throws Exception{
        try{
            TextPageLink pageLink = new TextPageLink(limit, textSearch, idOffset==null?null:toUUID(idOffset), textOffset);
            TextPageData tenantGroups = groupService.findGroupsByTenantId(tenantId,pageLink);
            return CommonResult.success(tenantGroups);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CommonResult.failed();
    }

}
