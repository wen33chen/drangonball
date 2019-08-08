package com.cathaybk.member.rest.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the CARS database table.
 * 
 */
@Entity
@IdClass(CarsPK.class)
@NamedQuery(name = "Cars.findType1", query = "SELECT c FROM Cars c where c.type=?1")
public class Cars implements Serializable {
    private static final long serialVersionUID = 1L;
  
   
    @Id
    private String manufacturer;

    @Id
    private String type;

    @Column(name = "MIN_PRICE")
    private BigDecimal minPrice;

    private BigDecimal price;

    public Cars() {
    }


    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }


    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    /**
     * @return the type
     */
    public String getType() {
        return type;
    }


    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }


    public BigDecimal getMinPrice() {
        return this.minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}