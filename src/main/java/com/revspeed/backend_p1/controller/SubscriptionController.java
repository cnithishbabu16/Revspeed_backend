package com.revspeed.backend_p1.controller;


import com.revspeed.backend_p1.dto.SubscriptionRequest;
import com.revspeed.backend_p1.model.Plan;
import com.revspeed.backend_p1.model.Subscription;
import com.revspeed.backend_p1.model.User;
import com.revspeed.backend_p1.service.PlanService;
import com.revspeed.backend_p1.service.SubscriptionService;
import com.revspeed.backend_p1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private final UserService userService;  // Use service to handle User operations

    @Autowired
    public SubscriptionController(UserService userService, PlanService planService) {
        this.userService = userService;
        this.planService = planService;
    }
    @Autowired
    private final PlanService planService;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        try {
            Subscription createdSubscription = subscriptionService.subscribeToPlan(userService, planService, subscription.getEndDate());
            return ResponseEntity.ok(createdSubscription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

//    @PostMapping("/subscribe")
//    public ResponseEntity<String> subscribeToPlan(@RequestBody SubscriptionRequest subscriptionRequest) {
//        if (subscriptionRequest.isPaymentSuccessful()) {
//            try {
//                subscriptionService.subscribeToPlan(userService , planService);
//                return ResponseEntity.ok("Subscription Successful");
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subscription Failed: " + e.getMessage());
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subscription Failed: Payment was not successful");
//        }
//    }
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToPlan(@RequestBody SubscriptionRequest subscriptionRequest) {
        System.out.println("Received payload: " + subscriptionRequest);
        if (subscriptionRequest.isPaymentSuccessful()) {
            try {
                // Fetch the user by email
                User user = userService.findByEmail(subscriptionRequest.getUserEmail());
                        //.orElseThrow(() -> new RuntimeException("User not found"));

                // Fetch the plan by ID
                Plan plan = planService.getPlanById(subscriptionRequest.getPlanId());
                        // .orElseThrow(() -> new RuntimeException("Plan not found"));
                System.out.println("Received endDate: " + subscriptionRequest.getEndDate());


                Subscription subscription = subscriptionService.subscribeToPlan(user, plan,subscriptionRequest.getEndDate());
                if (subscription != null) {
                    return ResponseEntity.ok("{ \"status\": \"Subscription successful\" }");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subscription Failed: Active subscription exists");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subscription Failed: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subscription Failed: Payment was not successful");
        }
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable Long userId) {
        try {
            Optional<User> userOptional = userService.findById(userId);

            User user = userOptional.get();

            List<Subscription> subscriptions = subscriptionService.getSubscriptions(user);

            if (subscriptions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            //user = userOptional.get();
            //List<Subscription> subscriptions = subscriptionService.getSubscriptions(user);
            return ResponseEntity.ok(subscriptions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

//        List<Subscription> subscriptions = subscriptionService.getSubscriptions(userService);
//        return ResponseEntity.ok(subscriptions);
    }



//@PutMapping("/{id}")
//public ResponseEntity<?> updateSubscription(@PathVariable Long id, @Valid @RequestBody SubscriptionDto subscriptionDto) {
//    Subscription subscription = subscriptionService.updateSubscription(id, subscriptionDto);
//    return ResponseEntity.ok(subscription);
//}

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {

        if (subscriptionService.cancelSubscription(id)!=null) {
            return ResponseEntity.ok("Subscription deleted successfully");
        }
        return ResponseEntity.status(404).body("Subscription not found");
    }
}

