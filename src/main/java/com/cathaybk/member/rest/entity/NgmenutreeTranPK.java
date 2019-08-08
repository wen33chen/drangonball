package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the NGMENUTREE_TRANS database table.
 * 
 */
@Embeddable
public class NgmenutreeTranPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MENU_ID")
	private Integer menuId;

	@Column(name="MSG_LANG")
	private String msgLang;

	public NgmenutreeTranPK() {
	}
	public int getMenuId() {
		return this.menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMsgLang() {
		return this.msgLang;
	}
	public void setMsgLang(String msgLang) {
		this.msgLang = msgLang;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof NgmenutreeTranPK)) {
			return false;
		}
		NgmenutreeTranPK castOther = (NgmenutreeTranPK)other;
		return 
			(this.menuId == castOther.menuId)
			&& this.msgLang.equals(castOther.msgLang);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.menuId ^ (this.menuId >>> 32)));
		hash = hash * prime + this.msgLang.hashCode();
		
		return hash;
	}
}