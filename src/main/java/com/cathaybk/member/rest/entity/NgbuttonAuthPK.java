package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the NGBUTTON_AUTH database table.
 * 
 */
@Embeddable
public class NgbuttonAuthPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="BUTTON_ID")
	private String buttonId;

	private String menucode;

	@Column(name="ROLE")
	private String role;

	public NgbuttonAuthPK() {
	}
	public String getButtonId() {
		return this.buttonId;
	}
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
	public String getMenucode() {
		return this.menucode;
	}
	public void setMenucode(String menucode) {
		this.menucode = menucode;
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
		if (!(other instanceof NgbuttonAuthPK)) {
			return false;
		}
		NgbuttonAuthPK castOther = (NgbuttonAuthPK)other;
		return 
			this.buttonId.equals(castOther.buttonId)
			&& this.menucode.equals(castOther.menucode)
			&& this.role.equals(castOther.role);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.buttonId.hashCode();
		hash = hash * prime + this.menucode.hashCode();
		hash = hash * prime + this.role.hashCode();
		
		return hash;
	}
}