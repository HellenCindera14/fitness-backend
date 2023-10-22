package com.gli.be.bean;

import lombok.Data;

@Data
public class LogoutRequest {

    private String email;
    private String token;

}
