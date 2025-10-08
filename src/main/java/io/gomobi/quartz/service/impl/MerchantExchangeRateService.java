package io.gomobi.quartz.service.impl;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.common.mapper.GlobalMapper;
import io.gomobi.quartz.common.util.StringUtil;
import io.gomobi.quartz.domain.entity.ExchangeRate;
import io.gomobi.quartz.domain.query.BasicNativeQueryBuilder;
import io.gomobi.quartz.domain.query.SqlCondition;
import io.gomobi.quartz.domain.repository.ExchangeRateRepository;
import io.gomobi.quartz.dto.data.GlobalExchangeRateDto;
import io.gomobi.quartz.dto.data.ToCurrencyDto;
import io.gomobi.quartz.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MerchantExchangeRateService implements ExchangeRateService {

    private static final Class<?> EXCHANGE_RATE_CLASS = ExchangeRate.class;

    private final ExchangeRateRepository basicExchangeRateRepository;

    @Override
    public GlobalExchangeRateDto findExchangeRate(CurrencyCode from, CurrencyCode[] to, BigDecimal amount, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        BasicNativeQueryBuilder queryBuilder = buildBaseQuery(from, to);

        if (startDateTime != null || endDateTime != null) {
            queryBuilder.and()
                    .where("liveRateTimestamp", SqlCondition.BETWEEN, startDateTime, endDateTime);
        }

        String sqlQuery =queryBuilder
                .orderBy(BasicNativeQueryBuilder.SortOrder.DESC, "liveRateTimestamp")
                .limit(to.length).build();

        List<ToCurrencyDto> toCurrencyDtoList = GlobalMapper.toCurrencyDtoList(basicExchangeRateRepository.findList(sqlQuery), amount);
        return new GlobalExchangeRateDto(from, amount, toCurrencyDtoList);
    }


    private BasicNativeQueryBuilder buildBaseQuery(CurrencyCode from, CurrencyCode... to){
        return BasicNativeQueryBuilder.fromBuilder(EXCHANGE_RATE_CLASS)
                .select()
                .where("baseCurrency", SqlCondition.EQUAL, from)
                .and()
                .where("targetCurrency", SqlCondition.IN, to);
    }

}
