package com.cathaybk.member.rest.sso;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cathaybk.member.rest.dto.UserObject;

@Component
@Order(3)
public class VoFiltrer implements Filter {
    
    @Autowired
    private SSOProperty ssoProperty;
    
    @Autowired
    private UserObject user;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String sessionAttrName = ssoProperty.getSessionAttrName();

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession session = httpRequest.getSession(false);
        
        SSOOutputVO ssoOutputVO = (SSOOutputVO) session.getAttribute(sessionAttrName);
        
        user.setApId(ssoOutputVO.getAPID());
        user.setBranchId(ssoOutputVO.getBranchid());
        user.setBranchName(ssoOutputVO.getBranchname());
        user.setEmpId(ssoOutputVO.getEmpId());
        user.setEmpName(ssoOutputVO.getEmpname());
        user.setIp(httpRequest.getRemoteAddr());
        user.setRole(ssoOutputVO.getRole());
        //user.setRoles(ssoOutputVO.get);
        
        chain.doFilter(request, response);
    }

}
