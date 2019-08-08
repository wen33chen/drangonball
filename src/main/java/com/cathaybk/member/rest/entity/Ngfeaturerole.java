package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the NGFEATUREROLE database table.
 * 
 */
@Entity
@IdClass(NgfeaturerolePK.class)
@NamedQuery(name="Ngfeaturerole.findAll", query="SELECT n FROM Ngfeaturerole n")
public class Ngfeaturerole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FEATURE_CODE")
    private String featureCode;

	@Id
    @Column(name="ROLE")
    private String role;

	public Ngfeaturerole() {
	}

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}