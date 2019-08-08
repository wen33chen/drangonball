package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ROLEFEATURE database table.
 * 
 */
@Embeddable
public class RolefeaturePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String roleid;

	private String id;

	public RolefeaturePK() {
	}
	public String getRoleid() {
		return this.roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RolefeaturePK)) {
			return false;
		}
		RolefeaturePK castOther = (RolefeaturePK)other;
		return 
			this.roleid.equals(castOther.roleid)
			&& this.id.equals(castOther.id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleid.hashCode();
		hash = hash * prime + this.id.hashCode();
		
		return hash;
	}
}