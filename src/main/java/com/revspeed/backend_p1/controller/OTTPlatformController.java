package com.revspeed.backend_p1.controller;

import com.revspeed.backend_p1.model.OTTPlatform;
import com.revspeed.backend_p1.service.OTTPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ottplatforms")
public class OTTPlatformController {

    @Autowired
    private OTTPlatformService ottPlatformService;

    @PostMapping("/create")
    public OTTPlatform createPlatform(@RequestBody OTTPlatform platform) {
        return ottPlatformService.createPlatform(platform);
    }

    @PutMapping("/update/{id}")
    public OTTPlatform updatePlatform(@PathVariable Long id, @RequestBody OTTPlatform platform) {
        return ottPlatformService.updatePlatform(id, platform);
    }

    @GetMapping("/all")
    public List<OTTPlatform> getAllPlatforms() {
        return ottPlatformService.getAllPlatforms();
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlatform(@PathVariable Long id) {
        ottPlatformService.deletePlatform(id);
    }
}

