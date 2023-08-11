package com.subscription.management.Subscriptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subscriptions")
@Slf4j
@CrossOrigin("http://localhost:3000")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("")
    public Page<Map<String, Object>> getAllSubscriptions(@RequestParam(defaultValue = "10") int limit,
                                                  @RequestParam(defaultValue = "0") int offset,
                                                  @RequestParam(defaultValue = "") String name){
        log.info("EXEC: /subscriptions");
        return subscriptionService.search(limit, offset, name);
    }

    @GetMapping("/{id}")
    public Optional<Subscription> getSubscription(@PathVariable String id){
        log.info("EXEC: /subscriptions/id");
        return subscriptionService.findSubscription(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        log.info("EXEC: /subscriptions/create");
        return subscriptionService.create(subscription);
    }

    @PutMapping("/update/{id}")
    public Subscription updateSubscription(@RequestBody Map<String, Object> newSubscription, @PathVariable String id) {
        log.info("EXEC: /subscriptions/update/id");
        return subscriptionService.update(id, newSubscription);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<HttpStatus> statusSubscription(@PathVariable String id){
        log.info("EXEC: /subscriptions/status/id");
        return subscriptionService.status(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSubscription(@PathVariable String id){
        log.info("EXEC: /subscriptions/delete/id");
        return subscriptionService.delete(id);
    }
}
