package com.revspeed.backend_p1.service;




import com.revspeed.backend_p1.dto.PlanDTO;
import com.revspeed.backend_p1.model.OTTPlatform;
import com.revspeed.backend_p1.model.Plan;
import com.revspeed.backend_p1.repository.OTTPlatformRepository;
import com.revspeed.backend_p1.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service
//public class PlanService {
//
//    @Autowired
//    private PlanRepository planRepository;
//
//    // Create a new plan
//    public Plan createPlan(Plan plan) {
//        return planRepository.save(plan);
//    }
//
//    // Update an existing plan
//    public Plan updatePlan(Long id, Plan plan) {
//        if (planRepository.existsById(id)) {
//            plan.setId(id);
//            return planRepository.save(plan);
//        }
//        return null;
//    }
//
//    // Get plan by ID
//    public Plan getPlanById(Long id) {
//        return planRepository.findById(id).orElse(null);
//    }
//
//    // Delete a plan
//    public boolean deletePlan(Long id) {
//        if (planRepository.existsById(id)) {
//            planRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//
//    // Get all plans
//    public List<Plan> getAllPlans() {
//        return planRepository.findAll();
//    }
//}

//@Service
//public class PlanService extends Plan {
//    private final OTTPlatformRepository ottPlatformRepository;
//
//    @Autowired
//    public PlanService(OTTPlatformRepository ottPlatformRepository) {
//        this.ottPlatformRepository = ottPlatformRepository;
//    }
//
//    @Autowired
//    private PlanRepository planRepository;
//
////    public Plan createPlan(Plan plan) {
////        List<OTTPlatform> selectedOttPlatforms = ottPlatformRepository.findAllById(plan.getOttPlatforms()
////                .stream().map(OTTPlatform::getId).collect(Collectors.toList()));
////
////        plan.setOttPlatforms(selectedOttPlatforms); // Set the associated OTT platforms
////        return planRepository.save(plan);
////    }
//public PlanDTO createPlan(PlanDTO planDTO) {
//    Plan plan = new Plan();
//    plan.setName(planDTO.getName());
//    plan.setDescription(planDTO.getDescription());
//    plan.setPrice(planDTO.getPrice());
//    plan.setDuration(planDTO.getDuration());
//    plan.setSpeed(planDTO.getSpeed());
//    System.err.println("err1");
//    System.out.println(planDTO.getOttPlatforms());
//    // Find OTT Platforms by IDs and set them to the Plan entity
//    List<OTTPlatform> ottPlatforms = ottPlatformRepository.findAllById(planDTO.getOttPlatforms());
//    plan.setOttPlatforms(ottPlatforms);
//    System.err.println("err2");
//    plan = planRepository.save(plan);
//    System.err.println("err3");
//    return mapToDTO(plan);
//}
//    private PlanDTO mapToDTO(Plan plan) {
//        List<Long> ottPlatformIds = plan.getOttPlatforms().stream()
//                .map(OTTPlatform::getId)
//                .collect(Collectors.toList());
//
//        return new PlanDTO(
//                plan.getName(),
//                plan.getDescription(),
//                plan.getSpeed(),
//                plan.getPrice(),
//                plan.getDuration(),
//                ottPlatformIds
//        );
//    }
//
//    public Plan updatePlan(Long id, Plan plan) {
//        if (planRepository.existsById(id)) {
//            plan.setId(id);
//            return planRepository.save(plan);
//        }
//        return null;
//    }
//
//    public Plan getPlanById(Long id) {
//        return planRepository.findById(id).orElse(null);
//    }
//
//    public boolean deletePlan(Long id) {
//        if (planRepository.existsById(id)) {
//            planRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//
//    public List<Plan> getAllPlans() {
//        return planRepository.findAll();
//    }
//}

@Service
public class PlanService extends Plan {
    private final OTTPlatformRepository ottPlatformRepository;

    @Autowired
    public PlanService(OTTPlatformRepository ottPlatformRepository) {
        this.ottPlatformRepository = ottPlatformRepository;
    }

    @Autowired
    private PlanRepository planRepository;


    public PlanDTO createPlan(PlanDTO planDTO) {
        Plan plan = new Plan();
        plan.setName(planDTO.getName());
        plan.setDescription(planDTO.getDescription());
        plan.setPrice(planDTO.getPrice());
        plan.setDuration(planDTO.getDuration());
        plan.setSpeed(planDTO.getSpeed());
        System.err.println("err1");
        System.out.println(planDTO.getOttPlatforms());
        // Find OTT Platforms by IDs and set them to the Plan entity
        List<OTTPlatform> ottPlatforms = ottPlatformRepository.findAllById(planDTO.getOttPlatforms());
        plan.setOttPlatforms(ottPlatforms);
        System.err.println("err2");
        plan = planRepository.save(plan);
        System.err.println("err3");
        return mapToDTO(plan);
    }

    public Plan updatePlan(Long id, Plan plan) {
        if (planRepository.existsById(id)) {
            plan.setId(id);
            return planRepository.save(plan);
        }
        return null;
    }

    private PlanDTO mapToDTO(Plan plan) {
        List<Long> ottPlatformIds = plan.getOttPlatforms().stream()
                .map(OTTPlatform::getId)
                .collect(Collectors.toList());

        return new PlanDTO(
                plan.getName(),
                plan.getDescription(),
                plan.getSpeed(),
                plan.getPrice(),
                plan.getDuration(),
                ottPlatformIds
        );
    }

    public Plan getPlanById(Long id) {
        return planRepository.findById(id).orElseThrow();
    }
//    public Plan findById(Long id){
//        return planRepository.findById(id);
//    }

    public boolean deletePlan(Long id) {
        if (planRepository.existsById(id)) {
            planRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }
}
