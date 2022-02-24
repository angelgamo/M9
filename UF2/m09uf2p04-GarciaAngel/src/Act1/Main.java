package Act1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		Desirable desirable = new Desirable();
		ExecutorService executor = Executors.newFixedThreadPool(5);
		executor.execute(new Eager(desirable));
		executor.execute(new Eager(desirable));
		executor.execute(new Eager(desirable));
		executor.execute(new Eager(desirable));
		executor.execute(new Eager(desirable));
		executor.shutdown();
		
		Thread conductorThread = new Thread(desirable);
		conductorThread.run();
	}
}
