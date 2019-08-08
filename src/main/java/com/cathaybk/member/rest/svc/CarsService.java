package com.cathaybk.member.rest.svc;

import java.util.List;

import com.cathaybk.member.rest.entity.Cars;

public interface CarsService {
    
    public List<Cars> getManu();

    public List<Cars> findAll();
}
