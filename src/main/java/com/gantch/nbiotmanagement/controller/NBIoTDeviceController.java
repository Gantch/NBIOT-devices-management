package com.gantch.nbiotmanagement.controller;

import com.gantch.nbiotmanagement.common.CommonPage;
import com.gantch.nbiotmanagement.common.CommonResult;
import com.gantch.nbiotmanagement.dto.*;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import com.gantch.nbiotmanagement.pojo.Group;
import com.gantch.nbiotmanagement.service.DeviceGroupService;
import com.gantch.nbiotmanagement.service.DeviceMessageService;
import com.gantch.nbiotmanagement.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcw332
 * Date 2019-12-26-11:41
 * Description:  nbiot-management , com.gantch.nbiotmanagement.controller
 * 设备管理接口
 **/
@RestController
@Api(tags = "NBIoTDeviceController",description = "设备管理接口")
@RequestMapping("/api/v1/nbiot/device")
public class NBIoTDeviceController extends BaseController{

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceGroupService groupService;

    @Autowired
    private DeviceMessageService messageService;


    @ApiOperation(value = "新建设备")
    @RequestMapping(value = "/createDevice",method = RequestMethod.POST)
    public CommonResult createDevice(@RequestBody DeviceCreateParam createParam){
            if (deviceService.createDeviceByMac(createParam)!=null){
                 return CommonResult.success(deviceService.createDeviceByMac(createParam));
            }
        return CommonResult.failed("Repeatedly add or fail to find device MAC");
    }

    @ApiOperation(value = "根据deviceId删除设备、设备与设备组关系")
    @RequestMapping(value = "/deleteDevice/{deviceId}",method = RequestMethod.DELETE)
    public CommonResult deleteDevice(@PathVariable String deviceId){
        Integer count = deviceService.deleteDevice(deviceId);
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("count", count);
        return CommonResult.success(countMap);
    }

    @ApiOperation(value = "设备组主人分享设备组给他人")
    @RequestMapping(value = "/shareDeviceGroup",method = RequestMethod.POST)
    public CommonResult shareDeviceGroup(@RequestBody UserMemberParam param){
        return CommonResult.failed();
    }


    @ApiOperation(value = "获取被分享的设备组")
    @RequestMapping(value = "/getShareDeviceGroup",method = RequestMethod.POST)
    public CommonResult getShareDeviceGroup(@RequestBody DeviceCreateParam createParam){
        return CommonResult.failed();
    }


    @ApiOperation(value = "根据groupId删除设备组与设备")
    @RequestMapping(value = "/deleteDeviceGroup/{groupId}",method = RequestMethod.DELETE)
    public CommonResult deleteDeviceGroup(@PathVariable String groupId){
        Integer count = groupService.deleteDeviceGroup(groupId);
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("count", count);
        return CommonResult.success(countMap);
    }

    @ApiOperation(value = "新建设备组")
    @RequestMapping(value = "/group/createDeviceGroup",method = RequestMethod.POST)
    public CommonResult createDeviceGroup(@RequestBody DeviceGroupCreateParam createParam){
        try{
            Integer group=groupService.createDeviceGroup(createParam);
            if (group==null){
                return CommonResult.failed();
            }
            return CommonResult.success(null);
        }catch (Exception e){
            return CommonResult.failed(e.toString());
        }
    }

    @ApiOperation(value = "分配设备到设备组")
    @RequestMapping(value = "/group/assignDeviceToGroup",method = RequestMethod.POST)
    public CommonResult updateDeviceRelation(@RequestBody DeviceGroupRelationParam updateParam){
            if (groupService.createDeviceGroupRelation(updateParam)!=null){
                return CommonResult.success("Update successful");
            }
            return CommonResult.failed("Repeat to add");
    }

    @ApiOperation(value = "修改设备昵称")
    @RequestMapping(value = "/modifyDeviceNickName",method = RequestMethod.POST)
    public CommonResult updateDeviceNickName(@RequestBody DeviceUpdateParam param){
            if (deviceService.updateDeviceNickName(param)!=null){
                return CommonResult.success("Update successful, Please refresh");
            }
        return CommonResult.failed();
    }

