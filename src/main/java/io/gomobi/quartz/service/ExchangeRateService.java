package io.gomobi.quartz.service;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.dto.data.GlobalExchangeRateDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ExchangeRateService {

    GlobalExchangeRateDto findExchangeRate(CurrencyCode from, CurrencyCode[] to, BigDecimal amount, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
