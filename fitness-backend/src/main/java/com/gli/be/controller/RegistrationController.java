package com.gli.be.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gli.be.bean.UserReistrationRequest;
import com.gli.be.entity.CreditCard;
import com.gli.be.entity.UserFitness;
import com.gli.be.entity.UserFitness.Status;
import com.gli.be.service.CreditCardService;
import com.gli.be.service.EmailService;
import com.gli.be.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/registration")
@Api(tags = "Registration Controller")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    CreditCardService ccService;

    @Autowired
    EmailService emailService;

    @PostMapping({ "", "/" })
    @ApiOperation("Registration New User")
    public ResponseEntity<Object> saveUser(@RequestBody UserReistrationRequest user) {
        System.out.println("masuk sini 1");
        UserFitness us = userService.getUserByEmail(user.getEmail());
        if (us != null)
            return new ResponseEntity<>("Gagal Regis. cobalah menggunakan email lain", HttpStatus.CONFLICT);

        UserFitness uf = new UserFitness();
        uf.setEmail(user.getEmail());
        uf.setName(user.getName());
        uf.setPassword(user.getPassword());
        uf.setTelphoneNumber(user.getTelphoneNumber());
        uf.setStatus(Status.BELUM_TERVALIDASI);
        uf.setOtp(generateOTP(4));
        System.out.println("masuk sini 1");
        UserFitness u = userService.save(uf);

        CreditCard cc = new CreditCard();
        cc.setUserName(user.getCardUserName());
        cc.setNumber(user.getCardNumber());
        cc.setCcv(user.getCardCCV());
        cc.setExpirationDate(user.getCardExpirationDate());
        ccService.save(cc);
        System.out.println("sukses regist:" + u.toString() + "cc: " + cc.toString());
        emailService.send("No reply: OTP Fitness BNI",
                "selamat pendaftaran anda tinggal selangkah lagi. klik tautan atau masukan otp di bawah ini: \n\n"
                        + uf.getOtp(),
                uf.getEmail());

        return new ResponseEntity<>("Sukses Registrasi! Silahkan cek email anda untuk memverifikasi", HttpStatus.OK);
    }

    @GetMapping("/validate/{email}/{otp}")
    public ResponseEntity<Object> validateOTP(
            @PathVariable String email, @PathVariable String otp) {

        UserFitness uf = userService.getByEmailAndOtp(email, otp);
        if (uf == null) {
            return new ResponseEntity<>("Gagal Verifikasi, pastikan email dan otp anda benar", HttpStatus.CONFLICT);
        }
        uf.setOtp(null);
        uf.setOtpExpirationDate(null);
        uf.setStatus(Status.TERDAFTAR);
        userService.save(uf);
        return new ResponseEntity<>("Selamat! akun anda telah terferifikasi", HttpStatus.OK);

    }

    public static String generateOTP(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero");
        }

        // Karakter yang diizinkan dalam OTP
        String allowedChars = "0123456789";

        StringBuilder otp = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            char nextChar = allowedChars.charAt(index);
            otp.append(nextChar);
        }

        return otp.toString();
    }
}