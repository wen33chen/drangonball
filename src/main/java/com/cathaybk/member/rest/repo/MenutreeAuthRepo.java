package com.cathaybk.member.rest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.cathaybk.member.rest.entity.MenuTreeAuth;
import com.cathaybk.member.rest.entity.MenuTreeAuthPK;

public interface MenutreeAuthRepo extends JpaRepositoryImplementation<MenuTreeAuth, MenuTreeAuthPK> {

    List<MenuTreeAuth> findByRole(String role);

    List<MenuTreeAuth> findByMenuCode(String menuCode);

    List<MenuTreeAuth> findByRoleIn(List<String> roles);

}
