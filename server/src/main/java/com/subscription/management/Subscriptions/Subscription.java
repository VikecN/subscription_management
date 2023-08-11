package com.subscription.management.Subscriptions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Subscription {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false, unique = true, length = 255)
    private String name;

    @Column(name = "link", nullable = false, unique = true)
    private String link;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PaymentType paymentType;

    @Column(name = "gracePeriod")
    private Integer gracePeriod;

    @Column(name = "startedOn", nullable = false)
    private Date startedOn;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @CreatedDate
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;




}
