package serverCommunicator;

import java.io.IOException;

import abstractMessages.AbstractMessage;
import state.StateManager;

/**
 * Helper class to encapsulate the send message logic; { 
 * take the message  ;  send the object via outputStream  ; write and flush and read on the object 
 * coming back and return the object if there is not errors
 */
public class SendMessage {
	private static SendMessage instance = null;
	private Object mostRecentResponseObject;
	
	private SendMessage() {};
	
	public static SendMessage getInstance() {
		if(instance == null) {
			instance = new SendMessage();
		}
		
		return instance;
	}
	
	public void send(AbstractMessage msg) {
		try {
			StateManager.getInstance().getClient().getOutputStream().writeObject(msg);
			StateManager.getInstance().getClient().getOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception - Output error");
			e.printStackTrace();
		}
	}

	public Object getMostRecentResponseObject() {
		return mostRecentResponseObject;
	}

	public void setMostRecentResponseObject(Object mostRecentResponseObject) {
		this.mostRecentResponseObject = mostRecentResponseObject;
	}
}
