package com.revspeed.backend_p1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SubscriptionRequest {
    private boolean paymentSuccessful;
    private String userEmail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private Long planId;

    public boolean isPaymentSuccessful() {
        return paymentSuccessful;
    }

    public void setPaymentSuccessful(boolean paymentSuccessful) {
        this.paymentSuccessful = paymentSuccessful;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }
    @Override
    public String toString() {
        return "SubscriptionRequest{" +
                "planId=" + planId +
                ", userEmail=" + userEmail +
                ", endDate=" + endDate +
                ", payment status= "+paymentSuccessful+
                '}';
    }

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
// Getters and Setters
}