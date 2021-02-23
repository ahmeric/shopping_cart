package com.qokoon.shoping;

import com.qokoon.shoping.enums.OfferType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;


public class ShoppingCart {

  private HashMap<Long, ShoppingItem> shoppingItems;
  private BigDecimal totalPrice;
  private BigDecimal totalDiscount;
  private BigDecimal totalTax;
  private GlobalOffer globalOffer;

  public ShoppingCart() {
  }


  public HashMap<Long, ShoppingItem> getShoppingItems() {
    return shoppingItems;
  }

  public BigDecimal getTotalPrice() {
    if (totalPrice == null) {
      return BigDecimal.ZERO;
    }

    return totalPrice;
  }

  public BigDecimal getTotalDiscount() {
    if (totalDiscount == null) {
      return BigDecimal.ZERO;
    }

    return totalDiscount;
  }

  public BigDecimal getTotalTax() {
    if (totalTax == null) {
      return BigDecimal.ZERO;
    }

    return totalTax;
  }

  public GlobalOffer getGlobalOffer() {
    return globalOffer;
  }

  public void setGlobalOffer(GlobalOffer globalOffer) {
    this.globalOffer = globalOffer;
  }


  public void calculateShoppingCart() {

    if (getShoppingItems() == null) {
      return;
    }

    BigDecimal totalCalculatedPrice = getTotalCalculatedPrice();
    BigDecimal totalCalculatedTax = getTotalCalculatedTax();
    BigDecimal totalPriceWithoutDiscount = getTotalPriceWithoutDiscount();

    if (isGlobalOfferEligible(totalCalculatedPrice)) {
      totalCalculatedPrice = totalCalculatedPrice.multiply(getGlobalOffer().getInvertedOfferRate());
      totalCalculatedTax = totalCalculatedTax.multiply(getGlobalOffer().getInvertedOfferRate());
    }

    this.totalTax = totalCalculatedTax.setScale(2, RoundingMode.HALF_UP);
    this.totalPrice = totalCalculatedPrice.add(getTotalTax())
        .setScale(2, RoundingMode.HALF_EVEN);
    this.totalDiscount = totalPriceWithoutDiscount.subtract(totalCalculatedPrice)
        .setScale(2, RoundingMode.HALF_UP);
  }

  private boolean isGlobalOfferEligible(BigDecimal totalAmount) {
    return getGlobalOffer() != null
        && totalAmount != null
        && getGlobalOffer().getOfferType()
        .equals(OfferType.ACCORDING_TO_TOTAL_AMOUNT_20_PERCENT_DISCOUNT)
        && getGlobalOffer().getTotalShoppingAmount().compareTo(totalAmount) <= 0;
  }

  public void addShoppingItem(ShoppingItem shoppingItem) {
    if (shoppingItems == null) {
      shoppingItems = new HashMap<>();
    }
    long key = shoppingItem.getProduct().getId();

    if (shoppingItems.get(key) != null) {
      shoppingItems.get(key).addCount(shoppingItem.getCount());
    } else {
      shoppingItems.put(key, shoppingItem);
    }
  }

  private BigDecimal getTotalCalculatedPrice(){
    return getShoppingItems().entrySet().stream()
        .map(x -> x.getValue().getTotalPrice())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal getTotalCalculatedTax(){
    return getShoppingItems().entrySet().stream()
        .map(x -> x.getValue().getTotalTax())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal getTotalPriceWithoutDiscount(){
    return getShoppingItems().entrySet().stream()
        .map(x -> x.getValue().getTotalPriceWithoutDiscount())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
