package examen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		HashMap<String, Integer> servers = new HashMap<String, Integer>();
		servers.put("COCO", 1);
		servers.put("AGUACATE", 1);
		LostArk lostArk = new LostArk(5, servers);
		ExecutorService executor = Executors.newCachedThreadPool();
		ArrayList<Future<Boolean>> results = new ArrayList<Future<Boolean>>();
		
		Future<Boolean> result = executor.submit(lostArk);
		
		for (int i = 0; i < 10; i++)
			results.add(executor.submit(new User(lostArk, "COCO")));
		for (int i = 0; i < 10; i++)
			results.add(executor.submit(new User(lostArk, "AGUACATE")));
		
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
