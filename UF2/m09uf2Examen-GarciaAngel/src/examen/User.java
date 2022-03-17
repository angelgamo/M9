package examen;

import java.util.concurrent.Callable;

import Utils.NameMaster;

public class User implements Callable<Boolean> {

	String name;
	LostArk lostArk;
	Object server;
	String lostArkServer;

	public User(LostArk lostArk, String server) {
		name = NameMaster.getInstance().getName();
		this.lostArk = lostArk;
		this.server = lostArk.serverList.getServer(server);
		this.lostArkServer = server;
	}

	@Override
	public Boolean call() {
		for(;;) {
			try {
				if (!lostArk.enterLobby()) {
					do {
						synchronized (lostArk) {
							lostArk.wait();
						}
					} while (!lostArk.enterLobby());
				}
				
				if (!lostArk.enterServer(lostArkServer)) {
					do {
						synchronized (server) {
							server.wait();
						}
					} while (!lostArk.enterServer(lostArkServer));
				}
				
				Thread.sleep(50);
				System.out.println("Yo "+name+" sali de "+lostArkServer+" andate a la verga");
				lostArk.exitServer(lostArkServer);
				lostArk.exitLobby();
				return true;
			} catch (InterruptedException e) {
				
			}
		}
	}
}
