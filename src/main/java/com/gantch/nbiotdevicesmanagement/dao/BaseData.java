package com.gantch.nbiotdevicesmanagement.dao;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-20-16:38
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao
 **/
public abstract class BaseData extends IdBased implements Serializable {

    private static final long serialVersionUID = 5422817607129962637L;

    protected long createdTime;

    public BaseData() {
        super();
    }

    public BaseData(UUID id) {
        super(id);
    }

    public BaseData(BaseData data) {
        super(data.getId());
        this.createdTime = data.getCreatedTime();
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (int) (createdTime ^ (createdTime >>> 32));
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseData other = (BaseData) obj;
        if (createdTime != other.createdTime)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseData [createdTime=");
        builder.append(createdTime);
        builder.append(", id=");
        builder.append(id);
        builder.append("]");
        return builder.toString();
    }

}
