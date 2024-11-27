package client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import message.LoginMessage;

import java.awt.*;

public class ClientGui extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField hostField;
	private JTextField portField;
	private JTextArea responseArea;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private DefaultListModel<String> tableListModel = new DefaultListModel<>(); // To store the list of Tables

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
				//TODO: Check user's Role and open different pane based on role
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = (int) (screenSize.width * 0.5);
		int frameHeigth = (int) (screenSize.height * 0.5);

		// create a new frame
		JFrame frame = new JFrame("Welcome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeigth);
		frame.setLayout(new GridBagLayout()); // it allows flexible component arrangement
		frame.setLocationRelativeTo(null); // center the frame on the screen

		// Create a JList using the DefaultListModel
		JList<String> jList = new JList<>(tableListModel);//TODO: recieve lobby object from server to create the list of tables.
		jList.setFont(new Font("Arial", Font.PLAIN, 30));

		// Add the JList wrapped in a JScrollPane to allow scrolling when items exceed
		// the visible area
		JScrollPane scrollPane = new JScrollPane(jList);

		// Add the JScrollPane to the GridBagLayout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0; // Column 0
		gbc.gridy = 0; // Row 0
		gbc.weightx = 1.0; // Fill the horizontal space
		gbc.weighty = 1.0; // Fill the vertical space
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20, 20, 20, 20); // Padding around components
		frame.add(scrollPane, gbc); // Add JScrollPane to the frame

		// create a panel for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(8, 1, 1, 10));

		// create multiple buttons
		JButton button1 = new JButton("Deposit Funds");
		JButton button2 = new JButton("Withdraw Funds");
		JButton button3 = new JButton("Join Table");
		JButton button4 = new JButton("Disconnect");

		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);

		// Disable button until user selects from the list
		button3.setEnabled(false);

		// Add the button panel to the GridBagLayout on the right side
		gbc.gridx = 1; // Column 1 (right side)
		gbc.gridy = 0; // Row 0
		gbc.weightx = 0.2; // Small weight for the button panel
		gbc.weighty = 1.0; // Fill the vertical space
		gbc.fill = GridBagConstraints.VERTICAL; // Fill vertically
		gbc.gridheight = 1;
		gbc.insets = new Insets(50, 50, 50, 50); // Padding around components
		frame.add(buttonPanel, gbc); // Add button panel to the frame


		// Listener
		jList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) { // Check if the event is adjusting

					// Get the selected item
					String selectedItem = jList.getSelectedValue();

					// Enable button1 if an item is selected
					button1.setEnabled(selectedItem != null);
				}
			}
		});

		// Add listeners for each buttons
		
//				button1.addActionListener(e -> {
//					int selectedIndex = jList.getSelectedIndex();
//					if (selectedIndex != -1) {
//						model.Table selectedDvd = dvdList.dvdArray()[selectedIndex];
//						Consumer<Void> callback = (result) -> { // call back to refresh list
//							this.refreshDVDListModel();
//						};
//						new DetailGUI(frame, dvdList, selectedDvd, callback);
//					}
//				});
//
//				button2.addActionListener(e -> doGetDVDsByRating());
//				button3.addActionListener(e -> doGetTotalRunningTime());
//				button4.addActionListener(e -> doAddOrModifyDVD());
		
		// Make the frame visible
		frame.setVisible(true);

	}	

	private void openDealerTableSelectionFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = (int) (screenSize.width * 0.5);
		int frameHeigth = (int) (screenSize.height * 0.5);

		// create a new frame
		JFrame frame = new JFrame("Welcome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeigth);
		frame.setLayout(new GridBagLayout()); // it allows flexible component arrangement
		frame.setLocationRelativeTo(null); // center the frame on the screen

		// Create a JList using the DefaultListModel
		JList<String> jList = new JList<>(tableListModel);
		jList.setFont(new Font("Arial", Font.PLAIN, 30));

		// Add the JList wrapped in a JScrollPane to allow scrolling when items exceed
		// the visible area
		JScrollPane scrollPane = new JScrollPane(jList);

		// Add the JScrollPane to the GridBagLayout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0; // Column 0
		gbc.gridy = 0; // Row 0
		gbc.weightx = 1.0; // Fill the horizontal space
		gbc.weighty = 1.0; // Fill the vertical space
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20, 20, 20, 20); // Padding around components
		frame.add(scrollPane, gbc); // Add JScrollPane to the frame

		// create a panel for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(8, 1, 1, 10));

		// create multiple buttons
		JButton button1 = new JButton("Join Table");
		JButton button2 = new JButton("Create Table");
		JButton button3 = new JButton("Disconnect");

		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);

		// Disable button until user selects from the list
		button1.setEnabled(false);

		// Add the button panel to the GridBagLayout on the right side
		gbc.gridx = 1; // Column 1 (right side)
		gbc.gridy = 0; // Row 0
		gbc.weightx = 0.2; // Small weight for the button panel
		gbc.weighty = 1.0; // Fill the vertical space
		gbc.fill = GridBagConstraints.VERTICAL; // Fill vertically
		gbc.gridheight = 1;
		gbc.insets = new Insets(50, 50, 50, 50); // Padding around components
		frame.add(buttonPanel, gbc); // Add button panel to the frame


		// Listener
		jList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) { // Check if the event is adjusting

					// Get the selected item
					String selectedItem = jList.getSelectedValue();

					// Enable button1 if an item is selected
					button1.setEnabled(selectedItem != null);
				}
			}
		});
	}
	
	private void openGameFrame() {
		// Close the current login frame
		this.dispose();
		// Create a new JFrame for the main application
		JFrame mainAppFrame = new JFrame("Group 5's Awesome Blackjack game");
		mainAppFrame.setSize(400, 300);
		mainAppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainAppFrame.setLocationRelativeTo(null); // Center the window

		// Add components to the main application frame
		mainAppFrame.setLayout(new BorderLayout());
		JLabel welcomeLabel = new JLabel("Welcome to the Main Application!", JLabel.CENTER);
		mainAppFrame.add(welcomeLabel, BorderLayout.NORTH);
		JPanel buttonPanel = new JPanel();
		JButton button1 = new JButton("Deposit Funds");
		JButton button2 = new JButton("Withdraw Funds");
		JButton button3 = new JButton("Join a Table");
		JButton button4 = new JButton("Disconnect");
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);
		mainAppFrame.add(welcomeLabel, BorderLayout.CENTER);
		mainAppFrame.add(buttonPanel, BorderLayout.CENTER);
//      TODO: Game screen for both player and dealer.
	
		// Show the main application frame
		mainAppFrame.setVisible(true);
		// dimensions according to the current screen size
	}

	// Main method to launch the GUI
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ClientGui clientGui = new ClientGui();
			clientGui.setVisible(true);
		});
	}

}
