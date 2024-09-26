package com.revspeed.backend_p1.controller;

import com.revspeed.backend_p1.model.User;
import com.revspeed.backend_p1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://35.193.163.237:31000")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return user;
        }
        throw new ResourceNotFoundException("User not found with email: " + email);
    }


    @PutMapping("/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User user) {
        User existingUser = userService.findByEmail(email);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setAddress(user.getAddress());
            existingUser.setPhoneNo(user.getPhoneNo());
            return userService.updateUserProfile(existingUser);
        }
        throw new ResourceNotFoundException("User not found with email: " + email);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}


