package com.revspeed.backend_p1.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

//public class UserRegistrationDTO {
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @NotEmpty(message = "Email is required")
//    @Email(message = "Invalid email format")
//    private String email;
//
//    @NotEmpty(message = "Name is required")
//    private String name;
//
//    @NotEmpty(message = "Password is required")
//    private String password;
//
//    // Getters and Setters
//}
//
public class UserRegistrationDTO {
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Password is required")
    private String password;

    private Long phoneNo;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public @NotEmpty(message = "Email is required") @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email is required") @Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password is required") String password) {
        this.password = password;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }
//    public @NotEmpty(message = "Email is required") @Email(message = "Invalid email format") String getEmail() {
//        return email;
//    }
//
//    public void setEmail(@NotEmpty(message = "Email is required") @Email(message = "Invalid email format") String email) {
//        this.email = email;
//    }
//
//    public @NotEmpty(message = "Name is required") String getName() {
//        return name;
//    }
//
//    public void setName(@NotEmpty(message = "Name is required") String name) {
//        this.name = name;
//    }
//
//    public @NotEmpty(message = "Password is required") String getPassword() {
//        return password;
//    }
//
//    public void setPassword(@NotEmpty(message = "Password is required") String password) {
//        this.password = password;
//    }

    // Getters and Setters
}

