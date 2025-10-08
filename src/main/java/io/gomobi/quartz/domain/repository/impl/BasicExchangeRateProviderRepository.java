package io.gomobi.quartz.domain.repository.impl;

import io.gomobi.quartz.domain.entity.ExchangeRateProviderConfiguration;
import io.gomobi.quartz.domain.query.BasicNativeQueryBuilder;
import io.gomobi.quartz.domain.query.SqlCondition;
import io.gomobi.quartz.domain.repository.ExchangeRateProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BasicExchangeRateProviderRepository implements ExchangeRateProviderRepository {

    private static final Class<?> EXCHANGE_RATE_PROVIDER_CONFIG = ExchangeRateProviderConfiguration.class;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public ExchangeRateProviderConfiguration findByStatus(ExchangeRateProviderConfiguration.Status status) {
        String sqlQuery = buildBaseQuery()
                .where("status", SqlCondition.EQUAL, status)
                .build();

        return (ExchangeRateProviderConfiguration) jdbcTemplate.queryForObject(sqlQuery, EXCHANGE_RATE_PROVIDER_CONFIG);
    }


    private static BasicNativeQueryBuilder buildBaseQuery(){
        return BasicNativeQueryBuilder.fromBuilder(EXCHANGE_RATE_PROVIDER_CONFIG)
                .select();
    }
}
