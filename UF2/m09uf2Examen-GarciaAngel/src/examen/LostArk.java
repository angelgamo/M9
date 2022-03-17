package examen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class LostArk implements Callable<Boolean> {

	class Server {
		public Object server;
		String serverName;
		int serverCapacity;

		public Server(Object server, String serverName, int serverCapacity) {
			this.server = server;
			this.serverName = serverName;
			this.serverCapacity = serverCapacity;
		}
	}

	class ServerList {
		ArrayList<Server> servers = new ArrayList<Server>();

		void add(Server server) {
			servers.add(server);
		}

		Object getServer(String server) {
			for (Server servera : servers) {
				if (servera.serverName.equals(server))
					return servera.server;
			}
			return null;
		}

		int getServerCapacity(String server) {
			for (Server servera : servers) {
				if (servera.serverName.equals(server))
					return servera.serverCapacity;
			}
			return -1;
		}

		void modifyServerCapacity(String server, int val) {
			for (Server servera : servers) {
				if (servera.serverName.equals(server))
					servera.serverCapacity += val;
			}
		}
	}

	private Integer lobbyCapacity;
	public ServerList serverList;

	public LostArk(Integer lobbyCapacity, HashMap<String, Integer> servers) {
		this.lobbyCapacity = lobbyCapacity;

		serverList = new ServerList();
		for (Map.Entry<String, Integer> entry : servers.entrySet()) {
			final Object server = new Object();
			String serverName = entry.getKey();
			int serverCapacity = entry.getValue();
			serverList.add(new Server(server, serverName, serverCapacity));
		}
	}

	@Override
	public Boolean call() {
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			System.out.println("se fini ma vi");
		}
		return true;
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

	public boolean enterServer(String server) {
		synchronized (serverList.getServer(server)) {
			boolean flag = serverList.getServerCapacity(server) > 0;
			if (flag)
				serverList.modifyServerCapacity(server, -1);
			return flag;
		}
	}

	public void exitServer(String server) {
		synchronized (serverList.getServer(server)) {
			serverList.modifyServerCapacity(server, 1);
			serverList.getServer(server).notifyAll();
		}
	}
}
