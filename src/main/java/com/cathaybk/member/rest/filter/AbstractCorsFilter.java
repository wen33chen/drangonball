/*
 * Copyright (c) 2018 - Business Intelligence Info. Tech.
 * All rights reserved.
 */
package com.cathaybk.member.rest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Provides a cross site domain access filter.<br/>
 * If want to make another site to access self, must extend this.
 * 
 * @author Allen Lin
 * @since 1.0.0
 */
@Component
@Order(1)
public class AbstractCorsFilter implements Filter {

    @Value("${ALLOWED_ORIGINS}")
    private String allowedOrigins;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        // Access Origin Set on Properties:ALLOWED_ORIGINS
        String origin = request.getHeader("Origin");
        if (StringUtils.isNotBlank(origin)) {
            if (allowedOrigins.contains("*") || allowedOrigins.contains(origin)) {
                response.setHeader("Access-Control-Allow-Origin", origin);
            }
        } else {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
            "x-requested-with, authorizatio, Content-Type, Authorization, credential, X-XSRF-TOKEN, X-Access-Token");
        response.setHeader("Access-Control-Expose-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

}