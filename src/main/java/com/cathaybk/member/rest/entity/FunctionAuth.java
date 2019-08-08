package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * The persistent class for the MENU_TREE database table.
 * 
 */
@Entity
@IdClass(FunctionAuthPK.class)
@Table(name = "FUNCTION_AUTH")
public class FunctionAuth implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "FUNCTION_ID")
    private String functionId;

    @Id   
    @Column(name = "METHOD_NAME")
    private String methodName;
   
    @Id
    private String role;

    @Column(name = "FEATURE_CODE")
    private String feature;

    @Column(name = "UPDATE_USER")
    private String updateUser;

    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;

    public FunctionAuth() {
    }

    /**
     * @return the functionId
     */
    public String getFunctionId() {
        return functionId;
    }

    /**
     * @param functionId the functionId to set
     */
    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the updateUser
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser the updateUser to set
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return the updateDate
     */
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

}