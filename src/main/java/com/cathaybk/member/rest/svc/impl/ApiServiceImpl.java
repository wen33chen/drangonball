
package com.cathaybk.member.rest.svc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cathaybk.member.rest.entity.Api;
import com.cathaybk.member.rest.repo.ApiRepo;
import com.cathaybk.member.rest.svc.ApiService;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    ApiRepo apiRepo;

    @Override
    public Map<String, Object> getApiMapping() {

        List<Api> listApi = apiRepo.findAll();
        Map<String, Object> apiMap = new HashMap<>();
        for (int i = 0; i < listApi.size(); i++) {
            Api api = listApi.get(i);
            String functionId=api.getFunctionId();
            if(apiMap.containsKey(functionId)){
                Map methodMap = (Map<String,Object>) apiMap.get(functionId);
                methodMap.put(api.getMethodName(), api.getUri());
            }else{
                Map<String, Object> methodMap = new HashMap<>();
                methodMap.put(api.getMethodName(), api.getUri());
                apiMap.put(functionId, methodMap);
            }
            
        }

        return apiMap;
    }

}
