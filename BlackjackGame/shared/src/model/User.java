package model;

import java.net.Socket;

public abstract class User {
	private Socket userSocket;
	
	public abstract void login();
	
	public Socket getSocket() {
		return userSocket;
	}
	
	public void setSocket(Socket socket) {
		userSocket = socket;
	}
}
