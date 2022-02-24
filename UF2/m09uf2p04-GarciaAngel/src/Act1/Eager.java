package Act1;

import Utils.NameMaster;

public class Eager implements Runnable {

	String name;
	Desirable desirable;

	public Eager(Desirable desirable) {
		name = NameMaster.getInstance().getName();
		this.desirable = desirable;
	}

	@Override
	public void run() {
		try {
			synchronized (desirable) {
				desirable.wait();
			}
			
			String name = desirable.getName();
			
			synchronized (desirable.salute) {
				desirable.salute.notify();
			}
			
			if (name !=null) {
				System.out.println(this.name + " saluda a " + name);
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
