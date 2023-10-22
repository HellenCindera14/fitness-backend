package com.gli.be.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ServiceMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String schedule;
    private int totalSessions;
    private int durationInMinutes;
    private double pricePerSession;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceMenu")
    private List<Exercise> exercises;

    
}
