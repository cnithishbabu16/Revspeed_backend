package com.revspeed.backend_p1.repository;



import com.revspeed.backend_p1.model.ERole;
import com.revspeed.backend_p1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}

