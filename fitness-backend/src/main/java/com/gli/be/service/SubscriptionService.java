package com.gli.be.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gli.be.entity.ServiceMenu;
import com.gli.be.entity.Subscription;
import com.gli.be.entity.UserFitness;
import com.gli.be.repository.ServiceMenuRepository;
import com.gli.be.repository.SubscriptionRepository;
import com.gli.be.repository.UserRepository;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private ServiceMenuRepository serviceMenuRepository;


       public Subscription subscribeToService(Long userFitnessId, Long serviceMenuId) {
        UserFitness userFitness = userRepository.findById(userFitnessId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userFitnessId));
        ServiceMenu serviceMenu = serviceMenuRepository.findById(userFitnessId)
                .orElseThrow(() -> new EntityNotFoundException("ServcieMenu not found with ID: " + serviceMenuId));

        Optional<Subscription> existingSubscription = subscriptionRepository.findByUserFitnessAndServiceMenu(userFitness, serviceMenu);

        if (existingSubscription.isPresent()) {
            return existingSubscription.get();
        }

        Subscription subscription = new Subscription();
        subscription.setUserFitness(userFitness);
        subscription.setServiceMenu(serviceMenu);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusDays(30));
        subscription.setRemainingSessions(serviceMenu.getTotalSessions());

        userFitness.setBillAmount(BigDecimal.valueOf(serviceMenu.getPricePerSession()*serviceMenu.getTotalSessions()));
        userRepository.save(userFitness);

        return subscriptionRepository.save(subscription);
    }

    public void cancelSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).get();
        subscriptionRepository.delete(subscription);
    }

    public void extendSubscription(Long subscriptionId, int numberOfSessions) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).get();
        subscription.setEndDate(subscription.getEndDate().plusDays(numberOfSessions));
        subscription.setRemainingSessions(subscription.getRemainingSessions() + numberOfSessions);
        subscriptionRepository.save(subscription);
    }
}

