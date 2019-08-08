package com.cathaybk.member.rest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.cathaybk.member.rest.entity.Ngmenutree;

public interface NgmenutreeRepo extends JpaRepositoryImplementation<Ngmenutree, Integer> {
    
    List<Ngmenutree> findByEnabledAndParentIdNotNull(char enabled);
    
    List<Ngmenutree> findByEnabledAndParentIdNotNullAndMenuIdIn(char enabled, List<Integer> menuIds);
    
    List<Ngmenutree> findByEnabledAndParentIdNotNullAndMenuCodeIn(char enabled, List<String> menuCodes);
    
    Ngmenutree findByMenuId(int id);
    
}
