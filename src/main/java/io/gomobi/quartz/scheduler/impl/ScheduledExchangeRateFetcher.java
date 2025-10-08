package io.gomobi.quartz.scheduler.impl;

import io.gomobi.quartz.domain.repository.ExchangeRateRepository;
import io.gomobi.quartz.scheduler.ExchangeRateFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
public class ScheduledExchangeRateFetcher implements ExchangeRateFetcher {

    private final ThreadPoolTaskScheduler taskScheduler;
    private final ScheduledFuture<Object> scheduledFuture;
    private final ExchangeRateRepository basicExchangeRateProviderRepository;

    @Override
    public void fetchAndPersistLiveRates() {
        
    }
}