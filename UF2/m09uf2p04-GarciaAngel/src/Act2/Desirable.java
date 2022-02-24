package Act2;

import java.util.concurrent.Callable;

import Utils.NameMaster;

public class Desirable implements Callable<Boolean> {

	public String name;
	public boolean ready;
	public Object salute;
	
	public Desirable() {
		name = NameMaster.getInstance().getName();
		salute = new Object();
		ready = true;
	}

	@Override
	public Boolean call() {
		for(;;) {
			try {
				Thread.sleep((long) (Math.random() * 50 + 50));
				synchronized (this) {
					this.notifyAll();
				}
				
				synchronized (salute) {
					salute.wait();
					ready = true;
				}
			} catch (InterruptedException e) {
				return true;
			}
		}
	}

	public String getName() {
		if (ready) {
			ready = false;
			return name;
		}
		else
			return null;
	}
}
