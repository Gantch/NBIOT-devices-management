package com.gantch.nbiotdevicesmanagement.dao;


import com.datastax.driver.core.utils.UUIDs;

import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-20-15:57
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao
 * Cassandra 常量类
 **/
public class ModelConstants {

    public static final UUID NULL_UUID = UUIDs.startOf(0);

    /**
     * Generic Constants
     */
    public static final String ID_PROPERTY = "id";
    public static final String TENANT_ID_PROPERTY = "tenant_id";
    public static final String CUSTOMER_ID_PROPERTY = "customer_id";
    public static final String SEARCH_TEXT_PROPERTY = "search_text";

    /**
     * Cassandra Device Constants
     */
    public static final String NBIOT_DEVICE_COLUMN_FAMILY_NAME ="nbiot_devcie";
    public static final String DEVICE_ID_PROPERTY= ID_PROPERTY;
    public static final String DEVICE_TENANT_ID_PROPERTY = TENANT_ID_PROPERTY;
    public static final String DEVICE_CUSTOMER_ID_PROPERTY = CUSTOMER_ID_PROPERTY;
    public static final String DEVICE_GROUP_ID_PROPERTY = "device_group_id";
    public static final String DEVICE_MAC_PROPERTY = "mac";
    public static final String DEVICE_NAME_PROPERTY = "name";
    public static final String DEVICE_MODEL_PROPERTY = "model";
    public static final String DEVICE_DEVICE_TYPE_PROPERTY = "device_type";
    public static final String DEVICE_NICKNAME_PROPERTY = "nickname";
    public static final String DEVICE_LOCATION_PROPERTY = "location";
    public static final String DEVICE_LATITUDE_PROPERTY = "latitude";
    public static final String DEVICE_LONGITUDE_PROPERTY = "longitude";
    public static final String DEVICE_DISTRICT_PROPERTY = "district";
    public static final String DEVICE_CREATE_TIME_PROPERTY = "create_time";


    public static final String DEVICE_BY_CUSTOMER_AND_SEARCH_TEXT_COLUMN_FAMILY_NAME = "device_by_customer_and_search_text";
    public static final String DEVICE_BY_TENANT_AND_SEARCH_TEXT_COLUMN_FAMILY_NAME = "device_by_tenant_and_search_text";
    public static final String DEVICE_BY_TENANT_AND_NAME_VIEW_NAME="device_by_tenant_and_name";

    /**
     * Cassandra device_by_group_id
     */
    public static final String DEVICE_BY_GROUP_ID_COLUMN_FAMILY_NAME="device_by_group_id";
    public static final String GROUP_GROUP_ID_PROPERTY="group_id";
    public static final String GROUP_DEVICE_ID_PROPERTY="device_id";

    /**
     * Cassandra device_credentials constants
     */

    public static final String DEVICE_CREDENTIALS_COLUMN_FAMILY_NAME = "device_credentials";
    public static final String DEVICE_CREDENTIALS_DEVICE_ID_PROPERTY = DEVICE_ID_PROPERTY;
    public static final String DEVICE_CREDENTIALS_TOKEN_PROPERTY = "device_token";

    public static final String DEVICE_CREDENTIALS_BY_DEVICE_COLUMN_FAMILY_NAME = "device_credentials_by_device";
    public static final String DEVICE_CREDENTIALS_BY_TOKEN_COLUMN_FAMILY_NAME = "device_credentials_by_device_token";


    /**
     * Cassandra device group constants.
     */
    public static final String GROUP_COLUMN_FAMILY_NAME = "group";
    public static final String GROUP_NAME_PROPERTY = "name";
    public static final String GROUP_TENANT_ID_PROPERTY = TENANT_ID_PROPERTY;
    public static final String GROUP_CUSTOMER_ID_PROPERTY = CUSTOMER_ID_PROPERTY;

    public static final String GROUP_BY_TENANT_AND_SEARCH_TEXT_COLUMN_FAMILY_NAME = "group_by_tenant_and_search_text";
    public static final String GROUP_BY_CUSTOMER_AND_SEARCH_TEXT_COLUMN_FAMILY_NAME = "group_by_customer_and_search_text";
    public static final String GROUP_BY_TENANT_AND_CUSTOMER_AND_SEARCH_TEXT_COLUMN_FAMILY_NAME = "group_by_tenant_and_customer_and_search_text";
    public static final String GROUP_BY_CUSTOMER_AND_NAME_COLUMN_FAMILY_NAME = "group_by_customer_and_name";
    public static final String GROUP_BY_TENANT_AND_CUSTOMER_AND_NAME_COLUMN_FAMILY_NAME = "group_by_tenant_and_customer_and_name";

}
