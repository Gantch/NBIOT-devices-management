package com.gantch.nbiotdevicesmanagement.service;

import com.gantch.nbiotdevicesmanagement.dao.page.TextPageData;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageLink;
import com.gantch.nbiotdevicesmanagement.model.Group;

import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-24-14:55
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.service
 **/
public interface GroupService {
    Group saveGroup(Group group);


    /**
     * 查找tenant下所有的设备组信息
     * @param tenantId
     * @param pageLink
     * @return
     */
    TextPageData<Group> findGroupsByTenantId(Integer tenantId, TextPageLink pageLink);

    void deleteGroup(UUID groupId);

    TextPageData<Group> findGroupsByCustomerId(Integer customerId, TextPageLink pageLink);

    TextPageData<Group> findGroupsByTenantIdAndCustomerId(Integer tenantId, Integer customerId, TextPageLink pageLink);
}
