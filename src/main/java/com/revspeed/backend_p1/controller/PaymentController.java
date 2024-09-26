package com.revspeed.backend_p1.controller;

import com.revspeed.backend_p1.dto.PaymentDTO;
import com.revspeed.backend_p1.dto.PaymentRequest;
import com.revspeed.backend_p1.model.Payment;
import com.revspeed.backend_p1.service.PaymentService;
import com.revspeed.backend_p1.service.PlanService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PlanService planService;

//    @PostMapping("/charge")
//    public ResponseEntity<Map<String,String>> processPayment(@RequestBody PaymentRequest paymentRequest) {
//        Map<String,String> response = new HashMap<>();
////        boolean paymentSuccess = paymentService.charge(paymentRequest);
////        if (paymentSuccess) {
////            return ResponseEntity.ok("Payment processed successfully");
////        } else {
////            return ResponseEntity.status(500).body("Payment processing failed");
////        }
//        try {
//            paymentService.charge(paymentRequest);
//            response.put("message","Payment Successful");
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            response.put("message", "Payment Failed: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//    }



//@PostMapping("/charge")
//public ResponseEntity<?> chargePayment(@RequestBody PaymentRequest paymentRequest) {
//    try {
//        Payment payment = paymentService.charge(paymentRequest);
//        return ResponseEntity.ok(payment);
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//}
@PostMapping("/charge")
public ResponseEntity<Map<String, String>> charge(@RequestBody PaymentRequest paymentRequest) {
    Map<String, String> response = new HashMap<>();
    try {
        Charge charge = Charge.create(Map.of(
                "amount", paymentRequest.getAmount(),
                "currency", paymentRequest.getCurrency(),
                "description", paymentRequest.getDescription(),
                "source", paymentRequest.getToken()
        ));

        if ("succeeded".equals(charge.getStatus())) {
            response.put("status", "success");
            response.put("message", "Payment processed successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "failure");
            response.put("message", charge.getFailureMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    } catch (StripeException e) {
        response.put("status", "error");
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

}

