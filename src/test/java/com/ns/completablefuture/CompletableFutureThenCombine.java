package com.ns.completablefuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class CompletableFutureThenCombine {

	public CompletableFuture<String> findFirstName() {

		return CompletableFuture.supplyAsync(() -> {
			sleep(1);
			System.out.println(Thread.currentThread() + " findFirstName");
			return "Niraj";
		});

	}

	public CompletableFuture<String> findLastName() {

		return CompletableFuture.supplyAsync(() -> {
			sleep(1);
			System.out.println(Thread.currentThread() + " findLastName");
			return "Sonawane";
		});
	}

	private void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void completableFutureThenCombine() {

		CompletableFuture<String> thenCombine = findFirstName().thenCombine(findLastName(), (firstName, lastname) -> {
			return firstName + lastname;
		});
		String fullName = thenCombine.join();
		assertEquals("NirajSonawane", fullName);

	}

}
