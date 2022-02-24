package Act2;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		Desirable desirable = new Desirable();
		int threads = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(threads);
		ArrayList<Future<Boolean>> results = new ArrayList<Future<Boolean>>();

		for (int i = 0; i < 5; i++)
			results.add(executor.submit(new Eager(desirable)));

		executor.submit(desirable);
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
