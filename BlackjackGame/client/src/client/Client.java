package client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import abstractMessages.AbstractLogin;
import abstractMessages.AbstractMessage;
import message.DepositMessage;
import message.LoginMessage;
import message.WithdrawMessage;
import model.Account;
import model.Message;

public class Client {

	private static final String SERVER_ADDRESS = "192.168.0.71"; // Carolina's
	private static final int SERVER_PORT = 12345;

	public static void main(String[] args) throws ClassNotFoundException {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the port number to connect: ");
		int port = sc.nextInt();
		//int port = SERVER_PORT;
		System.out.print("Enter the host address to connect:  ");
		String host = sc.next();
		//String host = SERVER_ADDRESS;

		// Client connect to the Server
		try (Socket socket = new Socket(host, port);

				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				Scanner scanner = new Scanner(System.in)) {

			System.out.println("Connected to the Server: " + host);

			System.out.print("Username: ");
			String username = scanner.nextLine();
			//String username = ("user1");
			System.out.print("Password: ");
			String password = scanner.nextLine();
			//String password = ("user1");

			// Send Login Message to Server
			LoginMessage loginMessage = new LoginMessage(username, password);
			outputStream.writeObject(loginMessage);
			outputStream.flush();

			// Receive login response
			Object response = (Object) inputStream.readObject();
			System.out.println("\n Response : " + response);

			// Send deposit Message to Server
			System.out.println("\n Deposit: ");
			DepositMessage deposit = new DepositMessage(100);
			deposit.setUsername(username);
			System.out.println("Send to server");
			outputStream.writeObject(deposit);
			outputStream.flush();

			// Receive deposit response
			response = (Object) inputStream.readObject();
			System.out.println("\n Response : " + response);

			// send withdraw message to Server
			System.out.println("\n Withdraw: \n");
			WithdrawMessage withdraw = new WithdrawMessage(100);
			withdraw.setUsername(username);
			System.out.println("Send to server");
			outputStream.writeObject(withdraw);
			outputStream.flush();

			// Receive Withdraw response
			response = (Object) inputStream.readObject();
			System.out.println("\n Response : " + response);

			// Maintain server connection - will need to attach to a user instance to keep open
			while (true) {
				AbstractMessage message = (AbstractMessage) inputStream.readObject();
				Object msgResponse = message.execute();
				
				System.out.println("msgResponse: " + msgResponse.toString());
			}
			
			
			
		} catch (EOFException e) {
			System.out.println("Server has closed the connection.");

		} catch (Exception e) {

			e.printStackTrace();
		}

		sc.close();
	}
}