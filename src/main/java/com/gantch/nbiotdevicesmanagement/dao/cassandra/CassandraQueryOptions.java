package com.gantch.nbiotdevicesmanagement.dao.cassandra;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.QueryOptions;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lcw332
 * Date 2019-12-20-15:03
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao.cassandra
 * QueryOptions配置类
 **/

@Component
@Configuration
@Data
public class CassandraQueryOptions {

    @Value("${cassandra.query.default_fetch_size}")
    private Integer defaultFetchSize;
    @Value("${cassandra.query.read_consistency_level}")
    private String readConsistencyLevel;
    @Value("${cassandra.query.write_consistency_level}")
    private String writeConsistencyLevel;

    private QueryOptions opts;

    private ConsistencyLevel defaultReadConsistencyLevel;
    private ConsistencyLevel defaultWriteConsistencyLevel;

    @PostConstruct
    public void initOpts(){
        opts = new QueryOptions();
        opts.setFetchSize(defaultFetchSize);
    }
    public QueryOptions getOpts() {
        return opts;
    }

    public void setOpts(QueryOptions opts) {
        this.opts = opts;
    }

    protected ConsistencyLevel getDefaultReadConsistencyLevel() {
        if (defaultReadConsistencyLevel == null) {
            if (readConsistencyLevel != null) {
                defaultReadConsistencyLevel = ConsistencyLevel.valueOf(readConsistencyLevel.toUpperCase());
            } else {
                defaultReadConsistencyLevel = ConsistencyLevel.ONE;
            }
        }
        return defaultReadConsistencyLevel;
    }

    protected ConsistencyLevel getDefaultWriteConsistencyLevel() {
        if (defaultWriteConsistencyLevel == null) {
            if (writeConsistencyLevel != null) {
                defaultWriteConsistencyLevel = ConsistencyLevel.valueOf(writeConsistencyLevel.toUpperCase());
            } else {
                defaultWriteConsistencyLevel = ConsistencyLevel.ONE;
            }
        }
        return defaultWriteConsistencyLevel;
    }
}
