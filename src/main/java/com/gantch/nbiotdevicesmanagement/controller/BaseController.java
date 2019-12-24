package com.gantch.nbiotdevicesmanagement.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-24-15:11
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.controller
 **/
@Slf4j
public class BaseController {
    UUID toUUID(String id) {
        if(id==null || id.length() == 0) {
            return null;
        }else {
            return UUID.fromString(id);
        }
    }


    <T> T checkNotNull(T reference) throws Exception{
        if (reference == null) {
            throw new Exception("Requested item wasn't found!");
        }
        return reference;
    }
}
