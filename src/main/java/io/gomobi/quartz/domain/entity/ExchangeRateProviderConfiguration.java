package io.gomobi.quartz.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class ExchangeRateProviderConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long id;

    @PrimaryQueryField
    @Column( unique = true, nullable = false, length = 50)
    private String providerName;

    @PrimaryQueryField
    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    private Status status;

    @Column(nullable = false, length = 30)
    private String subscriptionType;

    @Column(nullable = false)
    private LocalDateTime expireAt;

    @PrimaryQueryField
    @Column(nullable = false)
    private double fee;

    @PrimaryQueryField
    @Column(nullable = false)
    private int maxRequest;

    @OneToOne
    private ExchangeRateRefreshConfiguration refreshConfiguration;

    public enum Status{
        ACTIVE, INACTIVE, SUSPENDED
    }
}