package com.revspeed.backend_p1.service;

import com.revspeed.backend_p1.model.OTTPlatform;
import com.revspeed.backend_p1.repository.OTTPlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OTTPlatformService {

    @Autowired
    private OTTPlatformRepository ottPlatformRepository;

    public OTTPlatform createPlatform(OTTPlatform platform) {
        return ottPlatformRepository.save(platform);
    }

    public OTTPlatform updatePlatform(Long id, OTTPlatform platform) {
        if (ottPlatformRepository.existsById(id)) {
            platform.setId(id);
            return ottPlatformRepository.save(platform);
        }
        return null;
    }

    public List<OTTPlatform> getAllPlatforms() {
        return ottPlatformRepository.findAll();
    }

    public List<OTTPlatform> getPlatformsByIds(List<Long> ids) {
        return ottPlatformRepository.findAllById(ids);
    }

    public void deletePlatform(Long id) {
        if (ottPlatformRepository.existsById(id)) {
            ottPlatformRepository.deleteById(id);
        }
    }

    public Long getPlatformsByIds(Long id) {
        return id;
    }
}
