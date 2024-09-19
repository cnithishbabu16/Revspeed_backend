package com.revspeed.backend_p1.repository;



import com.revspeed.backend_p1.model.Subscription;
import com.revspeed.backend_p1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUser(User user);
    //List<Subscription> findActiveSubscriptionsByUser(User user,boolean active);
    @Query("SELECT s FROM Subscription s WHERE s.user = :user AND s.active = :active")
    List<Subscription> findActiveSubscriptionsByUser(@Param("user") User user, @Param("active") boolean active);
}

