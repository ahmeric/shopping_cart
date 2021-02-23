package com.qokoon.shoping;

import com.qokoon.shoping.enums.OfferType;
import java.math.BigDecimal;


public class Offer {

  private String description;
  private OfferType offerType;

  public Offer(String description, OfferType offerType) {
    this.description = description;
    this.offerType = offerType;
  }

  public OfferType getOfferType() {
    return offerType;
  }

  public BigDecimal getItemCountAfterOfferApplied(int count) {
    if (getOfferType().equals(OfferType.BUY2_GET1_FREE)
        && count > 2) {

      return BigDecimal.valueOf(((count * 2) / 3) + (count % 3));

    } else if (getOfferType().equals(OfferType.BUY1_GET_50_PERCENT_DISCOUNT)
        && count > 1) {

      return BigDecimal.valueOf(1.5).add(BigDecimal.valueOf(count % 2));
    }
    return BigDecimal.valueOf(count);
  }
}
