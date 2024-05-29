package com.example.insurance.repository;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.insurance.Models.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance,Integer>{
    Insurance findByemail(String email);
   
}
