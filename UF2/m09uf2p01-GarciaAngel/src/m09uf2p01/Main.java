package m09uf2p01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		System.out.println("Main start");
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 10; i++)
			executor.execute(new TimerThread((int) (Math.random() * 9) + 1));
		
		executor.shutdown();
		
		try {
			for (int i = 5; i >= 0; i--) {
				System.out.println("En " + i + " segons, es força l'aturada de tots els threads");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		executor.shutdownNow();
		
		System.out.println("Main ended");
	}
}
