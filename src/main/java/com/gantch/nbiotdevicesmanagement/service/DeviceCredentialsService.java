package com.gantch.nbiotdevicesmanagement.service;

import com.gantch.nbiotdevicesmanagement.model.DeviceCredentials;

import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-24-14:23
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.service
 **/
public interface DeviceCredentialsService {

    DeviceCredentials findDeviceCredentialsByDeviceId(UUID deviceId);

    DeviceCredentials updateDeviceCredentials(DeviceCredentials deviceCredentials);

    DeviceCredentials createDeviceCredentials(DeviceCredentials deviceCredentials);

    void deleteDeviceCredentials(DeviceCredentials deviceCredentials);

    DeviceCredentials findDeviceCredentialsByToken(String token);
}
