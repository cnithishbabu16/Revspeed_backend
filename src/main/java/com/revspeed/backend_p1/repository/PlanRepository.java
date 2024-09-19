package com.revspeed.backend_p1.repository;



import com.revspeed.backend_p1.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}


