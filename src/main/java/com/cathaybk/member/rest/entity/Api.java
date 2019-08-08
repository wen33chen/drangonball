package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the API database table.
 * 
 */
@Entity
@IdClass(ApiPK.class)
@NamedQuery(name="Api.findAll", query="SELECT a FROM Api a")
public class Api implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="FUNCTION_ID")
    private String functionId;

	@Id
    @Column(name="METHOD_NAME")
    private String methodName;

	@Column(name="UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name="UPDATE_USER")
	private String updateUser;

	private String uri;
	
	private String role;

	public Api() {
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
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }



    /**
     * @param methodName the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }



    public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
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

}