    @ApiOperation(value = "添加报警电话")
    @RequestMapping(value = "/addAlarmPhoneNumber",method = RequestMethod.POST)
    public CommonResult addAlarmPhoneNumber(@RequestBody DeviceMessageParam param){
            Integer count = messageService.insertDeviceMessage(param);
            if (count!=null ){//
                Map<String, Integer> countMap = new HashMap<>();
                countMap.put("count", count);
                return CommonResult.success(countMap,"Add PhoneNumber Success");
            }
        return CommonResult.failed("This phone number has been bound, or DeviceId was not found");
    }

    @ApiOperation(value = "更新报警推送状态")
    @RequestMapping(value = "/updateAlarmStatus",method = RequestMethod.POST)
    public CommonResult updateAlarmStatus(@RequestBody DeviceMessageUpdateParam param){
        Integer count = messageService.updateDevicePushStatus(param.getDeviceId(),param.getStatus());
        if (count != null){
            Map<String, Integer> countMap = new HashMap<>();
            countMap.put("count", count);
            return CommonResult.success(countMap,"Update Success");
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取当天报警状况")
    @RequestMapping(value = "/alarm/getTodayAlarm/{tenantId}",method = RequestMethod.GET)
    public CommonResult getTodayAlarm(@PathVariable("tenantId")Integer tenantId){
        Integer count = deviceService.getTodayDeviceAlarmCount(tenantId);
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("count", count);
        return CommonResult.success(countMap);
    }

    @ApiOperation(value = "获取昨日报警状况")
    @RequestMapping(value = "/alarm/getYesTodayAlarm/{tenantId}",method = RequestMethod.GET)
    public CommonResult getYesTodayAlarm(@PathVariable("tenantId")Integer tenantId){
        Integer count = deviceService.getYesTodayDeviceAlarmCount(tenantId);
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("count", count);
        return CommonResult.success(countMap);
    }

    @ApiOperation(value = "获取总设备数量")
    @RequestMapping(value = "/report/getDeviceCountByTenant/{tenantId}",method = RequestMethod.GET)
    public CommonResult getTenantDeviceCount(@PathVariable("tenantId")Integer tenantId){
        Integer count = deviceService.getTenantDeviceCount(tenantId);
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("count", count);
        return CommonResult.success(countMap);
    }

    @ApiOperation(value = "获取总设备类型")
    @RequestMapping(value = "/report/getDeviceTypeCount",method = RequestMethod.GET)
    public CommonResult getDeviceTypeCount(){
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取已分配设备组数量")
    @RequestMapping(value = "/report/getAssignDeviceCount/{tenantId}",method = RequestMethod.GET)
    public CommonResult getAssignDeviceCount(@PathVariable("tenantId")Integer tenantId){
        Integer count = deviceService.getAssignDeviceCount(tenantId);
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("count", count);
        return CommonResult.success(countMap);
    }

    @ApiOperation(value = "获取未分配设备组数量")
    @RequestMapping(value = "/report/getUnAssignDeviceCount/{tenantId}",method = RequestMethod.GET)
    public CommonResult getUnAssignDeviceCount(@PathVariable("tenantId")Integer tenantId){
        Integer count = deviceService.getUnAssignDeviceCount(tenantId);
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("count", count);
        return CommonResult.success(countMap);
    }

    @ApiOperation("根据租户ID获取租户下设备列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<DeviceRelation>> list(@RequestParam(value = "tenantId", required = false) Integer tenantId,
                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<DeviceRelation> relationList = deviceService.list(tenantId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(relationList));
    }

    @ApiOperation("根据租户ID获取租户下所有设备组")
    @RequestMapping(value = "/groupList",method = RequestMethod.GET)
    public CommonResult<CommonPage<Group>> groupList(@RequestParam(value = "tenantId", required = false) Integer tenantId,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<Group> groupList = groupService.list(tenantId,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(groupList));
    }

    @ApiOperation("根据用户Id获取用户下所有设备")
    @RequestMapping(value = "/customerDevices",method = RequestMethod.GET)
    public CommonResult<CommonPage<DeviceRelation>> customerDevices(@RequestParam(value = "customerId",required = false) Integer customerId,
                                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<DeviceRelation> customerDevices = deviceService.getCustomerDevices(customerId,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(customerDevices));
    }

    @ApiOperation("根据用户Id获取用户下设备组")
    @RequestMapping(value = "customerGroups",method = RequestMethod.GET)
    public CommonResult<CommonPage<Group>> customerGroups(@RequestParam(value = "customerId",required = false) Integer customerId,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<Group> customerGroups = groupService.getCustomerGroups(customerId,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(customerGroups));
    }

}
