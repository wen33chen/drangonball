package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the NGMENUTREE_AUTH database table.
 * 
 */
@Embeddable
public class NgmenutreeAuthPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MENU_ID")
	private Integer menuId;

	@Column(name="ROLE")
	private String role;

	public NgmenutreeAuthPK() {
	}
	public int getMenuId() {
		return this.menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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
		if (!(other instanceof NgmenutreeAuthPK)) {
			return false;
		}
		NgmenutreeAuthPK castOther = (NgmenutreeAuthPK)other;
		return 
			(this.menuId == castOther.menuId)
			&& this.role.equals(castOther.role);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.menuId ^ (this.menuId >>> 32)));
		hash = hash * prime + this.role.hashCode();
		
		return hash;
	}
}