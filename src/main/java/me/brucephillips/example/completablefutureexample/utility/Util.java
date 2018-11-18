package me.brucephillips.example.completablefutureexample.utility;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Provides utility methods.
 */
public class Util {

  private static final Random RANDOM = new Random(0);

  private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

  /*
  Create a delay to simulate a long running task.
   */
  public static void delay() {

    int delay = 10000;


    try {

      Thread.sleep(delay);

    } catch (InterruptedException e) {

      throw new RuntimeException(e);

    }

  }

  public static double format(double number) {

    synchronized (formatter) {

      return new Double(formatter.format(number));

    }

  }

  public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {

    return CompletableFuture.supplyAsync(() -> futures.stream()
        .map(future -> future.join())
        .collect(Collectors.<T>toList()));
  }

}
