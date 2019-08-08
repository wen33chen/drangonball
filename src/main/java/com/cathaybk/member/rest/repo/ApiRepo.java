package com.cathaybk.member.rest.repo;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cathaybk.member.rest.entity.Api;
import com.cathaybk.member.rest.entity.ApiPK;


public interface ApiRepo extends JpaRepository<Api, ApiPK> {

    @Cacheable(cacheNames="apiMapping")
    public List<Api> findAllByOrderByFunctionId();
    
    public List<Api> findByUri(String uri);
}
