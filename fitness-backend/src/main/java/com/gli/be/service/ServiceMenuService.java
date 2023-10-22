package com.gli.be.service;

import com.gli.be.entity.ServiceMenu;
import com.gli.be.repository.ServiceMenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceMenuService {
    @Autowired
    private ServiceMenuRepository serviceMenuRepository;

    public List<ServiceMenu> getAllServiceMenus() {
        return serviceMenuRepository.findAll();
    }

}

