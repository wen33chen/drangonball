package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ROLEFEATURE database table.
 * 
 */
@Entity
@IdClass(RolefeaturePK.class)
public class Rolefeature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String roleid;
	
    @Id
    private String id;

	public Rolefeature() {
	}

    /**
     * @return the roleid
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * @param roleid the roleid to set
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }



}