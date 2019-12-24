package com.gantch.nbiotdevicesmanagement.service;

import com.gantch.nbiotdevicesmanagement.dao.page.TextPageData;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageLink;
import com.gantch.nbiotdevicesmanagement.model.Device;

import java.util.Optional;

/**
 * @author lcw332
 * Date 2019-12-21-15:50
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.service
 **/
public interface DeviceService {

    Device saveDevice(Device device);

    TextPageData<Device> findDevicesByTenantIdAndCustomerId(Integer tenantId, Integer customerId, TextPageLink pageLink);

    TextPageData<Device> findDevicesByTenantId(Integer tenantId, TextPageLink pageLink);

    Optional<Device> findDeviceByTenantIdAndName(Integer tenantId,String name);
}
