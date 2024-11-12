package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import model.Account;
import model.Message;

public class Client {

	private static final String SERVER_ADDRESS = "192.168.0.71"; // Carolina's IP Desktop
	private static final int SERVER_PORT = 12345; // port used in the server

	public static void main(String[] args) throws ClassNotFoundException {

//		Scanner sc = new Scanner(System.in);
//		System.out.print("Enter the port number to connect: ");
//		int port = sc.nextInt();
//		System.out.print("Enter the host address to connect:  ");
//		String host = sc.next();
//

		try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				Scanner scanner = new Scanner(System.in)) {

			System.out.println("Connected to the Server ");
			System.out.print("Username: ");
			//String username = scanner.nextLine();
			String username = ("user1");
			System.out.print("Password: ");
			//String password = scanner.nextLine();
			String password = ("user1");

			Account account = new Account(username, password, null);

			// Create a Message login
			Message loginMessage = new Message(account);
			outputStream.writeObject(loginMessage);
			outputStream.flush();

			// Receive login response
			Message response = (Message) inputStream.readObject();
			if ("success".equals(response.getStatus())) {
				System.out.println("Login successful.");

				// Loop for sending messages when Client Logged in
				while (true) {
					System.out.print("Enter 'logout' to disconnect:   ");
					String userInputs = scanner.nextLine();

					// Client enter logout and we send a message to Server
					if ("logout".equalsIgnoreCase(userInputs)) {

						// Sending logout message
						Message logoutMessage = new Message("logout");
						outputStream.writeObject(logoutMessage);
						outputStream.flush();

						// Receive logout confirmation
						Message logoutResponse = (Message) inputStream.readObject();
						if ("logout".equals(logoutResponse.getStatus())) {
							System.out.println("Logout successful.");
							break;
						}
					}
				}
			} else {
				System.out.println("Login failed.");
			}

		} catch (EOFException e) {
			System.out.println("Server has closed the connection.");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}