package com.revspeed.backend_p1.controller;
//
//
//import com.revspeed.backend_p1.model.Plan;
//import com.revspeed.backend_p1.service.PlanService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/plans")
//public class PlanController {
//
//    @Autowired
//    private PlanService planService;
//
//    @PostMapping("/create")
//    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan) {
//        Plan createdPlan = planService.createPlan(plan);
//        return ResponseEntity.ok(createdPlan);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Plan>> getAllPlans() {
//        List<Plan> plans = planService.getAllPlans();
//        return ResponseEntity.ok(plans);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody Plan plan) {
//        Plan updatedPlan = planService.updatePlan(id, plan);
//        if (updatedPlan != null) {
//            return ResponseEntity.ok(updatedPlan);
//        }
//        return ResponseEntity.status(404).body(null);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletePlan(@PathVariable Long id) {
//        if (planService.deletePlan(id)) {
//            return ResponseEntity.ok("Plan deleted successfully");
//        }
//        return ResponseEntity.status(404).body("Plan not found");
//    }
//}
//

import com.revspeed.backend_p1.dto.PlanDTO;
import com.revspeed.backend_p1.model.OTTPlatform;
import com.revspeed.backend_p1.model.Plan;
import com.revspeed.backend_p1.service.OTTPlatformService;
import com.revspeed.backend_p1.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/plans")
public class PlanController {

//    @Autowired
//    private Plan plan;



    @Autowired
    private final PlanService planService;




    public PlanController( OTTPlatformService ottPlatformService, PlanService planService) {

        this.ottPlatformService = ottPlatformService;
        this.planService = planService;
    }

    @Autowired
    private final OTTPlatformService ottPlatformService;

//    @PostMapping("/create")
//    public ResponseEntity<Plan> createPlan(@RequestBody PlanDTO planDTO) {
//
//        List<OTTPlatform> ottPlatforms = ottPlatformService.getPlatformsByIds(planDTO.getOttPlatforms());
//        Plan plan = new Plan();
//        plan.setName(planDTO.getName());
//        plan.setDescription(planDTO.getDescription());
//        plan.setPrice(planDTO.getPrice());
//        plan.setDuration(planDTO.getDuration());
//        plan.setSpeed(planDTO.getSpeed());
//        plan.setOttPlatforms(ottPlatforms);
//
//        Plan createdPlan = planService.createPlan(plan);
//        return ResponseEntity.ok(createdPlan);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody PlanDTO planDTO) {
        List<OTTPlatform> ottPlatforms = ottPlatformService.getPlatformsByIds(planDTO.getOttPlatforms());
        Plan plan = planService.getPlanById(id);
        if (plan != null) {
            plan.setName(planDTO.getName());
            plan.setDescription(planDTO.getDescription());
            plan.setPrice(planDTO.getPrice());
            plan.setDuration(planDTO.getDuration());
            plan.setSpeed(planDTO.getSpeed());
            plan.setOttPlatforms(ottPlatforms);

            Plan updatedPlan = planService.updatePlan(id, plan);
            return ResponseEntity.ok(updatedPlan);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Plan>> getAllPlans() {
        List<Plan> plans = planService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Long id) {
        if (planService.deletePlan(id)) {
            return ResponseEntity.ok("Plan deleted successfully");
        }
        return ResponseEntity.status(404).body("Plan not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id) {
        Plan plan = planService.getPlanById(id);
        if (plan != null) {
            return ResponseEntity.ok(plan);
        }
        return ResponseEntity.status(404).body(null);
    }
}
