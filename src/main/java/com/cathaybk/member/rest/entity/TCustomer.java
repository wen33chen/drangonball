package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the T_CUSTOMER database table.
 * 
 */
@Entity
@Table(name = "T_CUSTOMER")
@IdClass(TCustomerPK.class)
@NamedQuery(name = "TCustomer.findAll", query = "SELECT t FROM TCustomer t")
public class TCustomer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Id
    private long customerId;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String name;

    private String sex;

    public TCustomer() {
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Object getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

}