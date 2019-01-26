package com.ns.completablefuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

public class SimpleCompletableFutureTest {

	public AtomicInteger someStateVaribale = new AtomicInteger(1);

	@Test
	void simpleCompletableFuture() {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		completableFuture.complete("Some Value");
		assertTrue(completableFuture.isDone());
		try {
			assertEquals("Some Value", completableFuture.get());
		} catch (ExecutionException | InterruptedException e) {
			fail("No Exception expected");
		}
	}

	public String processSomeData() {
		System.out.println(Thread.currentThread() + " Processing Some Data");
		return "Some Value";
	}

	@Test
	void completableFutureSupplyAsync() {
		CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(this::processSomeData);
		try {
			assertEquals("Some Value", supplyAsync.get());
		} catch (ExecutionException | InterruptedException e) {
			fail("No Exception expected");
		}

	}

	public void process() {
		System.out.println(Thread.currentThread() + " Process");
		someStateVaribale.set(100);

	}

	@Test
	void completableFutureRunAsync() {
		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> process());
		runAsync.join();
		assertEquals(100, someStateVaribale.get());

	}
}
