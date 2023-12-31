package com.gli.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gli.be.entity.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
