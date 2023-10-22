package com.gli.be.bean;

import lombok.Data;

@Data
public class UserReistrationRequest {

    private String email;
    private String name;
    private String password;
    private String telphoneNumber;
    private String cardNumber;
    private String cardCCV;
    private String cardExpirationDate;
    private String cardUserName;
}
