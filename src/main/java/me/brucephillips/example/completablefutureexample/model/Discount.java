package me.brucephillips.example.completablefutureexample.model;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static me.brucephillips.example.completablefutureexample.utility.Util.delay;
import static me.brucephillips.example.completablefutureexample.utility.Util.format;

/**
 * Models different Discount levels.
 */
public class Discount {

  private static final Logger LOGGER = LogManager.getLogger(Discount.class.getName());

  public enum Code {

    NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

    private final int percentage;

    Code(int percentage) {

      this.percentage = percentage;

    }

  }

  /**
   * Calculate the Discount and return a String with the
   * discounted price.
   * @param quote - Quote
   * @return - String that includes price
   */
  public static String applyDiscount(Quote quote) {

    LOGGER.info("About to apply discount to " + quote);

    return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode() ) + "\n";

  }

  private static double apply(double price, Code code) {


    LOGGER.info("About to call delay in applying discount...");

    delay();

    return format(price * (100 - code.percentage) / 100);

  }

}
