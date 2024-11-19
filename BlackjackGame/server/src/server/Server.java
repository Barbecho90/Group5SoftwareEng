package server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import abstractMessages.AbstractMessage;
import account.AccountManager;
import model.Account;
import model.Message;
import java.io.*;
import java.net.*;
import java.util.Enumeration;

public class Server {

	private static final int PORT = 12345;
	private static volatile boolean isRunning = true; // its use for close server
	private static ExecutorService pool = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		AccountManager.getInstance().loadAccounts();
		String ipAddress = getLocalIPAddress();
		System.out.println("Server Info: Port: " + PORT + "  IP: " + ipAddress);

		// create serverSocket
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server started. Waiting for connections...");

			// wait for client connections
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected." + clientSocket.getInetAddress());

				// handle client connection in a new thread
				pool.execute(new ClientHandler(clientSocket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown(); // shut down the thread pool
			System.out.println("Thread Pool is shutting down.");
		}
	}

	static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private final AccountManager accountManager;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
			this.accountManager = AccountManager.getInstance();
		}

		@Override
		public void run() {
			try {
				ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
				ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

				boolean isLoggedIn = false;

				while(true) {
					// Receive Login Message
					AbstractMessage message = (AbstractMessage) inputStream.readObject();
					// Validate the account credentials
					Object account = message.execute();
					outputStream.writeObject(account);
					outputStream.flush();
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
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

	public static void shutdownServer() {
		isRunning = false; // Stop accepting new connections
		pool.shutdown(); // shut down the thread pool
		System.out.println("Server shut down.");
	}
}