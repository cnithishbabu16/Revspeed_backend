//package com.revspeed.backend_p1.controller;
//
//import com.revspeed.backend_p1.dto.UserLoginDTO;
//import com.revspeed.backend_p1.dto.UserRegistrationDTO;
//import com.revspeed.backend_p1.model.ERole;
//import com.revspeed.backend_p1.model.Role;
//import com.revspeed.backend_p1.model.User;
//import com.revspeed.backend_p1.repository.RoleRepository;
//import com.revspeed.backend_p1.service.UserService;
//import com.revspeed.backend_p1.util.JwtUtil;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@RestController
//
//@RequestMapping("/api/auth")
//
//public class AuthController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
//        if (userService.findByEmail(userRegistrationDTO.getEmail()) != null) {
//            return ResponseEntity.status(400).body("User already exists with this email.");
//        }
//
//        User user = new User();
//        user.setEmail(userRegistrationDTO.getEmail());
//        user.setName(userRegistrationDTO.getName());
//        user.setPassword(userService.encodePassword(userRegistrationDTO.getPassword()));
//        //user.setPhoneNo(String.valueOf(userRegistrationDTO.getPhoneNo()));
//        //user.setAddress(userRegistrationDTO.getAddress());
//        Role defaultRole = roleRepository.findByName(ERole.ROLE_CUSTOMER);
//        if (defaultRole == null) {
//            return new ResponseEntity<>("Default role not found", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(defaultRole);
//        user.setRoles(roles);
//
//        //user.setRoles(Set.of(new Role(ERole.ROLE_CUSTOMER)));
//
//        User registeredUser = userService.registerUser(user);
//
//        if (registeredUser != null) {
//            return ResponseEntity.ok("User registered successfully");
//        } else {
//            return ResponseEntity.status(500).body("User registration failed");
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
//        User loggedInUser = userService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
//        if (loggedInUser != null) {
//            String token = jwtUtil.generateToken(loggedInUser.getEmail(), loggedInUser.getRoles().iterator().next().getName().name());
//            return ResponseEntity.ok(token);
//        }
//        return ResponseEntity.status(400).body("Invalid credentials");
//    }
//}

package com.revspeed.backend_p1.controller;

import com.revspeed.backend_p1.dto.UserLoginDTO;
import com.revspeed.backend_p1.dto.UserRegistrationDTO;
import com.revspeed.backend_p1.model.ERole;
import com.revspeed.backend_p1.model.Role;
import com.revspeed.backend_p1.model.User;
import com.revspeed.backend_p1.repository.RoleRepository;
import com.revspeed.backend_p1.service.UserService;
import com.revspeed.backend_p1.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }




    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        if (userService.findByEmail(userRegistrationDTO.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("User already exists with this email."));
        }

        User user = new User();
        user.setEmail(userRegistrationDTO.getEmail());
        user.setName(userRegistrationDTO.getName());
        user.setPassword(userService.encodePassword(userRegistrationDTO.getPassword()));
        user.setPhoneNo(String.valueOf(userRegistrationDTO.getPhoneNo())); // Assuming it's a String
        user.setAddress(userRegistrationDTO.getAddress());

        Role defaultRole = roleRepository.findByName(ERole.ROLE_CUSTOMER);
        System.out.println(defaultRole);
        if (defaultRole == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Default role not found"));
        }

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        User registeredUser = userService.registerUser(user);

        if (registeredUser != null) {
            return ResponseEntity.ok(new ApiResponse("User registered successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("User registration failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        User loggedInUser = userService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        if (loggedInUser != null) {
            String token = jwtUtil.generateToken(loggedInUser.getEmail(), loggedInUser.getRoles().iterator().next().getName().name(), loggedInUser.getId());
            Long id=loggedInUser.getId();
            System.out.println(id);
            return ResponseEntity.ok(new JwtResponse(token,id));

        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Invalid credentials"));
        }
    }

    // Helper classes for responses
    public static class ApiResponse {
        private String message;

        public ApiResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class JwtResponse {
        private String token;
        private long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public JwtResponse(String token, Long id) {
            this.token = token;
            this.id=id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
