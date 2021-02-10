package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.RegistrationEntity;



@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long> {
     RegistrationEntity findUserByEmail(String email);
     RegistrationEntity findByUserId(String userId);
}
