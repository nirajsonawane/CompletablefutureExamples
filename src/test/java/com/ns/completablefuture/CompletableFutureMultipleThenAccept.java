package com.ns.completablefuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class CompletableFutureMultipleThenAccept {

	
	public int findAccountNumber() {
		System.out.println(Thread.currentThread() + " findAccountNumber");
		sleep(1);
		return 10;
	}

	public int calculateBalance(int accountNumber) {
		System.out.println(Thread.currentThread() + " calculateBalance");
		sleep(1);
		return accountNumber * accountNumber;
	}

	public Integer notifyBalance(Integer balance) {
		System.out.println(Thread.currentThread() + "Sending Notification");
		sleep(1);
		return balance;
	}

	private void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void notify(String str) {
		sleep(1);
		System.out.println(Thread.currentThread() + "Recived vlaue " + str);
	}
	public void notifyByEmail() {
		sleep(1);
		System.out.println(Thread.currentThread() + "Recived vlaue " );
	}

	@Test
	public void comletableFutureMultipleThenApply() {
		CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(this::findAccountNumber)
				.thenApply(this::calculateBalance)
				.thenApply(this::notifyBalance);
		Integer balance = completableFuture.join();
		assertEquals(Integer.valueOf(balance), Integer.valueOf(100));

	}
	
	
	
	@Test
	public void completableFutureThenApplyAccept() {
		 CompletableFuture.supplyAsync(this::findAccountNumber)
				.thenApply(this::calculateBalance)
				.thenApply(this::notifyBalance)
				.thenAccept((i)->notifyByEmail()).join();
		

	}

}
