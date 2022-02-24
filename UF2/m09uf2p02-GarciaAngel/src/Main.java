import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random r = new Random();

		for (int i = 0; i < 100000000; i++) {
			list.add(r.nextInt());
		}
		
		System.out.println("Ejercicio 1");
		Ejercicio1(list);
		System.out.println("\nEjercicio 2");
		Ejercicio2(list);
		System.out.println("\nEjercicio 3");
		Ejercicio3(list);
		

	}

	public static void Ejercicio1(ArrayList<Integer> list) {
		double startTime = System.nanoTime();

		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Result> result = executor.submit(new GetMinMax(list));
		executor.shutdown();

		try {
			System.out.println("Result min: " + result.get().min + " max: " + result.get().max);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("Execution Time: " + (System.nanoTime() - startTime) / 1000000 + " miliseconds");
	}
	
	public static void Ejercicio2(ArrayList<Integer> list) {
		ArrayList<Integer> results = new ArrayList<Integer>();
		ArrayList<GetMinMax> calls = new ArrayList<GetMinMax>();
		double startTime = System.nanoTime();

		ExecutorService executor = Executors.newFixedThreadPool(2);
	
		calls.add(new GetMinMax(list.subList(0, list.size()/2)));
		calls.add(new GetMinMax(list.subList(list.size()/2, list.size())));
		
		for (GetMinMax result : calls) {
			results.add(result.result.min);
			results.add(result.result.max);
		}
		
		Future<Result> result = executor.submit(new GetMinMax(list));
		
		executor.shutdown();

		try {
			System.out.println("Result min: " + result.get().min + " max: " + result.get().max);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("Execution Time: " + (System.nanoTime() - startTime) / 1000000 + " miliseconds");
	}
	
	public static void Ejercicio3(ArrayList<Integer> list) {
		ArrayList<Integer> results = new ArrayList<Integer>();
		ArrayList<GetMinMax> calls = new ArrayList<GetMinMax>();
		int threads = Runtime.getRuntime().availableProcessors();
		double startTime = System.nanoTime();

		ExecutorService executor = Executors.newFixedThreadPool(threads);
	
		ArrayList<List<Integer>> sublists = ChunkList(list, threads);
		
		for (int i = 0; i < threads; i++) 
			calls.add(new GetMinMax(sublists.get(i)));
		
		for (GetMinMax result : calls) {
			results.add(result.result.min);
			results.add(result.result.max);
		}
		
		Future<Result> result = executor.submit(new GetMinMax(list));
		
		executor.shutdown();

		try {
			System.out.println("Result min: " + result.get().min + " max: " + result.get().max);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("Execution Time: " + (System.nanoTime() - startTime) / 1000000 + " miliseconds");
	}
	
	public static ArrayList<List<Integer>> ChunkList(ArrayList<Integer> list, int chunks){
		ArrayList<List<Integer>> sublists = new ArrayList<List<Integer>>();
		int size = list.size() / chunks;
		
		for (int i = 0; i < chunks; i++) 
			if (i == chunks - 1)
				sublists.add(list.subList(size * i, list.size()));
			else
				sublists.add(list.subList(size * i, size * (i + 1)));
		
		return sublists;
	}
}