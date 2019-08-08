package com.cathaybk.member.rest.dto;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * 登入人員基本資料
 * @author NT81652
 *
 */
@Component
@RequestScope
public class UserObject {

    /** 員工編號 */
    private String empId;

    /** 員工姓名 */
    private String empName;

    /** 單位代號 */
    private String branchId;

    /** 部單位名稱 */
    private String branchName;

    /** 角色 */
    private HashMap<String, Integer> roles;

    private String role;

    /** IP */
    private String ip;

    /** 安控代號 */
    private String apId;

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

    public HashMap<String, Integer> getRoles() {
        return roles;
    }

    public void setRoles(HashMap<String, Integer> roles) {
        this.roles = roles;
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
