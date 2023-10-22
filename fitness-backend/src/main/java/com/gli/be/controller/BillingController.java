package com.gli.be.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gli.be.service.PaymentService;

@RestController
public class BillingController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/verify-bill")
    public ResponseEntity<String> verifyBillAmount(@RequestParam Long userFitnessId,@RequestParam BigDecimal expectedBillAmount) {
        if (paymentService.verifyBillAmount(userFitnessId,expectedBillAmount)) {
            return ResponseEntity.ok("Verifikasi tagihan berhasil.");
        } else {
            return ResponseEntity.badRequest().body("Verifikasi tagihan gagal.");
        }
    }
    
}
