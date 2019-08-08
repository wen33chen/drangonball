package com.cathaybk.member.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cathaybk.member.rest.entity.EmpData;


public interface EmpDataRepo extends JpaRepository<EmpData, String> {
    
}
