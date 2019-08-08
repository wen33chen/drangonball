package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the NGBUTTON_AUTH database table.
 * 
 */
@Entity
@IdClass(NgbuttonAuthPK.class)
@Table(name = "NGBUTTON_AUTH")
@NamedQuery(name = "NgbuttonAuth.findAll", query = "SELECT n FROM NgbuttonAuth n")
public class NgbuttonAuth implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BUTTON_ID")
    private String buttonId;

    @Id
    private String menucode;

    @Id
    @Column(name = "ROLE")
    private String role;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    @Column(name = "UPDATE_USER")
    private String updateUser;

    public NgbuttonAuth() {
    }

 
    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


    /**
     * @return the buttonId
     */
    public String getButtonId() {
        return buttonId;
    }


    /**
     * @param buttonId the buttonId to set
     */
    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }


    /**
     * @return the menucode
     */
    public String getMenucode() {
        return menucode;
    }


    /**
     * @param menucode the menucode to set
     */
    public void setMenucode(String menucode) {
        this.menucode = menucode;
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