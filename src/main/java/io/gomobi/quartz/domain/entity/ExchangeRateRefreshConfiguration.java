package io.gomobi.quartz.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter @Setter
@Entity
public class ExchangeRateRefreshConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @PrimaryQueryField
    @Column(nullable = false)
    private TimeUnit timeUnit;

    @PrimaryQueryField
    @Column(nullable = false)
    private short windowTime;

    @PrimaryQueryField
    @OneToOne
    private ExchangeRateProviderConfiguration providerConfiguration;


}
