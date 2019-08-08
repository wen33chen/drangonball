package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the NGMENUTREE_TRANS database table.
 * 
 */
@Entity
@Table(name = "NGMENUTREE_TRANS")
@IdClass(NgmenutreeTranPK.class)
@NamedQuery(name = "NgmenutreeTran.findAll", query = "SELECT n FROM NgmenutreeTran n")
public class NgmenutreeTran implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MENU_ID")
    private Integer menuId;

    @Id
    @Column(name = "MSG_LANG")
    private String msgLang;

    @Column(name = "DISPLAY_TEXT")
    private String displayText;

    public NgmenutreeTran() {
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMsgLang() {
        return msgLang;
    }

    public void setMsgLang(String msgLang) {
        this.msgLang = msgLang;
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

}