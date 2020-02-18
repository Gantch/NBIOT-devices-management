package com.gantch.nbiotmanagement.service;

import com.gantch.nbiotmanagement.dto.UserMemberParam;
import com.gantch.nbiotmanagement.dto.UserRelationParam;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import com.gantch.nbiotmanagement.pojo.UserDeviceRelation;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-31-10:34
 * Description:  nbiot-management , com.gantch.nbiotmanagement.service
 **/

public interface UserRelationService {

    List<DeviceRelation> shareDevices(UserRelationParam param);

    UserDeviceRelation findRelationByBinderAndBinded(int binderId, int bindedId);
}
