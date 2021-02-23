package com.qokoon.shoping;

import static org.junit.Assert.assertThat;

import com.qokoon.shoping.enums.OfferType;
import java.math.BigDecimal;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class ShoppingCartTest {

  private static final String DOVE_SOAP = "Dove Soap";
  private static final String AXE_DEO = "Axe Deo";
  private static final long DOVE_SOAP_ID = 1;
  private static final long AXE_DEO_ID = 2;

  @Test
  public void step_1() {
    //Given
    BigDecimal soapPrice = BigDecimal.valueOf(39.99);
    Product product = new Product(DOVE_SOAP_ID, DOVE_SOAP, soapPrice, null, null);

    ShoppingCart shoppingCart = new ShoppingCart();

    //When
    ShoppingItem shoppingItem = new ShoppingItem(5, product);
    shoppingCart.addShoppingItem(shoppingItem);

    shoppingCart.calculateShoppingCart();

    //Then
    assertThat(shoppingCart.getTotalPrice(), CoreMatchers.equalTo(BigDecimal.valueOf(199.95)));
    assertThat(shoppingCart.getShoppingItems().size(), CoreMatchers.equalTo(1));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getCount(),
        CoreMatchers.equalTo(5));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getPrice(),
        CoreMatchers.equalTo(soapPrice));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getDescription(),
        CoreMatchers.equalTo(DOVE_SOAP));
  }

  @Test
  public void step_2() {
    //Given
    BigDecimal soapPrice = BigDecimal.valueOf(39.99);
    Product soapProduct = new Product(DOVE_SOAP_ID, DOVE_SOAP, soapPrice, null, null);

    ShoppingCart shoppingCart = new ShoppingCart();

    //When
    ShoppingItem shoppingItem1 = new ShoppingItem(5, soapProduct);
    shoppingCart.addShoppingItem(shoppingItem1);
    ShoppingItem shoppingItem2 = new ShoppingItem(3, soapProduct);
    shoppingCart.addShoppingItem(shoppingItem2);

    shoppingCart.calculateShoppingCart();

    //Then
    assertThat(shoppingCart.getTotalPrice(), CoreMatchers.equalTo(BigDecimal.valueOf(319.92)));
    assertThat(shoppingCart.getShoppingItems().size(), CoreMatchers.equalTo(1));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getCount(),
        CoreMatchers.equalTo(8));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getPrice(),
        CoreMatchers.equalTo(soapPrice));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getDescription(),
        CoreMatchers.equalTo(DOVE_SOAP));
  }

  @Test
  public void step_3() {
    //Given
    BigDecimal soapPrice = BigDecimal.valueOf(39.99);
    BigDecimal axePrice = BigDecimal.valueOf(99.99);
    BigDecimal taxRate = BigDecimal.valueOf(12.5);
    Product soapProduct = new Product(DOVE_SOAP_ID, DOVE_SOAP, soapPrice, taxRate, null);
    Product axeProduct = new Product(AXE_DEO_ID, AXE_DEO, axePrice, taxRate, null);

    ShoppingCart shoppingCart = new ShoppingCart();

    //When

    ShoppingItem shoppingItem1 = new ShoppingItem(2, soapProduct);
    shoppingCart.addShoppingItem(shoppingItem1);
    ShoppingItem shoppingItem2 = new ShoppingItem(2, axeProduct);
    shoppingCart.addShoppingItem(shoppingItem2);

    shoppingCart.calculateShoppingCart();

    //Then
    assertThat(shoppingCart.getTotalPrice(), CoreMatchers.equalTo(BigDecimal.valueOf(314.96)));
    assertThat(shoppingCart.getTotalTax(),
        CoreMatchers.equalTo(BigDecimal.valueOf(35.00).setScale(2)));
    assertThat(shoppingCart.getShoppingItems().size(), CoreMatchers.equalTo(2));

    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getCount(),
        CoreMatchers.equalTo(2));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getPrice(),
        CoreMatchers.equalTo(soapPrice));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getDescription(),
        CoreMatchers.equalTo(DOVE_SOAP));

    assertThat(shoppingCart.getShoppingItems().get(AXE_DEO_ID).getCount(), CoreMatchers.equalTo(2));
    assertThat(shoppingCart.getShoppingItems().get(AXE_DEO_ID).getProduct().getPrice(),
        CoreMatchers.equalTo(axePrice));
    assertThat(shoppingCart.getShoppingItems().get(AXE_DEO_ID).getProduct().getDescription(),
        CoreMatchers.equalTo(AXE_DEO));
  }


  @Test
  public void step_4_1() {
    //Given
    BigDecimal soapPrice = BigDecimal.valueOf(39.99);
    BigDecimal taxRate = BigDecimal.valueOf(12.5);

    Offer offerBuy2Get1Free = new Offer("Buy 2, Get 1 free", OfferType.BUY2_GET1_FREE);

    Product soapProduct = new Product(DOVE_SOAP_ID, DOVE_SOAP, soapPrice, taxRate,
        offerBuy2Get1Free);

    ShoppingCart shoppingCart = new ShoppingCart();

    //When
    ShoppingItem shoppingItem1 = new ShoppingItem(3, soapProduct);
    shoppingCart.addShoppingItem(shoppingItem1);

    shoppingCart.calculateShoppingCart();

    //Then
    assertThat(shoppingCart.getTotalPrice(), CoreMatchers.equalTo(BigDecimal.valueOf(89.98)));
    assertThat(shoppingCart.getShoppingItems().size(), CoreMatchers.equalTo(1));
    assertThat(shoppingCart.getTotalDiscount(), CoreMatchers.equalTo(BigDecimal.valueOf(39.99)));
    assertThat(shoppingCart.getTotalTax(),
        CoreMatchers.equalTo(BigDecimal.valueOf(10.00).setScale(2)));

    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getCount(),
        CoreMatchers.equalTo(3));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getPrice(),
        CoreMatchers.equalTo(soapPrice));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getDescription(),
        CoreMatchers.equalTo(DOVE_SOAP));

  }


  @Test
  public void step_4_2() {
    //Given
    BigDecimal soapPrice = BigDecimal.valueOf(39.99);
    BigDecimal axePrice = BigDecimal.valueOf(89.99);
    BigDecimal taxRate = BigDecimal.valueOf(12.5);

    Offer offerBuy2Get1Free = new Offer("Buy 2, Get 1 free", OfferType.BUY2_GET1_FREE);

    Product soapProduct = new Product(DOVE_SOAP_ID, DOVE_SOAP, soapPrice, taxRate,
        offerBuy2Get1Free);
    Product axeProduct = new Product(AXE_DEO_ID, AXE_DEO, axePrice, taxRate, null);

    ShoppingCart shoppingCart = new ShoppingCart();

    //When

    ShoppingItem shoppingItem1 = new ShoppingItem(3, soapProduct);
    shoppingCart.addShoppingItem(shoppingItem1);
    ShoppingItem shoppingItem2 = new ShoppingItem(2, axeProduct);
    shoppingCart.addShoppingItem(shoppingItem2);

    shoppingCart.calculateShoppingCart();

    //Then
    assertThat(shoppingCart.getTotalPrice(), CoreMatchers.equalTo(BigDecimal.valueOf(292.46)));
    assertThat(shoppingCart.getShoppingItems().size(), CoreMatchers.equalTo(2));
    assertThat(shoppingCart.getTotalDiscount(), CoreMatchers.equalTo(BigDecimal.valueOf(39.99)));
    assertThat(shoppingCart.getTotalTax(),
        CoreMatchers.equalTo(BigDecimal.valueOf(32.50).setScale(2)));

    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getCount(),
        CoreMatchers.equalTo(3));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getPrice(),
        CoreMatchers.equalTo(soapPrice));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getDescription(),
        CoreMatchers.equalTo(DOVE_SOAP));

    assertThat(shoppingCart.getShoppingItems().get(AXE_DEO_ID).getCount(), CoreMatchers.equalTo(2));
    assertThat(shoppingCart.getShoppingItems().get(AXE_DEO_ID).getProduct().getPrice(),
        CoreMatchers.equalTo(axePrice));
    assertThat(shoppingCart.getShoppingItems().get(AXE_DEO_ID).getProduct().getDescription(),
        CoreMatchers.equalTo(AXE_DEO));

  }

  @Test
  public void step_5() {
    //Given
    BigDecimal soapPrice = BigDecimal.valueOf(39.99);
    BigDecimal taxRate = BigDecimal.valueOf(12.5);

    Offer offerBuy1GetPercent50Discount = new Offer("Buy 1, Get 50% discount on next one",
        OfferType.BUY1_GET_50_PERCENT_DISCOUNT);

    Product soapProduct = new Product(DOVE_SOAP_ID, DOVE_SOAP, soapPrice, taxRate,
        offerBuy1GetPercent50Discount);

    ShoppingCart shoppingCart = new ShoppingCart();

    //When
    ShoppingItem shoppingItem1 = new ShoppingItem(2, soapProduct);
    shoppingCart.addShoppingItem(shoppingItem1);

    shoppingCart.calculateShoppingCart();

    //Then
    assertThat(shoppingCart.getTotalPrice(), CoreMatchers.equalTo(BigDecimal.valueOf(67.48)));
    assertThat(shoppingCart.getShoppingItems().size(), CoreMatchers.equalTo(1));
    assertThat(shoppingCart.getTotalDiscount(),
        CoreMatchers.equalTo(BigDecimal.valueOf(20.00).setScale(2)));
    assertThat(shoppingCart.getTotalTax(),
        CoreMatchers.equalTo(BigDecimal.valueOf(7.50).setScale(2)));

    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getCount(),
        CoreMatchers.equalTo(2));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getPrice(),
        CoreMatchers.equalTo(soapPrice));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getDescription(),
        CoreMatchers.equalTo(DOVE_SOAP));

  }

  @Test
  public void step_6() {
    //Given
    BigDecimal soapPrice = BigDecimal.valueOf(39.99);
    BigDecimal axePrice = BigDecimal.valueOf(89.99);
    BigDecimal taxRate = BigDecimal.valueOf(12.5);

    Product soapProduct = new Product(DOVE_SOAP_ID, DOVE_SOAP, soapPrice, taxRate, null);
    Product axeProduct = new Product(AXE_DEO_ID, AXE_DEO, axePrice, taxRate, null);

    ShoppingCart shoppingCart = new ShoppingCart();

    GlobalOffer globalOffer = new GlobalOffer(
        "20% discount if the cart total is greater than or equal to 500"
        , OfferType.ACCORDING_TO_TOTAL_AMOUNT_20_PERCENT_DISCOUNT
        , BigDecimal.valueOf(500.00)
        , BigDecimal.valueOf(20.00));

    shoppingCart.setGlobalOffer(globalOffer);

    //When
    ShoppingItem shoppingItem1 = new ShoppingItem(5, soapProduct);
    shoppingCart.addShoppingItem(shoppingItem1);
    ShoppingItem shoppingItem2 = new ShoppingItem(4, axeProduct);
    shoppingCart.addShoppingItem(shoppingItem2);

    shoppingCart.calculateShoppingCart();

    //Then
    assertThat(shoppingCart.getTotalPrice(), CoreMatchers.equalTo(BigDecimal.valueOf(503.92)));
    assertThat(shoppingCart.getTotalTax(), CoreMatchers.equalTo(BigDecimal.valueOf(55.99)));
    assertThat(shoppingCart.getTotalDiscount(), CoreMatchers.equalTo(BigDecimal.valueOf(111.98)));
    assertThat(shoppingCart.getShoppingItems().size(), CoreMatchers.equalTo(2));

    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getCount(),
        CoreMatchers.equalTo(5));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getPrice(),
        CoreMatchers.equalTo(soapPrice));
    assertThat(shoppingCart.getShoppingItems().get(DOVE_SOAP_ID).getProduct().getDescription(),
        CoreMatchers.equalTo(DOVE_SOAP));

    assertThat(shoppingCart.getShoppingItems().get(AXE_DEO_ID).getCount(), CoreMatchers.equalTo(4));
    assertThat(shoppingCart.getShoppingItems().get(AXE_DEO_ID).getProduct().getPrice(),
        CoreMatchers.equalTo(axePrice));
    assertThat(shoppingCart.getShoppingItems().get(AXE_DEO_ID).getProduct().getDescription(),
        CoreMatchers.equalTo(AXE_DEO));
  }

}
