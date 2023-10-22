package com.gli.be.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("bill_amount")
    @Column(name="bill_amount")
    private BigDecimal billAmount;

    @JsonProperty("payment_otp")
    @Column(name="payment_otp")
    private String paymentOTP;

    @JsonProperty("payment_status")
    @Column(name="payment_status")
    private boolean paymentStatus;

    @JsonProperty("payment_otp_expiration")
    @Column(name="payment_otp_expiration")
    private LocalDateTime paymentOTPExpiration;

    @JsonProperty("is_bill_verified")
    @Column(name="is_bill_verified")
    private boolean isBillVerified;



    public enum Status {
        TERDAFTAR,
        TIDAK_TERDAFTAR,
        BELUM_TERVALIDASI
    }
}
