package com.subscription.management.Subscriptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDTO {

    private String name;
    private String link;
    private Float amount;
    private String currency;
    private PaymentType paymentType;
    private Date startedOn;
    private boolean isActive;
}
