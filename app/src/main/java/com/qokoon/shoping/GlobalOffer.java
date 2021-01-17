package com.qokoon.shoping;

import com.qokoon.shoping.enums.OfferType;
import java.math.BigDecimal;


public class GlobalOffer extends Offer {

  private BigDecimal totalShoppingAmount;
  private BigDecimal offerRate;

  public GlobalOffer(String description, OfferType offerType,
      BigDecimal totalShoppingAmount, BigDecimal offerRate) {
    super(description, offerType);
    this.totalShoppingAmount = totalShoppingAmount;
    this.offerRate = offerRate;
  }

  public BigDecimal getTotalShoppingAmount() {
    return totalShoppingAmount;
  }

  public BigDecimal getOfferRate() {
    return offerRate;
  }

  public BigDecimal getInvertedOfferRate() {
    return BigDecimal.valueOf(100.00)
        .subtract(getOfferRate())
        .divide(BigDecimal.valueOf(100));
  }
}
