package examenthreadsExtra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		HashMap<String, Integer> servers = new HashMap<String, Integer>();
		servers.put("Zinnervale", 10);
		servers.put("Kadan", 10);
		servers.put("Nineveh", 10);
		LostArk lostArk = new LostArk(40, servers);
		ExecutorService executor = Executors.newCachedThreadPool();
		ArrayList<Future<Boolean>> results = new ArrayList<Future<Boolean>>();
		
		Future<Boolean> result = executor.submit(lostArk);
		ArrayList<User> jugadors = new ArrayList<User>();
		
		for (int i = 0; i < 25; i++) {
			jugadors.add(new User(lostArk, "Zinnervale"));
			jugadors.add(new User(lostArk, "Kadan"));
		}
		for (int i = 0; i < 10; i++)
			jugadors.add(new User(lostArk, "Nineveh"));
		
		Collections.shuffle(jugadors);
		for (User user : jugadors)
			results.add(executor.submit(user));
		
		//Future<Boolean> result = executor.submit(lostArk);
		executor.shutdown();
		
		try {	
			result.get();
			executor.shutdownNow();
			executor.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
	}
}
