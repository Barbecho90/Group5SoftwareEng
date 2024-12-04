package observable;

import abstractMessages.AbstractMessage;

public interface IObserver{
	public void update(AbstractMessage message);
}
