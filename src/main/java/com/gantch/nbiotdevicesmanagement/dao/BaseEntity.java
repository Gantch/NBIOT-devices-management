package com.gantch.nbiotdevicesmanagement.dao;

import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-21-9:59
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao
 **/
public interface BaseEntity {

    UUID getId();

    void setId(UUID id);
}
