package com.cathaybk.member.rest.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.cathaybk.member.rest.entity.FunctionAuth;
import com.cathaybk.member.rest.entity.FunctionAuthPK;

public interface FunctionAuthRepo extends JpaRepositoryImplementation<FunctionAuth, FunctionAuthPK> {

    @Query("select r from FunctionAuth r where role=?1")
    public List<FunctionAuth> findByRole1(String role);

    public FunctionAuth findByRoleAndFeature(String role, String featureCode);
    
    public List<FunctionAuth> findByRole(String role);
}