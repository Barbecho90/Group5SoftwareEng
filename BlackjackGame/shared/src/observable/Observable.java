package observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import abstractMessages.AbstractMessage;

public abstract class Observable implements Serializable{
	private static final long serialVersionUID = 1L;
	private final List<IObserver> observers;
	
	
	public Observable() {
		observers = new ArrayList<IObserver>();
	}
	
	public final void register(IObserver observer) {
		observers.add(observer);
	}
	
	public final void unregister(IObserver observer) {
		observers.remove(observer);
	}
	
	public final void broadcast(AbstractMessage message) {
		System.out.println("Broadcasting message: " + message);
		List<IObserver> altList = new ArrayList<IObserver>(observers);
		
		for (IObserver observer : altList) {
			observer.update(message);
		}
	}
}
