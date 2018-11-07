Completable Future Example

This example is based on Chapter 16 of Modern Java In Action (Urma)

The project shows how to use the CompletableFuture class
(https://docs.oracle.com/javase/10/docs/api/java/util/concurrent/CompletableFuture.html)
to run requests to find the best price for a product from
multiple shops in parallel.

The project introduces two artificial 2 sec delays (one when getting the price from a shop
and one when getting the discount to apply) - these delays simulate calling processes that may
run long and that we don't want to have the entire program wait for.

If we processed the requests to the five shops sequentially the total time would
be over 20 seconds (4 seconds per request).

By processing the requests in parallel using the CompletableFutures class it takes
only 4 seconds.