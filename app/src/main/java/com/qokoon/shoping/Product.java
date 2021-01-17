package com.qokoon.shoping;

import java.math.BigDecimal;


public class Product {

  private long id;
  private String description;
  private BigDecimal price;
  private BigDecimal taxRate;
  private Offer offer;

  public Product(long id, String description, BigDecimal price, BigDecimal taxRate,
      Offer offer) {
    this.id = id;
    this.description = description;
    this.price = price;
    this.taxRate = taxRate;
    this.offer = offer;
  }

  public long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getTaxRate() {
    return taxRate;
  }

  public Offer getOffer() {
    return offer;
  }


}
