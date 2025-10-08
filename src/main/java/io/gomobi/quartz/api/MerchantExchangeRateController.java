package io.gomobi.quartz.api;

import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.dto.data.GlobalExchangeRateDto;
import io.gomobi.quartz.dto.response.ApiResponse;
import io.gomobi.quartz.dto.response.HttpResponseStatus;
import io.gomobi.quartz.dto.response.ResponseBuilder;
import io.gomobi.quartz.service.ExchangeRateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/merchant/rates")
public class MerchantExchangeRateController {

    private final ExchangeRateService merchantExchangeRateService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Object>> getLatestExchangeRates(
        @RequestParam
        @NotNull(message = "Base currency cannot be null")
        CurrencyCode from,

        @RequestParam
        @NotNull(message = "Target currencies cannot be null")
        @NotEmpty(message = "Target currencies cannot be empty")
        CurrencyCode[] to,

        @RequestParam(required = false, defaultValue = "1")
        BigDecimal amount,

        @RequestParam(required = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime startDateTime,

        @RequestParam(required = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime endDateTime,

        @RequestParam
        @NotEmpty(message = "Master MID cannot be null or Empty")
        String masterMid,

        HttpServletRequest servletRequest
    ){
        GlobalExchangeRateDto exchangeRateData = merchantExchangeRateService.findExchangeRate(
                from,
                to,
                amount,
                startDateTime,
                endDateTime
        );
        return ResponseBuilder.build(exchangeRateData, HttpResponseStatus.FETCHED, servletRequest);
    }
}
