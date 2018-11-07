package me.brucephillips.example.completablefutureexample.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

import static me.brucephillips.example.completablefutureexample.utility.Util.delay;
import static me.brucephillips.example.completablefutureexample.utility.Util.format;

/**
 * Models a Shop that can provide product prices.
 */
public class Shop {

  private static final Logger LOGGER = LogManager.getLogger(Shop.class.getName());

  private final String shopName;

  private final Random productPrice;

  public Shop(String shopName) {

    this.shopName = shopName;

    //Create a random price for the product
    productPrice = new Random(shopName.charAt(0) * shopName.charAt(1) * shopName.charAt(2));

  }

  public String getPrice(String product, Shop shop) {

    LOGGER.debug("Getting price for " + product + " at " + shop);

    double price = calculatePrice(product);

    int randomInt = new Random().nextInt(Discount.Code.values().length);

    LOGGER.debug("Discount code randomInt is " + randomInt);

    Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];



    String priceStr = shopName + ":" + price + ":" + code;

    LOGGER.debug( priceStr );

    return priceStr;
  }

  public double calculatePrice(String product) {

    LOGGER.info("About to call delay...");

    delay();

    return format(productPrice.nextDouble() * product.charAt(0) + product.charAt(1));
  }

  public String getShopName() {
    return shopName;
  }

  @Override
  public String toString() {
    return "Shop{" +
            "shopName='" + shopName + '\'' +
            ", productPrice=" + productPrice.nextDouble() +
            '}';
  }
}
