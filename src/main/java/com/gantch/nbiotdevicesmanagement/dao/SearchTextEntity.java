package com.gantch.nbiotdevicesmanagement.dao;

/**
 * @author lcw332
 * Date 2019-12-21-9:58
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao
 **/
public interface SearchTextEntity extends BaseEntity{

    String getSearchTextSource();

    void setSearchText(String searchText);
}
