package Act4;

import java.util.concurrent.Callable;

import Utils.NameMaster;

public class Eager implements Callable<Boolean> {

	String name;
	Desirable desirable;

	public Eager(Desirable desirable) {
		name = NameMaster.getInstance().getName();
		this.desirable = desirable;
	}

	@Override
	public Boolean call() {
		for(;;) {
			try {
				synchronized (desirable) {
					desirable.wait();
				}
				
				String name = desirable.getName(this);
				
				if (name !=null) {
					System.out.println(this.name + " saluda a " + name);
					Thread.sleep(50);
					synchronized (desirable.salute) {
						desirable.salute.notify();
					}
				}
				
			} catch (InterruptedException e) {
				return false;
			}
		}
	}
}
