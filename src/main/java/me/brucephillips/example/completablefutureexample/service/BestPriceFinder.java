package me.brucephillips.example.completablefutureexample.service;

import me.brucephillips.example.completablefutureexample.model.Discount;
import me.brucephillips.example.completablefutureexample.model.Quote;
import me.brucephillips.example.completablefutureexample.model.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides a service to find the Best
 * price from a collection of Shops.
 */
public class BestPriceFinder {

  private final List<Shop> shops = Arrays.asList(
      new Shop("BestPrice"),
      new Shop("LetsSaveBig"),
      new Shop("MyFavoriteShop"),
      new Shop("BuyItAll"),
      new Shop("ShopEasy"));

  /*
  Create a thread pool with the number of threads that match the number of Shops.
  so that getting the prices from a shop will be run on its own thread.
   */
  private final Executor executor = Executors.newFixedThreadPool( shops.size(), (Runnable r) -> {
    Thread t = new Thread(r);
    t.setDaemon(true);
    return t;
  });


  /**
   * Create a collection of Strings that show the
   * the shop, product, price (after applying discount)
   * for all the shops.
   * @param product - product to find the prices for
   * @return - Collection of Strings
   */
  public List<String> findPricesFuture(String product) {



    List<CompletableFuture<String>> priceFutures = findPricesStream(product)
        .collect(Collectors.<CompletableFuture<String>>toList());

    return priceFutures.stream()
        .map(CompletableFuture::join)
        .collect(Collectors.toList());

  }

  /**
   * Create a Stream of CompletableFuture objects.
   * @param product
   * @return
   */
  public Stream<CompletableFuture<String>> findPricesStream(String product) {

    /*
    Stream over the collection of shops
    For each shop get the initial price
    Then parse the String returned by getPrice
    into a Quote object
    Then apply the discount to the Quote
     */
    return shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product, shop), executor))
        .map(future -> future.thenApply(Quote::parse))
        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
  }



}
