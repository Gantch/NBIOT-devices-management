package com.gantch.nbiotdevicesmanagement.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.gantch.nbiotdevicesmanagement.dao.SearchTextBased;
import com.gantch.nbiotdevicesmanagement.dao.SearchTextEntity;

import java.util.UUID;

import static com.gantch.nbiotdevicesmanagement.dao.ModelConstants.*;

/**
 * @author lcw332
 * Date 2019-12-24-14:45
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.model
 **/
@Table(name = "")
public class Group extends SearchTextBased implements SearchTextEntity {

    @PartitionKey(value = 0)
    @Column(name = ID_PROPERTY)
    private UUID id;

    @PartitionKey(value = 1)
    @Column(name = "")
    private Integer tenantId;

    @PartitionKey(value = 2)
    private Integer customerId;


    private String name;

    @Column(name = SEARCH_TEXT_PROPERTY)
    private String search_text;


    @Override
    public String getSearchText() {
        return null;
    }

    @Override
    public String getSearchTextSource() {
        return null;
    }

    @Override
    public void setSearchText(String searchText) {

    }
}
