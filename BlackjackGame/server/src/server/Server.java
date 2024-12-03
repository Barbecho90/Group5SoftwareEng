package server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import abstractMessages.AbstractMessage;
import account.AccountManager;
import message.LoginMessage;
import model.Account;
import observable.IObserver;
import observable.Observable;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

public class Server extends Observable {
	private static final long serialVersionUID = 1L;
	
	private static final int PORT = 12345;
	private static volatile boolean isRunning = true; // its use for close server
	private static ExecutorService pool = Executors.newCachedThreadPool();
	private static Observable observable;

	public static void main(String[] args) {
		AccountManager.getInstance().loadAccounts();
		String ipAddress = getLocalIPAddress();
		System.out.println("Server Info: Port: " + PORT + "  IP: " + ipAddress);
		observable = new Server();
		
		// create serverSocket
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server started. Waiting for connections...");

			// wait for client connections
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected." + clientSocket.getInetAddress());

				// handle client connection in a new thread
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				pool.execute(clientHandler);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown(); // shut down the thread pool
			System.out.println("Thread Pool is shutting down.");
		}
	}

	static class ClientHandler implements Runnable, IObserver {
		private final Socket clientSocket;
		ObjectOutputStream outputStream;

		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		@Override
		public void run() {
			try {
				ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
				outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

				boolean isLoggedIn = false;

				while (true) {
					AbstractMessage message = (AbstractMessage) inputStream.readObject();

					// Receive Login Message
					if (message instanceof LoginMessage) {
						Account account = (Account) message.execute();

						// If login successful save socket to user
						if (account != null) {
							AccountManager.getInstance().getAccount(message.getUsername()).getUser()
									.setOutputStream(outputStream);
							System.out.println("OutputStream attached to User");
						}

						outputStream.writeObject(account);
						outputStream.flush();
					} else {
						Object account = message.execute();
						// Sending a Response back to client
						outputStream.writeObject(account);
						outputStream.flush();
					}
				}

			} catch (EOFException e) {
				System.out.println("Client disconnected");
			} catch (Exception e) {
				System.out.println("catch");
				System.out.println(e);
			}
				
		}

		@Override
		public void update(AbstractMessage message) {
			sendMessage(message);			
		}
		
		public void sendMessage(AbstractMessage message) {
			try {
				if(outputStream != null) {
					outputStream.writeObject(message);
					outputStream.flush();
				}
			} catch(IOException e) {
				System.err.println("Failed to send message to client: " + e.getMessage());
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