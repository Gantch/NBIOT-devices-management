package com.gantch.nbiotdevicesmanagement.dao;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-20-16:44
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao
 **/
public interface Dao<T> {

    List<T> find();

    T findById(UUID id);

    ListenableFuture<T> findByIdAsync(UUID id);

    T save(T t);

    boolean removeById(UUID id);
}