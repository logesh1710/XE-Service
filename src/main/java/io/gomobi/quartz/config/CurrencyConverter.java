package io.gomobi.quartz.config;

import io.gomobi.quartz.common.constant.CurrencyCode;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter implements Converter<String, CurrencyCode> {

    @Override
    public CurrencyCode convert(String source) {
        return CurrencyCode.valueOf(source);
    }
}
