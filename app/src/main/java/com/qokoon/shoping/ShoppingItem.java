package com.qokoon.shoping;

import java.math.BigDecimal;


public class ShoppingItem {

  private int count;
  private Product product;

  public ShoppingItem(int count, Product product) {
    this.count = count;
    this.product = product;
  }

  public int getCount() {
    return count;
  }

  public Product getProduct() {
    return product;
  }

  public void addCount(int count) {
    this.count += count;
  }

  public BigDecimal getTotalTax() {
    if (getProduct() == null || getProduct().getTaxRate() == null
        || getProduct().getPrice() == null) {
      return BigDecimal.ZERO;
    }

    return getTotalPrice().multiply(getProduct().getTaxRate()).divide(BigDecimal.valueOf(100));
  }

  public BigDecimal getTotalPrice() {
    if (getProduct() == null) {
      return BigDecimal.ZERO;
    }

    if (getProduct().getOffer() == null) {
      return getProduct().getPrice().multiply(BigDecimal.valueOf(getCount()));
    }

    return getProduct().getPrice()
        .multiply(getProduct().getOffer().getItemCountAfterOfferApplied(getCount()));
  }

  public BigDecimal getTotalPriceWithoutDiscount() {
    if (getProduct() == null) {
      return BigDecimal.ZERO;
    }

    return getProduct().getPrice().multiply(BigDecimal.valueOf(getCount()));
  }

}
