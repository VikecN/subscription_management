package com.subscription.management.Subscriptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Page<Map<String, Object>> search(int limit, int offset, String name) {

        Pageable pageable = PageRequest.of(offset, limit);

        Page<Subscription> page = subscriptionRepository.findByName(name, pageable);

        return page.map(x -> Map.of("currency", x.getCurrency(),
                                    "amount", x.getAmount(),
                                    "active", x.isActive(),
                                    "name", x.getName(),
                                    "id", x.getId()));

    }

    public Subscription create(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> findSubscription(String id) {
        return subscriptionRepository.findById(id);
    }

    public Subscription update(String id, Map<String, Object> newSubscription) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);

        if (subscription.isEmpty()){
            return null;
        }

        Subscription oldSubscription = subscription.get();

        for (Map.Entry<String, Object> pair : newSubscription.entrySet()) {
            switch (pair.getKey()) {
                case "name" -> oldSubscription.setName(pair.getValue().toString());
                case "link" -> oldSubscription.setLink(pair.getValue().toString());
                case "amount" -> oldSubscription.setAmount(Float.parseFloat(pair.getValue().toString()));
                case "currency" -> oldSubscription.setCurrency(pair.getValue().toString());
                case "paymentType" -> oldSubscription.setPaymentType(PaymentType.valueOf(pair.getValue().toString()));
                case "startedOn" -> oldSubscription.setStartedOn(Date.valueOf(pair.getValue().toString()));
                case "gracePeriod" -> oldSubscription.setGracePeriod(Integer.parseInt(pair.getValue().toString()));
                case "active" -> oldSubscription.setActive(Boolean.parseBoolean(pair.getValue().toString()));

            }
        }

        subscriptionRepository.save(oldSubscription);

        return oldSubscription;
    }

    public ResponseEntity<HttpStatus> status(String id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);

        if (subscription.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        subscription.get().setActive(!subscription.get().isActive());
        subscriptionRepository.save(subscription.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> delete(String id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);

        if (subscription.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        subscriptionRepository.deleteById(subscription.get().getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}