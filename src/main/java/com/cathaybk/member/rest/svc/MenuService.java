package com.cathaybk.member.rest.svc;

import java.util.List;

import com.cathaybk.member.rest.dto.MenuItem;
import com.cathaybk.member.rest.dto.UserObject;

public interface MenuService {
    
    List<MenuItem> getMenuItems(String msgLang, UserObject user);
    
    List<String> getMenuCodesByRole(UserObject user);

}
