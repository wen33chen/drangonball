package com.cathaybk.member.rest.svc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.cathaybk.member.rest.dto.UserObject;
import com.cathaybk.member.rest.entity.Rolefeature;
import com.cathaybk.member.rest.repo.RoleFeatureRepo;
import com.cathaybk.member.rest.svc.JsontokenService;
import com.cathaybk.member.rest.svc.RoleFeatureService;

@Service
public class RoleFeatureServiceImpl implements RoleFeatureService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RoleFeatureRepo roleFeatureRepo;

    @Autowired
    JsontokenService jsontokenService;

    
    @Autowired
    private UserObject user;
    
    @Override
    public List<String> role() {
        System.out.println(user.getRole());
        System.out.println(user.getApId());
        System.out.println(user.getEmpId());
        
        //roleFeatureRepo.findAll();
        return roleFeatureRepo.findByrole(user.getRole());
    }

}
