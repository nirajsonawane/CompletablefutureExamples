package com.ns.completablefuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

public class CompletableFutureThenRun {
	
	public AtomicInteger someStateVaribale = new AtomicInteger(1);

	public String process() {
		System.out.println(Thread.currentThread() + " Process Method");
		sleep(1);
		return "Some Value";
	}

	private void sleep(Integer i)
	{
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void notifyMe() {
		System.out.println(Thread.currentThread());		
		sleep(1);
		someStateVaribale.set(100);
	}

	
	@Test
	public void completableFutureThenApply() {
		  CompletableFuture.supplyAsync(this::process)
						 .thenRun(this::notifyMe)
						 .join();		
		assertEquals(100,someStateVaribale.get());	
	}

	

}
