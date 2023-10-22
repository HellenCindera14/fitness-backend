package com.gli.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gli.be.entity.CreditCard;
import com.gli.be.repository.CreditCardRepository;

@Service
public class CreditCardService {
  
    @Autowired
    CreditCardRepository ccRepository;

    public CreditCard save(CreditCard cc) {
        return ccRepository.save(cc);
    }


}
