package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MENU_TREE database table.
 * 
 */
@Entity
@IdClass(EmpRolePK.class)
@Table(name="EMP_ROLE")
public class EmpRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String account;
	
	@Id
	private String role;

	private String test;
	
	public EmpRole() {
	}

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }



    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
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

    /**
     * @return the test
     */
    public String getTest() {
        return test;
    }

    /**
     * @param test the test to set
     */
    public void setTest(String test) {
        this.test = test;
    }

}