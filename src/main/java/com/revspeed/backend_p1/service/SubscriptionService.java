package com.revspeed.backend_p1.service;




import com.revspeed.backend_p1.model.Plan;
import com.revspeed.backend_p1.model.Subscription;
import com.revspeed.backend_p1.model.User;
import com.revspeed.backend_p1.repository.PlanRepository;
import com.revspeed.backend_p1.repository.SubscriptionRepository;
import com.revspeed.backend_p1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//@Service
//public class SubscriptionService {
//
//    @Autowired
//    private SubscriptionRepository subscriptionRepository;
//
//    @Autowired
//    private PlanRepository planRepository;
//
//    // Subscribe a user to a plan
//    public Subscription subscribeToPlan(User user, Plan plan) {
//        // Check if the user has an active subscription
//        List<Subscription> activeSubscriptions = subscriptionRepository.findActiveSubscriptionsByUser(user.getId());
//        if (!activeSubscriptions.isEmpty()) {
//            Subscription lastSubscription = activeSubscriptions.get(0);
//            if (lastSubscription.getEndDate().after(new Date())) {
//                return null; // Existing active subscription
//            }
//        }
//        Subscription subscription = new Subscription();
//        subscription.setUser(user);
//        subscription.setPlan(plan);
//        subscription.setStartDate(new Date());
//        // Assuming plan duration is set in days
//        subscription.setEndDate(new Date(System.currentTimeMillis() + (plan.getDuration() * 24 * 60 * 60 * 1000L)));
//        return subscriptionRepository.save(subscription);
//    }
//
//    // Get subscriptions for a user
//    public List<Subscription> getSubscriptions(User user) {
//        return subscriptionRepository.findByUser(user);
//    }
//}
//

@Service
public class SubscriptionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private PlanRepository planRepository;

    public Subscription subscribeToPlan(User user, Plan plan, Date endDate) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new Exception("User not found"));
//        Plan plan = planRepository.findById(id)
//                .orElseThrow(() -> new Exception("Plan not found"));
       // List<Subscription> activeSubscriptions = subscriptionRepository.findActiveSubscriptionsByUser(user,true);
//        if (!activeSubscriptions.isEmpty()) {
//            Subscription lastSubscription = activeSubscriptions.get(0);
//            if (lastSubscription.getEndDate().after(new Date())) {
//                return null; // Existing active subscription
//            }
//        }
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDate(new Date());
        subscription.setEndDate(endDate);
        subscription.setActive(true);
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptions(User user) {
        return subscriptionRepository.findActiveSubscriptionsByUser(user,true);
    }

    public Subscription cancelSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));


        subscription.setActive(false); // Mark subscription as inactive
        return subscriptionRepository.save(subscription);

        //return true;
    }
}

