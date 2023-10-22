package com.gli.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gli.be.bean.LoginRequest;
import com.gli.be.bean.LoginResponse;
import com.gli.be.bean.LogoutRequest;
import com.gli.be.entity.UserFitness;
import com.gli.be.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "Registration Controller")
public class LoginController {

    @Autowired
    UserService userService;
 

    @PostMapping("/login")
    @ApiOperation("Login ")
    public ResponseEntity<Object> login(@RequestBody LoginRequest req) {
        UserFitness us = userService.getByEmailAndPassword(req.getEmail(), req.getPassword());
        if (us == null)
            return new ResponseEntity<>("User atau password yang anda masukan salah", HttpStatus.UNAUTHORIZED);
        LoginResponse ls = new LoginResponse();
        ls.setName(us.getName());
        ls.setToken(us.getToken());
        ls.setTokenExpirationDate(us.getTokenExpirationDate());
        ls.setEmail(us.getEmail());
        return new ResponseEntity<>(ls, HttpStatus.OK);

    }

    @PostMapping("/logout")
    @ApiOperation("Logout ")
    public ResponseEntity<Object> logout(@RequestBody LogoutRequest req) {
        UserFitness us = userService.getByEmaiLAndToken(req.getEmail(), req.getToken());
        if (us == null)
            return new ResponseEntity<>("Anda telah logout sebelumnya", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>("Sukses logout", HttpStatus.OK);

    }
}