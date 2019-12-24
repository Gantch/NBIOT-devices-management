package com.gantch.nbiotdevicesmanagement.service.impl;

import com.gantch.nbiotdevicesmanagement.dao.device.DeviceDao;
import com.gantch.nbiotdevicesmanagement.dao.exception.DataValidationException;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageData;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageLink;
import com.gantch.nbiotdevicesmanagement.dao.util.DataValidator;
import com.gantch.nbiotdevicesmanagement.model.Device;
import com.gantch.nbiotdevicesmanagement.model.DeviceCredentials;
import com.gantch.nbiotdevicesmanagement.service.DeviceCredentialsService;
import com.gantch.nbiotdevicesmanagement.service.DeviceService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.gantch.nbiotdevicesmanagement.dao.util.Validator.*;


/**
 * @author lcw332
 * Date 2019-12-21-15:52
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.service.impl
 **/
@Service
public class DeviceServiceImpl implements DeviceService , InitializingBean {

    public static final String INCORRECT_TENANT_ID = "Incorrect tenantId ";
    public static final String INCORRECT_PAGE_LINK = "Incorrect page link ";
    public static final String INCORRECT_CUSTOMER_ID = "Incorrect customerId ";
    public static final String INCORRECT_DEVICE_ID = "Incorrect deviceId ";
    public static final String INCORRECT_GROUP_ID = "Incorrect groupId ";
    public static final String INCORRECT_MANUFACTURE = "Incorrect manufacture ";
    public static final String INCORRECT_DEVICE_TYPE = "Incorrect device type ";
    public static final String INCORRECT_MODEL = "Incorrect model ";
    public static final String INCORRECT_SITE_ID = "Incorrect siteId ";
    public static final String INCORRECT_TEXT_SEARCH = "Incorrect textSearch ";

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private DeviceCredentialsService credentialsService;



    @Override
    public Device saveDevice(Device device) {
        deviceValidator.validate(device);
        UUID deviceId = device.getId();
        Device savedDevice = deviceDao.save(device);
        if (deviceId == null){
            DeviceCredentials deviceCredentials = new DeviceCredentials();
            deviceCredentials.setDeviceId(savedDevice.getId());
            deviceCredentials.setDeviceToken(RandomStringUtils.randomAlphanumeric(20));//获取随机Token
            credentialsService.createDeviceCredentials(deviceCredentials);
        }
        return savedDevice;
    }

    @Override
    public TextPageData<Device> findDevicesByTenantIdAndCustomerId(Integer tenantId, Integer customerId, TextPageLink pageLink) {
        validateId(tenantId,INCORRECT_TENANT_ID + tenantId);
        validateId(customerId, INCORRECT_CUSTOMER_ID + customerId);
        validatePageLink(pageLink, INCORRECT_PAGE_LINK + pageLink);
        List<Device> devices = deviceDao.findDevicesByTenantIdAndCustomerId(tenantId,customerId,pageLink);
        return new TextPageData<>(devices,pageLink);
    }

    @Override
    public TextPageData<Device> findDevicesByTenantId(Integer tenantId, TextPageLink pageLink) {
        validateId(tenantId,INCORRECT_TENANT_ID + tenantId);
        validatePageLink(pageLink, INCORRECT_PAGE_LINK + pageLink);
        List<Device> devices = deviceDao.findDevicesByTenantId(tenantId,pageLink);
        return new TextPageData<>(devices,pageLink);
    }

    @Override
    public Optional<Device> findDeviceByTenantIdAndName(Integer tenantId, String name) {
        validateId(tenantId, INCORRECT_TENANT_ID + tenantId);
        Optional<Device> deviceOpt = deviceDao.findDeviceByTenantIdAndName(tenantId,name);
        if (deviceOpt.isPresent()){
            return Optional.of(deviceOpt.get());
        }
        return Optional.empty();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String textSearch = null;
        String idOffset = null;
        String textOffset = null;
        TextPageData pageData;

        Observable
                .interval(1, TimeUnit.DAYS)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        try {
                            //List<Tenant> tenants= getTenants();
                            TextPageData pageData;
//                            for (Tenant tenant: tenants){
//                                TextPageLink pageLink = new TextPageLink(1000, textSearch,idOffset==null?null:UUID.fromString(idOffset), textOffset);
//                                pageData = findDevices(tenant.getId() , pageLink);
//                                checkData(pageData);
//
//                                while(pageData.hasNext()) {
//                                    pageData = findDevices(tenant.getId() , pageData.getNextPageLink());
//                                    checkData(pageData);
//                                }
//                            }
                        }  catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }



    private DataValidator<Device> deviceValidator =
            new DataValidator<Device>() {

                @Override
                protected void validateCreate(Device device) {
                    deviceDao.findDeviceByTenantIdAndName(device.getTenantId(), device.getName()).ifPresent(
                            d -> {
                                throw new DataValidationException("Device with such name already exists!");
                            }
                    );
                }

                @Override
                protected void validateDataImpl(Device device) {
                    if (StringUtils.isEmpty(device.getName())) {
                        throw new DataValidationException("Device name should be specified!");
                    }
                    if (device.getTenantId() == null || device.getTenantId()==1) {
                        throw new DataValidationException("Device should be assigned to tenant!");
                    }
                    if (device.getCustomerId() == null) {
                        device.setCustomerId(1);
                    }
                    if (StringUtils.isEmpty(device.getDeviceType())) {
                        device.setDeviceType("default");
                    }
                    if (StringUtils.isEmpty(device.getModel())) {
                        device.setModel("default");
//                        throw new DataValidationException("Device model should be specified!");
                    }
                    if (StringUtils.isEmpty(device.getCreateTime())){
                        device.setCreateTime(new Date());
                    }
                }
            };
}
