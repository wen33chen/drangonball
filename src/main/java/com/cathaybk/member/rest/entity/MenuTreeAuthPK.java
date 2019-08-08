package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the NGMENU_TREE_AUTH database table.
 * 
 */
@Embeddable
public class MenuTreeAuthPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MENU_CODE")
	private String menuCode;

	@Column(name="ROLE")
	private String role;

	public MenuTreeAuthPK() {
	}
	public String getMenuCode() {
		return this.menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
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
		if (!(other instanceof MenuTreeAuthPK)) {
			return false;
		}
		MenuTreeAuthPK castOther = (MenuTreeAuthPK)other;
		return 
			this.menuCode.equals(castOther.menuCode)
			&& this.role.equals(castOther.role);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.menuCode.hashCode();
		hash = hash * prime + this.role.hashCode();
		
		return hash;
	}
}