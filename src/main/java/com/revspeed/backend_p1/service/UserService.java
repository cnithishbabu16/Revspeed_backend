package com.revspeed.backend_p1.service;


import com.revspeed.backend_p1.model.ERole;
import com.revspeed.backend_p1.model.User;
import com.revspeed.backend_p1.repository.DeletedUserRepository;
import com.revspeed.backend_p1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private DeletedUserRepository deletedUserRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    // Register a new user
//    public User registerUser(User user) {
//        if (userRepository.existsByEmail(user.getEmail())) {
//            return null; // User already exists
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
//        return userRepository.save(user);
//    }
//
//    // Authenticate user
//    public User loginUser(String email, String password) {
//        User user = userRepository.findByEmail(email);
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return user; // Valid credentials
//        }
//        return null; // Invalid credentials
//    }
//
//    // Get user by email
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    // Update user profile
//    public User updateUserProfile(User user) {
//        return userRepository.save(user);
//    }
//
//    // Soft delete user profile
//    public void deleteUserProfile(String email) {
//        User user = userRepository.findByEmail(email);
//        if (user != null) {
//            deletedUserRepository.save(user); // Save to deleted users table
//            userRepository.delete(user); // Delete from main table
//        }
//    }
//
//    // Get all users
//    public List<User> getAllCustomers() {
//        return userRepository.findAll();
//    }
//
//    // Soft delete customer profile
//    public void softDeleteCustomer(Long id) {
//        User user = userRepository.findById(id).orElse(null);
//        if (user != null) {
//            deletedUserRepository.save(user); // Save to deleted users table
//            userRepository.delete(user); // Delete from main table
//        }
//    }
//}

@Service
public class UserService extends User implements UserDetailsService {


    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private DeletedUserRepository deletedUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    private BCryptPasswordDecoder passwordDecoder;
    @Autowired
    public UserService(DeletedUserRepository deletedUserRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.deletedUserRepository = deletedUserRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return null; // User already exists
        }
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null) {

            System.out.println("Stored Password Hash: " + user.getPassword());
            System.out.println("Input Password: " + passwordEncoder.encode(password));

            if (passwordEncoder.matches(password, user.getPassword())) {
                System.out.println("Password match successful");
                return user; // Valid credentials
            } else {
                System.out.println("Password match failed");
            }
        } else {
            System.out.println("User not found");
        }
        return null;
    }



    //public User findById(Long userId){return userRepository.findById(getId());}
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUserProfile(User user) {
        return userRepository.save(user);
    }

    public void deleteUserProfile(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            deletedUserRepository.save(user); // Soft delete
            userRepository.delete(user);
        }
    }

    public List<User> getAllCustomers() {

        List<User> customers = userRepository.findUsersByRole(ERole.ROLE_CUSTOMER);
        return customers;
    }

    public void softDeleteCustomer(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            deletedUserRepository.save(user); // Soft delete
            userRepository.delete(user);
        }
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch user from the database by email
        User user = userRepository.findByEmail(email);  // Implement this method in UserRepository
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }
        return user;
    }

    public Optional<User> findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }
}
