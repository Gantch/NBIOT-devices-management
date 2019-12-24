package com.gantch.nbiotdevicesmanagement.dao.device;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.querybuilder.Select;
import com.gantch.nbiotdevicesmanagement.dao.cassandra.CassandraAbstractSearchTextDao;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageLink;
import com.gantch.nbiotdevicesmanagement.model.Device;
import org.springframework.stereotype.Component;

import static com.datastax.driver.core.querybuilder.QueryBuilder.*;
import static com.gantch.nbiotdevicesmanagement.dao.ModelConstants.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author lcw332
 * Date 2019-12-21-16:22
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao.device
 **/
@Component
public class CassandraDeviceDao extends CassandraAbstractSearchTextDao<Device> implements DeviceDao {


    private PreparedStatement fetchStmt;
    private PreparedStatement fetchCustomerCountStmt;
    public static final String SELECT_PREFIX = "SELECT ";
    public static final String EQUALS_PARAM = " = ? ";


    @Override
    public Device save(Device device) {
        Device savedDevice = super.save(device);
        return savedDevice;
    }

    @Override
    protected Class<Device> getColumnFamilyClass() {
        return Device.class;
    }

    @Override
    protected String getColumnFamilyName() {
        return NBIOT_DEVICE_COLUMN_FAMILY_NAME;
    }

    @Override
    public List<Device> findDevicesByTenantIdAndCustomerId(Integer tenantId, Integer customerId, TextPageLink pageLink) {

        List<Device> devices = findPageWithTextSearch(DEVICE_BY_CUSTOMER_AND_SEARCH_TEXT_COLUMN_FAMILY_NAME,
                Arrays.asList(eq(DEVICE_CUSTOMER_ID_PROPERTY,customerId),
                              eq(DEVICE_TENANT_ID_PROPERTY,tenantId)),
                              pageLink);

            return devices;
    }

    @Override
    public List<Device> findDevicesByTenantId(Integer tenantId,TextPageLink pageLink) {
        List<Device> devices = findPageWithTextSearch(DEVICE_BY_TENANT_AND_SEARCH_TEXT_COLUMN_FAMILY_NAME,
                Collections.singletonList(eq(DEVICE_TENANT_ID_PROPERTY,tenantId)),pageLink);
        return devices;
    }

    @Override
    public Optional<Device> findDeviceByTenantIdAndName(Integer tenantId, String name) {
        Select select = select().from(DEVICE_BY_TENANT_AND_NAME_VIEW_NAME);
        Select.Where query = select.where();
        query.and(eq(DEVICE_TENANT_ID_PROPERTY,tenantId));
        query.and(eq(DEVICE_NAME_PROPERTY,name));
        return Optional.ofNullable(findOneByStatement(query));
    }

}
