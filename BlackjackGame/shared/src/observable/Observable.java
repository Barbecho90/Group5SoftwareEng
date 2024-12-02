package observable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	
	public final void update() {
		List<IObserver> altList = new ArrayList<IObserver>(observers);
		
		for (IObserver observer : altList) {
			observer.update(this);
		}
	}
}
