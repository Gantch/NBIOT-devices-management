package com.gantch.nbiotdevicesmanagement.dao.device;

import com.gantch.nbiotdevicesmanagement.dao.ModelConstants;
import com.gantch.nbiotdevicesmanagement.dao.cassandra.CassandraAbstractSearchTextDao;
import com.gantch.nbiotdevicesmanagement.dao.page.TextPageLink;
import com.gantch.nbiotdevicesmanagement.model.Group;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

/**
 * @author lcw332
 * Date 2019-12-24-15:19
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao.device
 **/
@Component
public class CassandraGroupDao extends CassandraAbstractSearchTextDao<Group> implements GroupDao{
    @Override
    protected Class<Group> getColumnFamilyClass() {
        return Group.class;
    }

    @Override
    protected String getColumnFamilyName() {
        return ModelConstants.GROUP_COLUMN_FAMILY_NAME;
    }

    @Override
    public List<Group> findGroupsByTenantId(Integer tenantId, TextPageLink pageLink) {
        List<Group> groups = findPageWithTextSearch(ModelConstants.GROUP_BY_TENANT_AND_SEARCH_TEXT_COLUMN_FAMILY_NAME,
                Arrays.asList(eq(ModelConstants.GROUP_TENANT_ID_PROPERTY,tenantId)),
                    pageLink);
        return groups;
    }

    @Override
    public List<Group> findGroupsByTenantIdAndCustomerId(Integer tenantId, Integer customerId, TextPageLink pageLink) {
        return null;
    }

    @Override
    public List<Group> findGroupsByCustomerId(Integer customerId, TextPageLink pageLink) {
        return null;
    }

    @Override
    public Optional<Group> findGroupByTenantAndCustomerIdAndName(Integer tenantId, Integer customerId, String name) {
        return Optional.empty();
    }
}
