package com.gantch.nbiotmanagement.controller;

import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-26-12:45
 * Description:  nbiot-management , com.gantch.nbiotmanagement.controller
 **/
public class BaseController  {
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
