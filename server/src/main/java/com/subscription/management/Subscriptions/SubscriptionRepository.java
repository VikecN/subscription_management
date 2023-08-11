package com.subscription.management.Subscriptions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

    @Query(value = "SELECT s FROM Subscription s WHERE s.name LIKE CONCAT('%',:name,'%')")
    Page<Subscription> findByName(@Param("name") String name, Pageable pageable);
}
