package m09uf2p01;

public class TimerThread implements Runnable {
	int time;

	public TimerThread(int time) {
		super();
		this.time = time;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Temps incial: " + time);
		try {
			while (time > 0) {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " Temps restant: " + --time);
			}
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " Interruptut amb temps restant: " + time);
		}
	}
}
