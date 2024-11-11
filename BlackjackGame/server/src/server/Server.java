package server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import account.AccountManager;
import model.Account;
import model.Message;
import java.io.*;
import java.net.*;
import java.util.Enumeration;
//import java.util.concurrent.*;

public class Server {

	private static final int PORT = 12345;

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		ExecutorService pool = Executors.newCachedThreadPool();
		String ipAddress = getLocalIPAddress();
		System.out.println("Server Info: Port: " + PORT + "  IP: " + ipAddress);

		// create serverSocket
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server started. Waiting for connections...");

			// wait until client to connect to the socketServer
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected.");

				pool.execute(new ClientHandler(clientSocket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private boolean isLoggedIn = false;
		private final AccountManager accountManager;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
			this.accountManager = AccountManager.getInstance();
		}

		@Override
		public void run() {
			try (ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
					ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {

				while (true) {
					
					//Message to receive for login
					Message message = (Message) inputStream.readObject();  
					Account account = message.getAccount();  	// Extract the account from the message
					boolean loginSuccess = AccountManager.getInstance().login(account.getUsername(), 
							account.getPassword());    // Validate the account credentials (for example, checking username and password)
					
					// Respond to the client
					message.setStatus(loginSuccess ? "success" : "failure");
					outputStream.writeObject(message);
					outputStream.flush();
				}

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getLocalIPAddress() {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					// Check if the address is not loopback and is an IPv4 address
					if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return "127.0.0.1"; // Fallback to localhost if no valid address is found
	}

}
