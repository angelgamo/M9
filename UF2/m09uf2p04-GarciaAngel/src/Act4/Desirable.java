package Act4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Callable;

import Utils.NameMaster;

public class Desirable implements Callable<Boolean> {

	public String name;
	public boolean ready;
	public HashMap<Object, Integer> getters;
	public boolean finish;
	public Object salute;
	
	public Desirable() {
		name = NameMaster.getInstance().getName();
		salute = new Object();
		ready = true;
		finish = false;
		getters = new HashMap<Object, Integer>();
	}

	@Override
	public Boolean call() {
		for(;;) {
			try {
				Thread.sleep((long) (Math.random() * 50 + 50));
				synchronized (this) {
					if (finish)
						return true;
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

	public String getName(Object getter) {
		if (ready) {
			ready = false;
			if (getters.containsKey(getter)) {
				int val = getters.get(getter);
				if (++val >= 3)
					finish = true;
				getters.put(getter, val);
			}
			else
				getters.put(getter, 1);
			return name + " " + getters.get(getter) + " veces";
		}
		else
			return null;
	}
}
