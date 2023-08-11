package com.subscription.management.Subscriptions;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriptionDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public SubscriptionDTO convertSubscriptiontoSubscriptionDTO(Subscription subscription){
        SubscriptionDTO subscriptionDTO = modelMapper.map(subscription, SubscriptionDTO.class);
        return subscriptionDTO;
    }

    public Subscription convertSubscriptionDTOtoSubscription(SubscriptionDTO subscriptionDTO){
        Subscription subscription = modelMapper.map(subscriptionDTO, Subscription.class);
        return subscription;
    }
}
