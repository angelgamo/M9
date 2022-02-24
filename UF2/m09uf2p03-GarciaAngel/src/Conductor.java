
public class Conductor implements Runnable {
	long tempo;
	
	public Conductor(double tempo) {
		super();
		this.tempo = (long)(((60 / tempo) / 16) * 1000);
	}

	@Override
	public void run() {
		try {
			for (;;) {
				synchronized (this) {
					this.notifyAll();
				}
				Thread.sleep(tempo);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
