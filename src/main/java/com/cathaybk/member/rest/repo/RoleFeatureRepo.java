package com.cathaybk.member.rest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.cathaybk.member.rest.entity.Rolefeature;
import com.cathaybk.member.rest.entity.RolefeaturePK;



public interface RoleFeatureRepo extends JpaRepositoryImplementation<Rolefeature, RolefeaturePK> {

    @Query("select r.id from Rolefeature r where roleid=?1")
    List<String> findByrole(String roleid); 
}
