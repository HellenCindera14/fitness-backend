package com.gli.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gli.be.entity.UserFitness;

@Repository
public interface UserRepository extends JpaRepository<UserFitness, Long> {
    Optional<UserFitness> findByEmail(String email);

    @Query(value = "SELECT * FROM User_Fitness p WHERE p.name = :name", nativeQuery = true)
    UserFitness getUserByName(String name);

    @Query(value = "SELECT * FROM User_Fitness p WHERE p.email = :email", nativeQuery = true)
    UserFitness getUserByEmail(String email);

    @Query(value = "SELECT * FROM User_Fitness p WHERE p.email = :email and p.password = :password", nativeQuery = true)
    UserFitness getByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM User_Fitness p WHERE p.email = :email and p.token = :token", nativeQuery = true)
    UserFitness getByEmaiLAndToken(String email, String token);

    @Query(value = "SELECT * FROM User_Fitness p WHERE p.email = :email and p.otp = :otp", nativeQuery = true)
    UserFitness getByEmailAndOtp(String email, String otp);
    




}
