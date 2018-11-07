package me.brucephillips.example.completablefutureexample.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Models a Quote for a product from a shop.
 */
public class Quote {

  private static final Logger LOGGER = LogManager.getLogger(Quote.class.getName());

  private final String shopName;

  private final double price;

  private final Discount.Code discountCode;

  public Quote(String shopName, double price, Discount.Code discountCode) {

    this.shopName = shopName;
    this.price = price;
    this.discountCode = discountCode;

  }

  public static Quote parse(String s) {

    LOGGER.info("Parsing " + s + " into a Quote");

    String[] split = s.split(":");

    String shopName = split[0];

    double price = Double.parseDouble(split[1]);

    Discount.Code discountCode = Discount.Code.valueOf(split[2]);

    Quote aQuote = new Quote(shopName, price, discountCode);

    LOGGER.debug("Quote returned is " + aQuote);

    return aQuote;

  }

  public String getShopName() {
    return shopName;
  }

  public double getPrice() {
    return price;
  }

  public Discount.Code getDiscountCode() {
    return discountCode;
  }

  @Override
  public String toString() {
    return "Quote{" +
            "shopName='" + shopName + '\'' +
            ", price=" + price +
            ", discountCode=" + discountCode +
            '}';
  }
}
