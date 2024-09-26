package com.revspeed.backend_p1.controller;





import com.revspeed.backend_p1.model.Plan;
import com.revspeed.backend_p1.model.Subscription;
import com.revspeed.backend_p1.model.User;
import com.revspeed.backend_p1.service.PlanService;
import com.revspeed.backend_p1.service.SubscriptionService;
import com.revspeed.backend_p1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/customer")
//public class CustomerController {
//
//    @Autowired
//    private SubscriptionService subscriptionService;
//
//    @Autowired
//    private PlanService planService;
//
//    @Autowired
//    private UserService userService;
//
//    // Get all available plans
//    @GetMapping("/plans")
//    public List<Plan> getAllPlans() {
//        return planService.getAllPlans();
//    }
//
//    // Subscribe to a plan
//    @PostMapping("/subscribe")
//    public Subscription subscribeToPlan(@RequestParam Long planId, @RequestParam String email) {
//        User user = userService.findByEmail(email);
//        Plan plan = planService.getPlanById(planId);
//        return subscriptionService.subscribeToPlan(user, plan);
//    }
//
//    // Get subscriptions for a user
//    @GetMapping("/subscriptions")
//    public List<Subscription> getSubscriptions(@RequestParam String email) {
//        User user = userService.findByEmail(email);
//        return subscriptionService.getSubscriptions(user);
//    }
//
//    // Update customer profile
//    @PutMapping("/profile")
//    public User updateProfile(@RequestBody User user) {
//        return userService.updateUserProfile(user);
//    }
//
//    // Delete customer profile (soft delete)
//    @DeleteMapping("/profile")
//    public void deleteProfile(@RequestParam String email) {
//        userService.deleteUserProfile(email);
//    }
//}
@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    @GetMapping("/plans")
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }

//    @PostMapping("/subscribe")
//    public Subscription subscribeToPlan(@RequestParam Long planId, @RequestParam String email) {
//        User user = userService.findByEmail(email);
//        Plan plan = planService.getPlanById(planId);
//        return subscriptionService.subscribeToPlan(user, plan,endDate);
//    }

    @GetMapping("/subscriptions")
    public List<Subscription> getSubscriptions(@RequestParam String email) {
        User user = userService.findByEmail(email);
        return subscriptionService.getSubscriptions(user);
    }

    @PutMapping("/updateprofile")
    public User updateProfile(@RequestBody User user) {
        return userService.updateUserProfile(user);
    }

    @DeleteMapping("/delete/{email}")
    public void deleteProfile(@PathVariable String email) {
        userService.deleteUserProfile(email);
    }
}

