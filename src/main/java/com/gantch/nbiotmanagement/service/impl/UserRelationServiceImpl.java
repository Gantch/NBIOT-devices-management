package com.gantch.nbiotmanagement.service.impl;

import com.gantch.nbiotmanagement.dto.UserMemberParam;
import com.gantch.nbiotmanagement.mapper.UserMemberMapper;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import com.gantch.nbiotmanagement.pojo.UserDeviceRelation;
import com.gantch.nbiotmanagement.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-31-10:35
 * Description:  nbiot-management , com.gantch.nbiotmanagement.service.impl
 **/
@Service
public class UserRelationServiceImpl implements UserRelationService {

    @Autowired
    private UserMemberMapper memberMapper;

    @Override
    public List<DeviceRelation> getShareDeviceGroup(UserMemberParam param) {
        List<UserDeviceRelation> list=memberMapper.selectUserDeviceRelationByBinded(param.getCustomerId());
        System.out.println(list.toString());

        return null;
    }
}
