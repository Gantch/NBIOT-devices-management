package com.gantch.nbiotdevicesmanagement.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-20-16:32
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao
 **/
public abstract class SearchTextBased extends BaseData{
    public SearchTextBased() {
        super();
    }

    public SearchTextBased(UUID id) {
        this.id = id;
    }

    @JsonIgnore
    public abstract String getSearchText();

}
