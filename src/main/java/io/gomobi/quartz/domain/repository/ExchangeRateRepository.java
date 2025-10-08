package io.gomobi.quartz.domain.repository;

import io.gomobi.quartz.domain.entity.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeRateRepository {
    
    ExchangeRate find(String sqlQuery);

    List<ExchangeRate> findList(String sqlQuery);

    BigDecimal findRate(String sqlQuery);

}
