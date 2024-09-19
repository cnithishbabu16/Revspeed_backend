package com.revspeed.backend_p1.repository;

import com.revspeed.backend_p1.model.OTTPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTTPlatformRepository extends JpaRepository<OTTPlatform, Long> {
}
