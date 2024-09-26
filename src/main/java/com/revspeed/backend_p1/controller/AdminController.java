package com.revspeed.backend_p1.controller;




import com.revspeed.backend_p1.dto.PlanDTO;
import com.revspeed.backend_p1.model.Plan;
import com.revspeed.backend_p1.model.User;
import com.revspeed.backend_p1.service.PlanService;
import com.revspeed.backend_p1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/admin")
//public class AdminController {
//    @Autowired
//    private PlanService planService;
//
//    @Autowired
//    private UserService userService;
//
//    // Create a new plan
//    @PostMapping("/plan")
//    public Plan createPlan(@RequestBody Plan plan) {
//        return planService.createPlan(plan);
//    }
//
//    // Update an existing plan
//    @PutMapping("/plan/{id}")
//    public Plan updatePlan(@PathVariable Long id, @RequestBody Plan plan) {
//        Plan existingPlan = planService.getPlanById(id);
//        if (existingPlan != null) {
//            existingPlan.setName(plan.getName());
//            existingPlan.setDescription(plan.getDescription());
//            existingPlan.setPrice(plan.getPrice());
//            existingPlan.setDuration(plan.getDuration());
//            return planService.updatePlan(id,existingPlan);
//        }
//        return null;
//    }
//
//    // Delete a plan
//    @DeleteMapping("/plan/{id}")
//    public void deletePlan(@PathVariable Long id) {
//        planService.deletePlan(id);
//    }
//
//    // Get all plans
//    @GetMapping("/plans")
//    public List<Plan> getAllPlans() {
//        return planService.getAllPlans();
//    }
//
//    // Get all customers
//    @GetMapping("/customers")
//    public List<User> getAllCustomers() {
//        return userService.getAllCustomers();
//    }
//
//    // Delete a customer profile (soft delete)
//    @DeleteMapping("/customer/{id}")
//    public void deleteCustomer(@PathVariable Long id) {
//        userService.softDeleteCustomer(id);
//    }
//}
@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    @PostMapping("/plan")
    public ResponseEntity<PlanDTO> createPlan(@RequestBody PlanDTO planDTO) {
        System.out.println(planDTO);
        PlanDTO createdPlan = planService.createPlan(planDTO);
        return ResponseEntity.ok(createdPlan);
    }

    @PutMapping("/plan/{id}")
    public Plan updatePlan(@PathVariable Long id, @RequestBody Plan plan) {
        return planService.updatePlan(id, plan);
    }

    @DeleteMapping("/plan/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Long id) {
        if (planService.deletePlan(id)) {
            return ResponseEntity.ok("Plan deleted successfully");
        }
        return ResponseEntity.status(404).body("Plan not found");
    }

    @GetMapping("/plans")
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/customers")
    public List<User> getAllCustomers() {

        return userService.getAllCustomers();
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        userService.softDeleteCustomer(id);
        return ResponseEntity.ok("Customer profile deleted successfully");
    }
}