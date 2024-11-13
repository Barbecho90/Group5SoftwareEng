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

public class Server {

	private static final int PORT = 12345;
	 private static volatile boolean isRunning = true;  //its use for close server
	 private static ExecutorService pool = Executors.newCachedThreadPool();
		

	public static void main(String[] args) {
		
		String ipAddress = getLocalIPAddress();
		System.out.println("Server Info: Port: " + PORT + "  IP: " + ipAddress);

		// create serverSocket
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server started. Waiting for connections...");

			// wait for client connections
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client connected.");

				// handle client connection in a new thread
				pool.execute(new ClientHandler(clientSocket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown(); // Gracefully shut down the thread pool
			System.out.println("Server is shutting down.");
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
			try (ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
					ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {

				boolean isLoggedIn = false;

				while (true) {
					// Message to receive for login
					Message message = (Message) inputStream.readObject();
					Account account = message.getAccount();

					// Validate the account credentials
					boolean loginSuccess = accountManager.login(account.getUsername(), account.getPassword());
					message.setStatus(loginSuccess ? "success" : "failure");
					outputStream.writeObject(message);
					outputStream.flush();

					if (loginSuccess) {
						isLoggedIn = true;
					}

					// Receive logout message
					Message logR = (Message) inputStream.readObject();
					logR.setStatus("logout");
					outputStream.writeObject(logR);
					
				
					if ("logout".equalsIgnoreCase(logR.getStatus()) && isLoggedIn) {
						logR.setStatus("logout");
						 // Send back the updated status to the client
						outputStream.writeObject(logR);
						outputStream.flush();
						
						// Reset login status and end the connection
						
						System.out.println("Client requested logout. Ending connection...");
						isLoggedIn=false;
		                return; // End the connection and stop handling the client
		            
					}
					
				}
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("CLient disconnected unexpectedly");
				e.printStackTrace();
			} finally {
				shutdownServer();
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
	    isRunning = false;  // Stop accepting new connections
	    pool.shutdown();  // shut down the thread pool
	    System.out.println("Server shut down.");
	} 
}

