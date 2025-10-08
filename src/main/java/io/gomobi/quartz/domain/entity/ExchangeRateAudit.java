package io.gomobi.quartz.domain.entity;


import io.gomobi.quartz.common.constant.CurrencyCode;
import io.gomobi.quartz.common.constant.RequestSource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class ExchangeRateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime requestedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestSource requestedSource;

    @Column(nullable = false, length = 50)
    private String requestedBy;

    @Column(nullable = false, unique = true)
        private String requesterId;

    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal requestedAmount;

    @Column(nullable = false, length = 25)
    private String providerName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyCode baseCurrency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyCode targetCurrency;

    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal liveExchangeRate;

    @Column(nullable = false)
    private double merchantFee;

    @Column(nullable = false)
    private double netConvertedRate;
}
