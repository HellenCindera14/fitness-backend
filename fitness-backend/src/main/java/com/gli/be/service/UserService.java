package com.gli.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gli.be.entity.UserFitness;
import com.gli.be.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserFitness getUserByName(String name) {
        return userRepository.getUserByName(name);

    }    
    
    public UserFitness getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);

    }

    public UserFitness save(UserFitness user) {
        return userRepository.save(user);
    }

    public UserFitness getByEmailAndPassword(String name, String password) {
        return userRepository.getByEmailAndPassword(name,password);
    }

    public UserFitness getByEmaiLAndToken(String email, String token) {
        return userRepository.getByEmaiLAndToken(email,token);
    }

    public UserFitness getByEmailAndOtp(String email, String otp) {
        return userRepository.getByEmailAndOtp(email,otp);
    }

}
