package com.gantch.nbiotdevicesmanagement.dao.device;

import com.gantch.nbiotdevicesmanagement.dao.page.TextPageLink;
import com.gantch.nbiotdevicesmanagement.model.Group;

import java.util.List;
import java.util.Optional;

/**
 * @author lcw332
 * Date 2019-12-24-15:18
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao.device
 *
 **/
public interface GroupDao {
    /**
     * Save or update group object
     *
     * @param group the group object
     * @return saved group object
     */
    Group save(Group group);

    /**
     *  通过租户Id找到设备组
     * @param tenantId the tenant id
     * @param pageLink the page link
     * @return the list of group objects
     */
    List<Group> findGroupsByTenantId(Integer tenantId, TextPageLink pageLink);

    List<Group> findGroupsByTenantIdAndCustomerId(Integer tenantId, Integer customerId, TextPageLink pageLink) ;

    List<Group> findGroupsByCustomerId(Integer customerId, TextPageLink pageLink);

    Optional<Group> findGroupByTenantAndCustomerIdAndName(Integer tenantId, Integer customerId, String name);
}
