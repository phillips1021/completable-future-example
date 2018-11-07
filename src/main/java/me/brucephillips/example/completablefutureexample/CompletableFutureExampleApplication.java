package me.brucephillips.example.completablefutureexample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import me.brucephillips.example.completablefutureexample.service.BestPriceFinder;

import java.util.List;
import java.util.function.Supplier;


@SpringBootApplication
public class CompletableFutureExampleApplication {

	private static final Logger LOGGER = LogManager.getLogger(CompletableFutureExampleApplication.class.getName());

	private static BestPriceFinder bestPriceFinder = new BestPriceFinder();


	public static void main(String[] args) {


		SpringApplication.run(CompletableFutureExampleApplication.class, args);


	}

	@Bean
	public CommandLineRunner app() {


		return args -> { run(); };

	}

	private void run() {

		LOGGER.info("CompletableFutureExampleApplication started ...");

		execute("Completable Future Example", () -> bestPriceFinder.findPricesFuture("myPhone27S"));

		LOGGER.info("CompletableFutureExampleApplicationFinished.");

	}

	private static void execute(String msg, Supplier<List<String>> s) {

		long start = System.nanoTime();

		LOGGER.info("Prices found: " + s.get());

		long duration = (System.nanoTime() - start) / 1_000_000;

		LOGGER.debug(msg + " done in " + duration + " msecs");

	}
}
