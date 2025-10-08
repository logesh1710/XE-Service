package io.gomobi.quartz.domain.repository.impl;

import io.gomobi.quartz.domain.entity.ExchangeRate;
import io.gomobi.quartz.domain.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BasicExchangeRateRepository implements ExchangeRateRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public ExchangeRate find(String sqlQuery) {

        return null;
    }

    @Override
    public List<ExchangeRate> findList(String sqlQuery) {
        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(ExchangeRate.class));
    }

    @Override
    public BigDecimal findRate(String sqlQuery) {
        return null;
    }
}
