package com.cathaybk.member.rest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.cathaybk.member.rest.entity.NgmenutreeAuth;
import com.cathaybk.member.rest.entity.NgmenutreeAuthPK;

public interface NgmenutreeAuthRepo extends JpaRepositoryImplementation<NgmenutreeAuth, NgmenutreeAuthPK> {

    List<NgmenutreeAuth> findByRole(String role);

    List<NgmenutreeAuth> findByMenuId(String menuId);

    List<NgmenutreeAuth> findByRoleIn(List<String> roles);

}
