package com.gli.be.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gli.be.entity.UserFitness;
import com.gli.be.repository.UserRepository;

@Service
public class PaymentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public boolean verifyPaymentOTP(String email, String otp) {
        Optional<UserFitness> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserFitness userFitness = userOptional.get();

            if (userFitness.getPaymentOTP() != null &&
                    userFitness.getPaymentOTP().equals(otp) &&
                    LocalDateTime.now().isBefore(userFitness.getPaymentOTPExpiration())) {

                userFitness.setPaymentStatus(true);
                userFitness.setPaymentOTP(null);
                userFitness.setPaymentOTPExpiration(null);

                userRepository.save(userFitness);
                return true;
            }
        }

        return false;
    }

    public boolean verifyBillAmount(Long userFitnessId, BigDecimal expectedBillAmount) {
        Optional<UserFitness> userFitnessOptional = userRepository.findById(userFitnessId);

        if (userFitnessOptional.isPresent()) {
            UserFitness userFitness = userFitnessOptional.get();

            if (userFitness.getBillAmount() != null && userFitness.getBillAmount().compareTo(expectedBillAmount) == 0) {
                userFitness.setBillVerified(true);
                userRepository.save(userFitness);
                return true;
            }
        }

        return false;
    }

    public boolean isPaymentOTPExpired(String email) {
        Optional<UserFitness> userFitnessOptional = userRepository.findByEmail(email);

        if (userFitnessOptional.isPresent()) {
            UserFitness userFitness = userFitnessOptional.get();
            if (userFitness.getPaymentOTPExpiration() != null &&
                    LocalDateTime.now().isAfter(userFitness.getPaymentOTPExpiration())) {
                return true;
            }
        }

        return false;
    }

    private String generateVerificationCode() {
        int codeLength = 6;
        String allowedChars = "0123456789";

        Random random = new Random();
        StringBuilder verificationCode = new StringBuilder(codeLength);

        for (int i = 0; i < codeLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            verificationCode.append(randomChar);
        }

        return verificationCode.toString();
    }

public void sendEmailVerification(String email, Long participantId) throws MessagingException {
        String code = generateVerificationCode();

        Optional<UserFitness> userFitnessOptional = userRepository.findById(participantId);

        if (userFitnessOptional.isPresent()) {
           UserFitness userFitness = userFitnessOptional.get();
           userFitness.setPaymentOTP(code);
           userRepository.save(userFitness);
        }

        String subject = "Kode OTP";
        String text = "Kode OTP anda adalah : " + code;
        emailService.send(email,subject,text);
    }
}