package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the NGFEATUREROLE database table.
 * 
 */
@Embeddable
public class NgfeaturerolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FEATURE_CODE")
	private String featureCode;

	@Column(name="ROLE")
	private String role;

	public NgfeaturerolePK() {
	}
	public String getFeatureCode() {
		return this.featureCode;
	}
	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}
	public String getRole() {
		return this.role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NgfeaturerolePK)) {
			return false;
		}
		NgfeaturerolePK castOther = (NgfeaturerolePK)other;
		return 
			this.featureCode.equals(castOther.featureCode)
			&& this.role.equals(castOther.role);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.featureCode.hashCode();
		hash = hash * prime + this.role.hashCode();
		
		return hash;
	}
}