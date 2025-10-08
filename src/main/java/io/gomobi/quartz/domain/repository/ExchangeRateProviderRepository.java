package io.gomobi.quartz.domain.repository;

import io.gomobi.quartz.domain.entity.ExchangeRateProviderConfiguration;

public interface ExchangeRateProviderRepository {

    ExchangeRateProviderConfiguration findByStatus(ExchangeRateProviderConfiguration.Status status);

}
