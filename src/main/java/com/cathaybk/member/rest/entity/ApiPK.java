package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the API database table.
 * 
 */
@Embeddable
public class ApiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FUNCTION_ID")
	private String functionId;

	@Column(name="METHOD_NAME")
	private String methodName;

	public ApiPK() {
	}
	public String getFunctionId() {
		return this.functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getMethodName() {
		return this.methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApiPK)) {
			return false;
		}
		ApiPK castOther = (ApiPK)other;
		return 
			this.functionId.equals(castOther.functionId)
			&& this.methodName.equals(castOther.methodName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.functionId.hashCode();
		hash = hash * prime + this.methodName.hashCode();
		
		return hash;
	}
}