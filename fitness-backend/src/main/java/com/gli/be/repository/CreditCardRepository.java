package com.gli.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gli.be.entity.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    // @Query(value = "SELECT p FROM credit_card p WHERE p.name = :name", nativeQuery = true)
    // UserFitness getUserByName(String name);

    // @Query(value = "SELECT p FROM credit p WHERE p.email = :email", nativeQuery = true)
    // UserFitness getUserByEmail(String email);

}
