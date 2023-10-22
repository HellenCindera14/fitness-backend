package com.gli.be.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gli.be.entity.ServiceMenu;
import com.gli.be.entity.Subscription;
import com.gli.be.entity.UserFitness;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByUserFitnessAndServiceMenu(UserFitness userFitness, ServiceMenu serviceMenu);
}

