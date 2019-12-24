package com.gantch.nbiotdevicesmanagement.service.impl;

import com.gantch.nbiotdevicesmanagement.model.DeviceCredentials;
import com.gantch.nbiotdevicesmanagement.service.DeviceCredentialsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-24-14:23
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.service.impl
 **/
@Service
public class DeviceCredentialsServiceImpl implements DeviceCredentialsService {
    @Override
    public DeviceCredentials findDeviceCredentialsByDeviceId(UUID deviceId) {
        return null;
    }

    @Override
    public DeviceCredentials updateDeviceCredentials(DeviceCredentials deviceCredentials) {
        return null;
    }

    @Override
    public DeviceCredentials createDeviceCredentials(DeviceCredentials deviceCredentials) {
        return null;
    }

    @Override
    public void deleteDeviceCredentials(DeviceCredentials deviceCredentials) {

    }

    @Override
    public DeviceCredentials findDeviceCredentialsByToken(String token) {
        return null;
    }
}
