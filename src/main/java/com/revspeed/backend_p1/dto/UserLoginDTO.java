package com.revspeed.backend_p1.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

//public class UserLoginDTO {
//
//    @NotEmpty(message = "Email is required")
//    @Email(message = "Invalid email format")
//    private String email;
//
//    @NotEmpty(message = "Password is required")
//    private String password;
//
//    // Getters and Setters
//}

public class UserLoginDTO {
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    public @NotEmpty(message = "Email is required") @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email is required") @Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password is required") String password) {
        this.password = password;
    }

    // Getters and Setters
}
