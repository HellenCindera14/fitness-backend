package com.gli.be.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gli.be.entity.ServiceMenu;


@Repository
public interface ServiceMenuRepository extends JpaRepository<ServiceMenu, Long> {
}
