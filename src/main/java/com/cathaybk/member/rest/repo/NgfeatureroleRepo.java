package com.cathaybk.member.rest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.cathaybk.member.rest.entity.Ngfeaturerole;
import com.cathaybk.member.rest.entity.NgfeaturerolePK;

public interface NgfeatureroleRepo extends JpaRepositoryImplementation<Ngfeaturerole, NgfeaturerolePK> {
    
    List<Ngfeaturerole> findByRoleIn(List<String> roles);
}
