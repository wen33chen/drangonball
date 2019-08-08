package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the NGMENUTREE_AUTH database table.
 * 
 */
@Entity
@Table(name = "NGMENUTREE_AUTH")
@IdClass(NgmenutreeAuthPK.class)
@NamedQuery(name = "NgmenutreeAuth.findAll", query = "SELECT n FROM NgmenutreeAuth n")
public class NgmenutreeAuth implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MENU_ID")
    private Integer menuId;

    @Id
    @Column(name = "ROLE")
    private String role;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "CREATE_ON_UTC")
    private Timestamp createOnUtc;

    @Column(name = "MODIFY_BY")
    private String modifyBy;

    @Column(name = "MODIFY_ON_UTC")
    private Timestamp modifyOnUtc;

    public NgmenutreeAuth() {
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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

    public Timestamp getCreateOnUtc() {
        return this.createOnUtc;
    }

    public void setCreateOnUtc(Timestamp createOnUtc) {
        this.createOnUtc = createOnUtc;
    }

    public String getModifyBy() {
        return this.modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Timestamp getModifyOnUtc() {
        return this.modifyOnUtc;
    }

    public void setModifyOnUtc(Timestamp modifyOnUtc) {
        this.modifyOnUtc = modifyOnUtc;
    }

}