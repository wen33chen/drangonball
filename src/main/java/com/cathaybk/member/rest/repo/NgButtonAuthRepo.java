package com.cathaybk.member.rest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.cathaybk.member.rest.entity.FunctionAuth;
import com.cathaybk.member.rest.entity.NgbuttonAuth;
import com.cathaybk.member.rest.entity.NgbuttonAuthPK;

public interface NgButtonAuthRepo extends JpaRepositoryImplementation<NgbuttonAuth, NgbuttonAuthPK> {
    
    public List<NgbuttonAuth> findByRole(String role);
}