package client;

import java.io.*;
import java.net.*;
import javax.swing.*;

import message.LoginMessage;

import java.awt.*;

public class ClientGui extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField hostField;
	private JTextField portField;
	private JTextArea responseArea;
	private JTextField usernameField;
	private JPasswordField passwordField;

	private static final String SERVER_ADDRESS = "192.168.0.71"; // Default
	private static final int SERVER_PORT = 12345;
	private Socket socket;

	// Constructor
	public ClientGui() {

		setTitle("Connect to Server");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Center the window

		setLayout(new GridLayout(5, 2));

		// Host field
		add(new Label("Server Address:"));
		hostField = new JTextField("");
		add(hostField);

		// Port field
		add(new JLabel("Port:"));
		portField = new JTextField(String.valueOf(""));
		add(portField);

		// Username field
		add(new JLabel("Username:"));
		usernameField = new JTextField();
		add(usernameField);

		// Password field
		add(new JLabel("Password:"));
		passwordField = new JPasswordField();
		add(passwordField);
		// Connect Button
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(e -> {
			try {
				connectToServer();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		add(connectButton);

	}

	private void connectToServer() throws ClassNotFoundException {
		String host = hostField.getText();
		int port = Integer.parseInt(portField.getText());
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());

		try (Socket socket = new Socket(host, port);
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

			// Send Login Message
			LoginMessage loginMessage = new LoginMessage(username, password);
			outputStream.writeObject(loginMessage);
			outputStream.flush();

			// Receive login response
			Object loginResponse = inputStream.readObject();

			// Check Server's response
			if ("success".equals(loginResponse)) {
				JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
				// Close the current login frame
				this.dispose();
				// main frame after login and connect to server
				openMainAppFrame();

			} else {
				JOptionPane.showMessageDialog(this, "Login Failed: " + loginResponse, "Error",
						JOptionPane.ERROR_MESSAGE);

			}
			// responseArea.append("Login Response: " + loginResponse + "\n");

		} catch (IOException e) {
			// responseArea.append("Error: Unable to connect to the server.\n");
			JOptionPane.showMessageDialog(this, "Error: Unable to connect to the server.\n" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void openMainAppFrame() {

		// Close the current login frame
		this.dispose();

		// Create a new JFrame for the main application
		JFrame mainAppFrame = new JFrame("Main Application");
		mainAppFrame.setSize(400, 300);
		mainAppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainAppFrame.setLocationRelativeTo(null); // Center the window

		// Add components to the main application frame
		mainAppFrame.setLayout(new BorderLayout());
		JLabel welcomeLabel = new JLabel("Welcome to the Main Application!", JLabel.CENTER);
		mainAppFrame.add(welcomeLabel, BorderLayout.CENTER);

		// Show the main application frame
		mainAppFrame.setVisible(true);
	}

	// Main method to launch the GUI
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ClientGui clientGui = new ClientGui();
			clientGui.setVisible(true);
		});
	}

}
