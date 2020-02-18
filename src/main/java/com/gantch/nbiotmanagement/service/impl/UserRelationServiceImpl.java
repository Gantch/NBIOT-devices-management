package com.gantch.nbiotmanagement.service.impl;

import com.gantch.nbiotmanagement.dto.UserMemberParam;
import com.gantch.nbiotmanagement.dto.UserRelationParam;
import com.gantch.nbiotmanagement.mapper.UserMemberMapper;
import com.gantch.nbiotmanagement.mapper.UserRelationMapper;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import com.gantch.nbiotmanagement.pojo.UserDeviceRelation;
import com.gantch.nbiotmanagement.pojo.UserMember;
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

    @Autowired
    private UserRelationMapper relationMapper;

    @Override
    public List<DeviceRelation> shareDevices(UserRelationParam param) {

        List<UserMember> memberList = memberMapper.selectUserMemberByPhone(param.getPhone());
        if (memberList.size() == 0){
            return null;
        }
        Integer id=memberList.get(0).getId();
//        UserDeviceRelation re =


        return null;
    }

    @Override
    public UserDeviceRelation findRelationByBinderAndBinded(int binderId, int bindedId) {

        return  null;
    }
}
