package com.cathaybk.member.rest.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cathaybk.member.rest.dto.UserObject;
import com.cathaybk.member.rest.svc.MenuService;

@RestController
public class BaseMenuController {

    @Autowired
    private UserObject user;//注入登入人員資訊
    
    @Autowired
    private MenuService menuService;

    /**
     * getMenuCodesByRole
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMenuCodesByRole", method = RequestMethod.POST, produces = { "application/json" })
    public Object getMenuCodesByRole(HttpServletRequest request) {
        return menuService.getMenuCodesByRole(user);
    }

    /**
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMenuItems", method = RequestMethod.POST)
    public Object getMenuItems(HttpServletRequest request) {
        String msgLang = "zh_TW";//預設語系為zh_TW
        return menuService.getMenuItems(msgLang, user);
    }
}
