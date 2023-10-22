package com.gli.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gli.be.entity.UserFitness;
import com.gli.be.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(tags = "UserFitness")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/get-by-name/{name}")
    @ApiOperation("Get UserFitness By Name")
    public ResponseEntity<UserFitness> getAllBreedVariants(@PathVariable String name) {
        UserFitness u = userService.getUserByName(name);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping("/get-by-email/{email}")
    @ApiOperation("Get UserFitness By email")
    public ResponseEntity<UserFitness> getUserByEmail(@PathVariable String name) {
        UserFitness u = userService.getUserByEmail(name);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PostMapping("/save")
    @ApiOperation("Get UserFitness By email")
    public ResponseEntity<UserFitness> saveUser(@RequestBody UserFitness user) {
        UserFitness u = userService.save(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

}
