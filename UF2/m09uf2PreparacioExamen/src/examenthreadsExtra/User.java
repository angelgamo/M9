package examenthreadsExtra;

import java.util.concurrent.Callable;

import Utils.NameMaster;

public class User implements Callable<Boolean> {

	String name;
	LostArk lostArk;
	Object server;
	String lostArkServer;

	int regneIntents;
	int serverIntents;

	public User(LostArk lostArk, String server) {
		name = NameMaster.getInstance().getName();
		this.lostArk = lostArk;
		this.server = lostArk.serverList.getServer(server);
		this.lostArkServer = server;
		this.regneIntents = 3;
		this.serverIntents = 7;
	}

	@Override
	public Boolean call() {
		try {
			if (!lostArk.enterLobby()) {
				do {
					if (--regneIntents <= 0) {
						System.out.println(name + " me voy a tomar por culo");
						return false;
					}
					System.out.println(name + " No puc ni entrar al joc!");
					synchronized (lostArk) {
						lostArk.wait();
					}
				} while (!lostArk.enterLobby());
			}

			if (!lostArk.enterServer(lostArkServer)) {
				do {
					if (--serverIntents <= 0) {
						System.out.println(name + " voy a desinstalar el juego, vaya servidor el " + lostArkServer);
						lostArk.exitLobby();
						return false;
					}
					System.out.println(name + " Un altre cop cua a " + lostArkServer + "?!");
					synchronized (server) {
						server.wait();
					}
				} while (!lostArk.enterServer(lostArkServer));
			}

			System.out.println(name + " Per fi jugant a " + lostArkServer);
			Thread.sleep(100);
			System.out.println(name + " Ja prou per avui, deixant plaça lliure a " + lostArkServer);
			lostArk.exitServer(lostArkServer);
			lostArk.exitLobby();
			return true;
		} catch (InterruptedException e) {
			System.out.println(name+" Peores servidores que Reddit");
		}
		return false;
	}
}
