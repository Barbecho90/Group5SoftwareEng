package abstractMessages;

public abstract class AbstractBroadcastBalanceMessage extends AbstractMessage{

	private static final long serialVersionUID = 1L;
	
	private double blance;

	public double getBlance() {
		return blance;
	}

	public void setBlance(double blance) {
		this.blance = blance;
	}
}
