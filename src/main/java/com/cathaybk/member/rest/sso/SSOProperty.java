package com.cathaybk.member.rest.sso;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config/application-local.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "com.cathaybk.common.sso.util")
public class SSOProperty {

    private String sessionAttrName;

    private String aesSeed;

    private String requestAttrName;

    private String checkKey;

    private String sessionTokenKey;

    private String requestTokenKey;

    private String switchUserSessionAttrName;

    private String switchUserRequestAttrName;

    private String local;

    private String empId;

    private String empName;

    private String branchId;

    private String branchName;

    private String role;

    private String ip;

    private String apId;
    
    private String apIp;
    
    private String serverUrl;
    

    public String getApIp() {
        return apIp;
    }

    public void setApIp(String apIp) {
        this.apIp = apIp;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getSessionAttrName() {
        return sessionAttrName;
    }

    public void setSessionAttrName(String sessionAttrName) {
        this.sessionAttrName = sessionAttrName;
    }

    public String getAesSeed() {
        return aesSeed;
    }

    public void setAesSeed(String aesSeed) {
        this.aesSeed = aesSeed;
    }

    public String getRequestAttrName() {
        return requestAttrName;
    }

    public void setRequestAttrName(String requestAttrName) {
        this.requestAttrName = requestAttrName;
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }

    public String getSessionTokenKey() {
        return sessionTokenKey;
    }

    public void setSessionTokenKey(String sessionTokenKey) {
        this.sessionTokenKey = sessionTokenKey;
    }

    public String getRequestTokenKey() {
        return requestTokenKey;
    }

    public void setRequestTokenKey(String requestTokenKey) {
        this.requestTokenKey = requestTokenKey;
    }

    public String getSwitchUserSessionAttrName() {
        return switchUserSessionAttrName;
    }

    public void setSwitchUserSessionAttrName(String switchUserSessionAttrName) {
        this.switchUserSessionAttrName = switchUserSessionAttrName;
    }

    public String getSwitchUserRequestAttrName() {
        return switchUserRequestAttrName;
    }

    public void setSwitchUserRequestAttrName(String switchUserRequestAttrName) {
        this.switchUserRequestAttrName = switchUserRequestAttrName;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getApId() {
        return apId;
    }

    public void setApId(String apId) {
        this.apId = apId;
    }

}
