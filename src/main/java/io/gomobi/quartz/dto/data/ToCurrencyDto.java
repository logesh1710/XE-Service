package io.gomobi.quartz.dto.data;

import io.gomobi.quartz.common.constant.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ToCurrencyDto(
        CurrencyCode to,
        String mid,
        String calculated,
        LocalDateTime timestamp
) {
    public static ToCurrencyDto build(
            CurrencyCode to,
            BigDecimal mid,
            BigDecimal amount,
            LocalDateTime timestamp
    ){
            return new ToCurrencyDto(
                    to,
                    mid.toPlainString(),
                    mid.multiply(amount).toPlainString(),
                    timestamp
            );
    }

}
