package com.gli.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gli.be.service.SubscriptionService;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToService(@RequestParam Long userFitnessId, @RequestParam Long serviceMenuId) {
        subscriptionService.subscribeToService(userFitnessId, serviceMenuId);
        return ResponseEntity.ok("Berlangganan berhasil.");
        
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelSubscription(@RequestParam Long subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId);
        return ResponseEntity.ok("Langganan berhasil dibatalkan.");
    }

    @PostMapping("/extend")
    public ResponseEntity<String> extendSubscription(@RequestParam Long subscriptionId, @RequestParam int numberOfSessions) {
        subscriptionService.extendSubscription(subscriptionId, numberOfSessions);
        return ResponseEntity.ok("Durasi pertemuan berhasil ditambahkan.");
    }

    
}
