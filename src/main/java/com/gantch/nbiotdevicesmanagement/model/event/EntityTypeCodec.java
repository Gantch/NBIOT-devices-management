package com.gantch.nbiotdevicesmanagement.model.event;

import com.datastax.driver.extras.codecs.enums.EnumNameCodec;

/**
 * Created by CZX on 2018/5/28.
 */
public class EntityTypeCodec extends EnumNameCodec<EntityType> {

    public EntityTypeCodec() {
        super(EntityType.class);
    }

}
