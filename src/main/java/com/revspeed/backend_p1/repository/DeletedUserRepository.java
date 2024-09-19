package com.revspeed.backend_p1.repository;

import com.revspeed.backend_p1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedUserRepository extends JpaRepository<User, Long> {
}
