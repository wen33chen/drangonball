package com.cathaybk.member.rest.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@SuppressWarnings("rawtypes")
public class WebRequestContext {

    private RequestTemplate request;

    private Class<?> responseTranrs;

    public RequestTemplate getRequest() {
        return request;
    }

    public void setRequest(RequestTemplate request) {
        this.request = request;
    }

    public Class<?> getResponseTranrs() {
        return responseTranrs;
    }

    public void setResponseTranrs(Class<?> responseTranrs) {
        this.responseTranrs = responseTranrs;
    }

}
