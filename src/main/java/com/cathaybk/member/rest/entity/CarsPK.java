package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CARS database table.
 * 
 */
@Embeddable
public class CarsPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String manufacturer;

	private String type;

	public CarsPK() {
	}
	public String getManufacturer() {
		return this.manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CarsPK)) {
			return false;
		}
		CarsPK castOther = (CarsPK)other;
		return 
			this.manufacturer.equals(castOther.manufacturer)
			&& this.type.equals(castOther.type);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.manufacturer.hashCode();
		hash = hash * prime + this.type.hashCode();
		
		return hash;
	}
}