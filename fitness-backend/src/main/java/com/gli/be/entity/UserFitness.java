package com.gli.be.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserFitness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String telphoneNumber;
    private String otp;
    private String token;
    private Date tokenExpirationDate;
    private Date otpExpirationDate;

    @Enumerated
    private Status status;

    public enum Status {
        TERDAFTAR,
        TIDAK_TERDAFTAR,
        BELUM_TERVALIDASI
    }

    // @OneToOne
    // @JoinColumn(name = "credit_card_id", referencedColumnName = "number")
    // @Transient
    // private CreditCard creditCard;
}
