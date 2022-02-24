package Act3;

import java.util.concurrent.Callable;

import Utils.NameMaster;

public class Desirable implements Callable<Boolean> {

	public String name;
	public int acc;
	public Object salute;
	
	public Desirable() {
		name = NameMaster.getInstance().getName();
		salute = new Object();
		acc = 5;
	}

	@Override
	public Boolean call() {
		try {
			Thread.sleep((long) (Math.random() * 50 + 50));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		synchronized (this) {
			this.notifyAll();
		}
		
		for(;;) {
			try {
				synchronized (salute) {
					salute.wait();
					synchronized (this) {
						acc++;
						this.notifyAll();
					}
				}
			} catch (InterruptedException e) {
				return true;
			}
		}
	}

	public String getName() {
		if (acc > 0) {
			acc--;
			return name;
		}
		else
			return null;
	}
}
