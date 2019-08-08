package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the T_CUSTOMER database table.
 * 
 */
@Embeddable
public class TCustomerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CUSTOMER_ID")
	private long customerId;

	private String id;

	public TCustomerPK() {
	}
	public long getCustomerId() {
		return this.customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCustomerPK)) {
			return false;
		}
		TCustomerPK castOther = (TCustomerPK)other;
		return 
			(this.customerId == castOther.customerId)
			&& this.id.equals(castOther.id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.customerId ^ (this.customerId >>> 32)));
		hash = hash * prime + this.id.hashCode();
		
		return hash;
	}
}