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

    // public UserFitness getUserByName(String name) {
    //     return userRepository.getUserByName(name);

    // }
    
    // public UserFitness getUserByEmail(String email) {
    //     return userRepository.getUserByEmail(email);

    // }

    // public UserFitness save(UserFitness user) {
    //     return userRepository.save(user);
    // }

}
