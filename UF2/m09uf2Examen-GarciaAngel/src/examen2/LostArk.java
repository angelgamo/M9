package examen2;

import java.util.concurrent.Callable;

public class LostArk implements Callable<Boolean> {

	private Integer lobbyCapacity;
	public Object serverOne;
	public Object serverTwo;
	public int serverOneCapacity;
	public int serverTwoCapacity;

	public LostArk(int lobbyCapacity, int serverCapacity) {
		this.lobbyCapacity = lobbyCapacity;
		serverOne = new Object();
		serverTwo = new Object();
		serverOneCapacity = serverCapacity;
		serverTwoCapacity = serverCapacity;
	}

	@Override
	public Boolean call() {
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			System.out.println("se fini ma vi puta");
		}
		return true;
	}
	
	Object getServer(int i) {
		if (i == 0)
			return serverOne;
		return serverTwo;
	}

	public synchronized boolean enterLobby() {
		boolean flag = lobbyCapacity > 0;
		if (flag)
			lobbyCapacity--;
		return flag;
	}

	public synchronized void exitLobby() {
		lobbyCapacity++;
		this.notifyAll();
	}

	public boolean enterServer(int i) {
		if (i == 0) {
			synchronized (serverOne) {
				boolean flag = serverOneCapacity > 0;
				if (flag)
					serverOneCapacity--;
				return flag;
			}
		}else {
			synchronized (serverTwo) {
				boolean flag = serverTwoCapacity > 0;
				if (flag)
					serverTwoCapacity--;
				return flag;
			}
		}
	}

	public void exitServer(int i) {
		if (i == 0) {
			synchronized (serverOne) {
				serverOneCapacity++;
				serverOne.notifyAll();
			}
		}else {
			synchronized (serverTwo) {
				serverTwoCapacity++;
				serverTwo.notifyAll();
			}
		}
	}
}
