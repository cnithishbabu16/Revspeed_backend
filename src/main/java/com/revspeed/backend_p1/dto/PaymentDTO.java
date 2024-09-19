package com.revspeed.backend_p1.dto;

import com.revspeed.backend_p1.model.User;

public class PaymentDTO {
    private double amount;
    private User user;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getters and Setters
}
