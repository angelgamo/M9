package Act1;

import Utils.NameMaster;

public class Desirable implements Runnable {

	public String name;
	public Object salute;
	
	public Desirable() {
		name = NameMaster.getInstance().getName();
		salute = new Object();
	}

	@Override
	public void run() {
		try {
			Thread.sleep((long) (Math.random() * 50 + 50));
			synchronized (this) {
				this.notifyAll();
			}
			
			synchronized (salute) {
				salute.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		String tmp = name;
		name = null;
		return tmp;
	}
}
