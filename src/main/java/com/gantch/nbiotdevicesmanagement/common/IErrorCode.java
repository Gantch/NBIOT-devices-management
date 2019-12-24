package com.gantch.nbiotdevicesmanagement.common;

/**
 * @author lcw332
 * Date 2019-12-17-10:04
 * Description:  nbiot-devices-management , com.gantch.devicemanagement.api
 * 封装API的错误码
 **/
public interface IErrorCode {
    long getCode();

    String getMessage();
}
