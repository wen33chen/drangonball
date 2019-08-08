package com.cathaybk.member.rest.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.cathaybk.member.rest.entity.EmpRole;
import com.cathaybk.member.rest.entity.EmpRolePK;



public interface EmpRoleRepo extends JpaRepositoryImplementation<EmpRole, EmpRolePK> {
    
    public EmpRole findByAccount(String account);
    
    //@Cacheable(value = "testCache")
    //public EmpRole findByAccount(String account);
//Ehcache 
     
        
    
        
    
}