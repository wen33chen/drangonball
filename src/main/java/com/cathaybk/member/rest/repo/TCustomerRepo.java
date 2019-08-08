package com.cathaybk.member.rest.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.cathaybk.member.rest.entity.TCustomer;
import com.cathaybk.member.rest.entity.TCustomerPK;

public interface TCustomerRepo extends JpaRepositoryImplementation<TCustomer, TCustomerPK> {

}
