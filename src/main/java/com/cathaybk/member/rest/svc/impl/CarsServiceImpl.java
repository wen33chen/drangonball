
package com.cathaybk.member.rest.svc.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cathaybk.member.rest.entity.Cars;
import com.cathaybk.member.rest.repo.CarsRepo;
import com.cathaybk.member.rest.svc.CarsService;

@Service
public class CarsServiceImpl implements CarsService {

    @Autowired
    CarsRepo CarsRepo;
    
    @Override
    public List<Cars> findAll() {
        return CarsRepo.findAll();
    }
    
    @Override
    public List<Cars> getManu() {
        List<Cars> tempList = findAll();
        List<Cars> resultList = new ArrayList<>();
        Set<String> manuSet = new HashSet<>();
        for (int i = 0; i < tempList.size(); i++) {
            String manu = tempList.get(i).getManufacturer();            
            if (!manuSet.contains(manu)) {
                manuSet.add(manu);
                resultList.add(tempList.get(i));
            }
        }

        return resultList;

    }

  

}
