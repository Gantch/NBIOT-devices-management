package com.gantch.nbiotdevicesmanagement.dao.device;

import com.gantch.nbiotdevicesmanagement.dao.Dao;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageLink;
import com.gantch.nbiotdevicesmanagement.model.Device;

import java.util.List;
import java.util.Optional;

/**
 * @author lcw332
 * Date 2019-12-20-16:22
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao.device
 **/
public interface DeviceDao extends Dao<Device> {

    /**
     * Save or update
     * @param device
     * @return
     */
    Device save(Device device);

    /**
     * 通过租户Id和用户Id查找设备
     * @param tenantId
     * @param customerId
     * @param pageLink
     * @return
     */
    List<Device> findDevicesByTenantIdAndCustomerId(Integer tenantId, Integer customerId, TextPageLink pageLink);

    /**
     * 通过租户ID获取租户下所有设备
     * @param tenantId
     * @param pageLink
     * @return
     */
    List<Device> findDevicesByTenantId(Integer tenantId,TextPageLink pageLink);

    /**
     * 通过租户id和设备name找到设备
     * @param tenantId
     * @param name
     * @return
     */
    Optional<Device> findDeviceByTenantIdAndName(Integer tenantId,String name);

}
