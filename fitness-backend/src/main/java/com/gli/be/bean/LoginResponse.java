package com.gli.be.bean;

import java.util.Date;

import lombok.Data;

@Data
public class LoginResponse {

    private String name;
    private String email;
    private String token;
    private Date tokenExpirationDate;

}
