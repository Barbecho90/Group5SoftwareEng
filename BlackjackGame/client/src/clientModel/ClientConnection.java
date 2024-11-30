package clientModel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Account;

public class ClientConnection {
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	
	public ClientConnection() {}
	
	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(ObjectOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public ObjectInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ObjectInputStream inputStream) {
		this.inputStream = inputStream;
	}
}
