package com.gli.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gli.be.entity.UserFitness;
import com.gli.be.service.UserService;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

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
