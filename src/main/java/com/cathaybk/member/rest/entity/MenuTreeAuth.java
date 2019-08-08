package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the NGMENU_TREE_AUTH database table.
 * 
 */
@Entity
@Table(name="NGMENU_TREE_AUTH")
@IdClass(MenuTreeAuthPK.class)
@NamedQuery(name="MenuTreeAuth.findAll", query="SELECT m FROM MenuTreeAuth m")
public class MenuTreeAuth implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MENU_CODE")
    private String menuCode;

	@Id
    @Column(name="ROLE")
    private String role;

	@Column(name="CREATE_BY")
	private String createBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="MODIFY_BY")
	private String modifyBy;

	@Temporal(TemporalType.DATE)
	@Column(name="MODIFY_DATE")
	private Date modifyDate;

	public MenuTreeAuth() {
	}

	public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}