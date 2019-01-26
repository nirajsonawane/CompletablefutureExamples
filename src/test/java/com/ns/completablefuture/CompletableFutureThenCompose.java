package com.ns.completablefuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class CompletableFutureThenCompose {

	
	public CompletableFuture<Integer> findAccountNumber() {
		sleep(1);
		System.out.println(Thread.currentThread() + " findAccountNumber");
		return CompletableFuture.completedFuture(10);
	}

	public CompletableFuture<Integer> calculateBalance(int accountNumber) {
		System.out.println(Thread.currentThread() + " calculateBalance");
		sleep(1);		
		return CompletableFuture.completedFuture(accountNumber * accountNumber);
	}

	public CompletableFuture<Integer> notifyBalance(Integer balance) {
		System.out.println(Thread.currentThread() + "Sending Notification");
		sleep(1);		
		return CompletableFuture.completedFuture(balance);
		
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
	public void completableFutureThenCompose()
	{
    
		Integer join = findAccountNumber()
		.thenComposeAsync(this::calculateBalance)
		.thenCompose(this::notifyBalance)
		.join();
		assertEquals(new Integer(100), join);
	
	}
	
}
