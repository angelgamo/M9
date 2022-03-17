package examen2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		String[] servers = new String[] { "COCO", "AGUACATE" };
		LostArk lostArk = new LostArk(5, 2);
		ExecutorService executor = Executors.newCachedThreadPool();
		ArrayList<Future<Boolean>> results = new ArrayList<Future<Boolean>>();
		
		Future<Boolean> result = executor.submit(lostArk);
		
		for (int i = 0; i < 10; i++)
			results.add(executor.submit(new User(lostArk, 0)));
		for (int i = 0; i < 10; i++)
			results.add(executor.submit(new User(lostArk, 1)));
		
		//Future<Boolean> result = executor.submit(lostArk);
		executor.shutdown();
		
		try {
			for (Future<Boolean> future : results)
				future.get();
			executor.shutdownNow();
			executor.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
	}
}
