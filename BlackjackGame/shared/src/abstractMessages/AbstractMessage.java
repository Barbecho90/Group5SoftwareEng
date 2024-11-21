package abstractMessages;

import java.io.Serializable;

public abstract class AbstractMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public abstract Object execute();

}
