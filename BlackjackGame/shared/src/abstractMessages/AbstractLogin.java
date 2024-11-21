package abstractMessages;

public abstract class AbstractLogin extends AbstractMessage{
	
	public String username;
	public String password;
	
	
	// Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Abstract method to be implemented by subclasses
    public abstract Object execute();
}

