package serverCommunicator;

import java.io.IOException;
import java.io.ObjectInputStream;

import abstractMessages.AbstractMessage;
import state.StateManager;

/**
 * Helper class to encapsulate the send message logic; { 
 * take the message  ;  send the object via outputStream  ; write and flush and read on the object 
 * coming back and return the object if there is not errors
 */
public class SendMessage {
	private static SendMessage instance = null;
	
	private SendMessage() {};
	
	public static SendMessage getInstance() {
		if(instance == null) {
			instance = new SendMessage();
		}
		
		return instance;
	}
	
	public Object send(AbstractMessage msg) {
		ObjectInputStream is = StateManager.getInstance().getClient().getInputStream();
		try {
			StateManager.getInstance().getClient().getOutputStream().writeObject(msg);
			StateManager.getInstance().getClient().getOutputStream().flush();
			
			Object response = is.readObject();
			return response;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception - Output error");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found Exception - Input error");
			e.printStackTrace();
		}
		
		return null;
	}
}
