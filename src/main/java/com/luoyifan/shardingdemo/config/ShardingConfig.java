package com.luoyifan.shardingdemo.config;

import com.google.common.collect.Range;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author EvanLuo
 * @date 2018/11/14 10:20
 */
@Slf4j
@Configuration
public class ShardingConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() throws SQLException {

        try {
            //总策略
            ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
            shardingRuleConfig.setTableRuleConfigs(Stream.of(getOrderTableRuleConfiguration())
                    .collect(Collectors.toList()));

            //数据源选择
            Map<String, DataSource> dataSourceMap = new HashMap<>();
            dataSourceMap.put("ds0", getHikariDataSource());

            //选项
            Properties prop = new Properties();
            //是否u展示sql
            prop.setProperty("sql.show", "true");
            return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new HashMap<>(), prop);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private TableRuleConfiguration getOrderTableRuleConfiguration() {
        //查询策略
        PreciseShardingAlgorithm preciseShardingAlgorithm = (PreciseShardingAlgorithm<Timestamp>) (collection, preciseShardingValue) -> {
            Timestamp timestamp = preciseShardingValue.getValue();
            log.info("shardingValue:{}", timestamp);
            return preciseShardingValue.getLogicTableName() + "_" + timestamp.toLocalDateTime().getYear();
        };

        //查询策略
        RangeShardingAlgorithm rangeShardingAlgorithm = (RangeShardingAlgorithm<Timestamp>) (collection, rangeShardingValue) -> {
            Range<Timestamp> valueRange = rangeShardingValue.getValueRange();
            log.info("shardingValueRange:{}", valueRange);
//              rangeShardingValue.getLogicTableName()
            return Collections.EMPTY_SET;
        };

        //分表策略,分表字段为order_time
        StandardShardingStrategyConfiguration shardingStrategyConfiguration = new StandardShardingStrategyConfiguration("order_time",
                preciseShardingAlgorithm, rangeShardingAlgorithm);

        TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration();
        tableRuleConfig.setLogicTable("order_record");
        //分表规则,表命名为order_record_2017 / order_record_2018 / order_record_2019 ...这样
        tableRuleConfig.setActualDataNodes("ds0.order_record_$->{2017..2019}");
        tableRuleConfig.setTableShardingStrategyConfig(shardingStrategyConfiguration);
        return tableRuleConfig;
    }

    private HikariDataSource getHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dataSourceProperties.getDriverClassName());
        hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
        hikariConfig.setUsername(dataSourceProperties.getUsername());
        hikariConfig.setPassword(dataSourceProperties.getPassword());
        return new HikariDataSource(hikariConfig);
    }
}
