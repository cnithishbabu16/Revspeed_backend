package com.revspeed.backend_p1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter

public class PaymentRequest {

    private int amount; // amount in cents
    private String currency; // e.g., "usd"
    private String description; // description of the charge
    private String token; // Stripe token received from frontend

    // Getters and Setters

    public void setToken(String token) {
        this.token = token;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Default constructor
    public PaymentRequest() {}

    // Constructor with all fields
    public PaymentRequest(int amount, String currency, String description, String token) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.token = token;
    }
}
