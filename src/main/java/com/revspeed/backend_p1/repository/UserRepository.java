package com.revspeed.backend_p1.repository;




import com.revspeed.backend_p1.model.ERole;
import com.revspeed.backend_p1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<User> findUsersByRole(@Param("roleName") ERole roleName);
    //User findById(Long userId);
    boolean existsByEmail(String email);
}



