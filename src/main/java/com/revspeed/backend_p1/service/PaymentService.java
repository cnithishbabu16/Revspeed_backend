package com.revspeed.backend_p1.service;

import com.revspeed.backend_p1.dto.PaymentRequest;
import com.revspeed.backend_p1.model.Payment;
import com.revspeed.backend_p1.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
//@Service
//public class PaymentService {
//    @Value("${stripe.secret.key}")
//    private String stripeKey;
//    @PostConstruct
//    public void init() {
//        Stripe.apiKey = stripeKey;  // Initialize Stripe API key
//    }
//    //private String stripeKey="sk_test_51PvyLS07yTsI6F7jZRl0qMBM4uEZ97ntahXEeQYnEvOoW4KmIUrwbUCbjrEqplLnF5X2cTWyVOBmTRPM5OCtUa8a00OfDVTZMo";
//
//    public Payment charge(PaymentRequest paymentRequest) throws Exception {
//        //Stripe.apiKey = "{secret"; // Your Stripe API key
//
//
//        Map<String, Object> chargeParams = new HashMap<>();
//        chargeParams.put("amount", (int) (paymentRequest.getAmount() * 100)); // amount in cents
//        chargeParams.put("currency", "usd");
//        chargeParams.put("source", paymentRequest.getPaymentMethod()); // Token from frontend
//        chargeParams.put("description",paymentRequest.getDescription());
//
//        try {
//            Charge charge = Charge.create(chargeParams);
//            if (charge.getStatus().equals("succeeded")) {
//                // Payment was successful, save payment details
//                // ...
//                return savePayment(paymentRequest);
//            } else {
//                throw new Exception("Payment failed");
//            }
//        } catch (StripeException e) {
//            throw new Exception("Payment failed: " + e.getMessage());
//        }
//    }
//
//    private Payment savePayment(PaymentRequest paymentRequest) {
//        // Save payment details to the database
//        // ...
//        return null;
//    }
//}
@Service
public class PaymentService {



    @Value("${stripe.secret.key}")
    private String stripeKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeKey;  // Initialize Stripe API key
    }

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment charge(PaymentRequest paymentRequest) throws Exception {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentRequest.getAmount()); // amount in cents
        chargeParams.put("currency", paymentRequest.getCurrency());
        chargeParams.put("source", paymentRequest.getToken()); // Token from frontend
        chargeParams.put("description", paymentRequest.getDescription());

        try {
            Charge charge = Charge.create(chargeParams); // Create charge with Stripe
            if ("succeeded".equals(charge.getStatus())) {
                // Payment was successful, save payment details
                return savePayment(paymentRequest);
               // return paymentRepository.save(Payment);
            } else {
                throw new Exception("Payment failed");
            }
        } catch (StripeException e) {
            throw new Exception("Payment failed: " + e.getMessage());
        }
    }

    private Payment savePayment(PaymentRequest paymentRequest) {
        // Save payment details to the database (you need to implement this part)
        Payment payment = new Payment();
        // Fill payment object with necessary details, such as amount, user, etc.
        // payment.setUser(paymentRequest.getUser()); // You'll need user info
        payment.setAmount(paymentRequest.getAmount());
        //payment.setCurrency(paymentRequest.getCurrency());
        //payment.setDescription(paymentRequest.getDescription());
        // Save the payment to the database (PaymentRepository)
        return paymentRepository.save(payment);
    }
}
