package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 
 * save the input and output for the GUI - maintains the connection
 * with the server
 */
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
