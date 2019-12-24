package com.gantch.nbiotdevicesmanagement.service.impl;

import com.gantch.nbiotdevicesmanagement.dao.device.GroupDao;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageData;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageLink;
import com.gantch.nbiotdevicesmanagement.model.Group;
import com.gantch.nbiotdevicesmanagement.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.gantch.nbiotdevicesmanagement.dao.util.Validator.validateId;
import static com.gantch.nbiotdevicesmanagement.dao.util.Validator.validatePageLink;


import java.util.List;
import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-24-14:56
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.service.impl
 **/
@Service
public class GroupServiceImpl implements GroupService {


    @Autowired
    private GroupDao groupDao;

    @Override
    public Group saveGroup(Group group) {
        return null;
    }


    @Override
    public TextPageData<Group> findGroupsByTenantId(Integer tenantId, TextPageLink pageLink) {
        validateId(tenantId, "Incorrect tenantId " + tenantId);
        validatePageLink(pageLink, "Incorrect page link " + pageLink);
        List<Group> groups = groupDao.findGroupsByTenantId(tenantId,pageLink);
        return new TextPageData<>(groups,pageLink);
    }

    @Override
    public void deleteGroup(UUID groupId) {

    }

    @Override
    public TextPageData<Group> findGroupsByCustomerId(Integer customerId, TextPageLink pageLink) {
        return null;
    }

    @Override
    public TextPageData<Group> findGroupsByTenantIdAndCustomerId(Integer tenantId, Integer customerId, TextPageLink pageLink) {
        return null;
    }
}
