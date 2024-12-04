package sharedModel;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String type; // "login", "text", or "logout"
	private Account account;
	private String text;
	private String status;

	
	public Message(Account account) {
		this.account =account;
		this.type = "login";
	}
	
	
	// Constructor for a message type
	public Message(String type) {
		this.type = type;
		
	}

	// Constructor with text
	public Message(String type, String text) {
		this.type = type;
		this.text = text;
	}

	
	
	public void setAccount(Account account) {
        this.account = account;
    }
	
	
	public String getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Account getAccount() {
		return account;
	}
}
