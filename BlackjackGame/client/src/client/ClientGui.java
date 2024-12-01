package client;

import java.io.*;
import java.net.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clientModel.Table;
import message.CreateTableMessage;
import message.DepositMessage;
import message.GetTablesMessage;
import message.JoinTableMessage;
import message.LoginMessage;
import message.WithdrawMessage;
import model.Account;
import model.LobbyTable;
import serverCommunicator.SendMessage;
import state.StateManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGui extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField hostField;
	private JTextField portField;
	private JLabel balance;
	private JTextArea responseArea;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private DefaultListModel<String> tableListModel = new DefaultListModel<>(); // To store the list of Tables
	
	private LoginMessage loginMessage;
	private LobbyTable selectedTable;

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
		
		try {
			socket = new Socket(host, port);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
					
			// Send Login Message
			loginMessage = new LoginMessage(username, password);
			outputStream.writeObject(loginMessage);
			outputStream.flush();

			// Receive login response
			Account loginResponse = (Account) inputStream.readObject();

			// Check Server's response
			if (loginResponse != null) {
				// Set Account for local use
				StateManager.getInstance().setAccount(loginResponse);
				
				// Save connection state
				StateManager.getInstance().getAccount().getUser().setSocket(socket);
				StateManager.getInstance().getClient().setInputStream(inputStream);
				StateManager.getInstance().getClient().setOutputStream(outputStream);
				
				// keep connection open
				startListeningForServerMessages();
				
				JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
				// Close the current login frame
				this.dispose();
				if (loginMessage.username.contains("user")) {
					openMainAppFrame();
				} else if (loginMessage.username.contains("dealer")) {
					openDealerTableSelectionFrame();
				}

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
		
		GetTablesMessage gtm = new GetTablesMessage();
		List<LobbyTable> response = (List<LobbyTable>) SendMessage.getInstance().send(gtm);
		
		DefaultListModel<LobbyTable> listModel = new DefaultListModel<LobbyTable>();
		
		for (LobbyTable table: response) {
			listModel.addElement(table);
		}
		
		// Create a new JFrame for the main application
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = (int) (screenSize.width * 0.5);
		int frameHeight = (int) (screenSize.height * 0.5);

		// create a new frame
		JFrame frame = new JFrame("Welcome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setLayout(new GridBagLayout()); // it allows flexible component arrangement
		frame.setLocationRelativeTo(null); // center the frame on the screen

		// Create a JList using the DefaultListModel
		JList<LobbyTable> jList = new JList<LobbyTable>(listModel);//TODO: recieve lobby object from server to create the list of tables.
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
		balance = new JLabel("ACCOUNT BALANCE:$" + StateManager.getInstance().getAccount().getBalance());

		
		
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);
		buttonPanel.add(balance);

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
					LobbyTable selectedItem = jList.getSelectedValue();
					selectedTable = selectedItem;
					// Enable button1 if an item is selected
					button3.setEnabled(selectedItem != null);
				}
			}
		});
		
		//add action listeners
		button1.addActionListener(e -> openDepositFrame());
		button2.addActionListener(e -> openWithdrawFrame());
		button3.addActionListener(new ActionListener() {  //TODO: make a player join a table
			public void actionPerformed(ActionEvent e) {
				JoinTableMessage jtm = new JoinTableMessage(selectedTable.getTableId());
				String tableId = (String) SendMessage.getInstance().send(jtm);
				
				JOptionPane.showMessageDialog(frame, "Pushed to join " + tableId);
			}
		});
		
		button4.addActionListener(e -> disconnect());
		
		// Make the frame visible
		frame.setVisible(true);

	}

	private void openDealerTableSelectionFrame() { //Placeholder TODO: Implement dealer view
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
		
		button1.addActionListener(e -> joinTable());
		button2.addActionListener(e -> createTable());
		button3.addActionListener(e -> disconnect());
		
		frame.setVisible(true);
	}
	
	private void openGameFrame() { //placeholder TODO: Implement Gameframe
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
	
	private void openDepositFrame() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = (int) (screenSize.width * 0.5);
		int frameHeight = (int) (screenSize.height * 0.5);
		JFrame DepositFrame = new JFrame("Deposit");
		DepositFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DepositFrame.setSize(frameWidth, frameHeight);
		DepositFrame.setLayout(new GridBagLayout()); // it allows flexible component arrangement
		DepositFrame.setLocationRelativeTo(null); // center the frame on the screen
		
		JPanel DepositPanel = new JPanel();
		DepositPanel.setLayout(new GridLayout(8, 1, 1, 10));
		
		JButton submitDeposit = new JButton("Deposit");
		
		JTextField numberField = new JTextField(20);
		
		submitDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int number = Integer.parseInt(numberField.getText());
			
			// Send Deposit message, get account balance as response and update local account
			DepositMessage dposit = new DepositMessage(number);
			Object resp = SendMessage.getInstance().send(dposit);
			System.out.print("Deposit Made new balance = " + resp);
			
			if(resp != null) {
				StateManager.getInstance().getAccount().setBalance((double) resp);
				balance.setText("Account Balance: " + (double) resp);
				JOptionPane.showMessageDialog(DepositFrame, "$" + number + " Deposited");
			} else {
				JOptionPane.showMessageDialog(DepositFrame, "Error funds not deposited!");
			}
			
			numberField.setText("");
			DepositFrame.dispose();
			}
			
		});
		
		DepositPanel.add(numberField);
		DepositPanel.add(submitDeposit);
		DepositFrame.add(DepositPanel);
		DepositFrame.setVisible(true);
	}

	private void openWithdrawFrame() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = (int) (screenSize.width * 0.5);
		int frameHeight = (int) (screenSize.height * 0.5);
		JFrame WithdrawlFrame = new JFrame("Withdrawl");
		WithdrawlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WithdrawlFrame.setSize(frameWidth, frameHeight);
		WithdrawlFrame.setLayout(new GridBagLayout()); // it allows flexible component arrangement
		WithdrawlFrame.setLocationRelativeTo(null); // center the frame on the screen
		
		JPanel WithdrawlPanel = new JPanel();
		WithdrawlPanel.setLayout(new GridLayout(8, 1, 1, 10));
		
		JTextField numberField = new JTextField(20);
		
		JButton submitWithdrawl = new JButton("Withdrawl");
		
		submitWithdrawl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int number = Integer.parseInt(numberField.getText());
			
			WithdrawMessage withdraw = new WithdrawMessage(number);
			Object resp = SendMessage.getInstance().send(withdraw);
			
			if(resp != null) {
				StateManager.getInstance().getAccount().setBalance((double) resp);
				balance.setText("Account Balance: " + (double) resp);
				JOptionPane.showMessageDialog(WithdrawlFrame, "$" + number + " Withdraw");
			} else {
				JOptionPane.showMessageDialog(WithdrawlFrame, "Error funds not withdraw!");
			
			}
			
			numberField.setText("");
			WithdrawlFrame.dispose();
			}
		});
		
		WithdrawlPanel.add(numberField);
		WithdrawlPanel.add(submitWithdrawl);
		WithdrawlFrame.add(WithdrawlPanel);
		WithdrawlFrame.setVisible(true);
	}
	
	public void joinTable() {
		
	}

	public void createTable() {
		CreateTableMessage ctm = new CreateTableMessage();
		
		String resp = (String) SendMessage.getInstance().send(ctm);
		
		System.out.println(resp);
		
		if(resp == null) {
			return;
		}
		
		Table.getInstance().setTableId(resp);
		JOptionPane.showMessageDialog(null, Table.getInstance().getTableId());
		
		// TODO: Implement transition to dealer view
	}
	
	public void disconnect() {
		System.exit(0);
	}
	
	// Main method to launch the GUI
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ClientGui clientGui = new ClientGui();
			clientGui.setVisible(true);
		});
	}
	
	private void startListeningForServerMessages() {
	    new Thread(() -> {
	    	// TODO: Add implementation of method listener here, this will be used when the server broadcasts messages to update clients on game state
//	        try {
//	            ObjectInputStream inputStream = StateManager.getInstance().getClient().getInputStream();
	            while (true) {
//	                Object message = inputStream.readObject();
	                // Handle incoming messages
//	                System.out.println("Received message: " + message);
	            }
//	        } catch (IOException | ClassNotFoundException e) {
//	            System.err.println("Connection lost: " + e.getMessage());
//	        }
	    }).start();
	}


}
