package model;

import java.io.ObjectOutputStream;

import observable.IObserver;

public abstract class User implements IObserver{
	private ObjectOutputStream outputStream;
	
	public abstract void login();

	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(ObjectOutputStream outputStream) {
		this.outputStream = outputStream;
	}
}
