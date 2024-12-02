package abstractMessages;
/**
 * The amount that will be deposit in the account  
 * Server will return balance on execute
 * 
 * NOTE: Username Required from AbstractMessage
 */
public abstract class AbstractDeposit extends AbstractMessage {
	
	private static final long serialVersionUID = 1L;
	private double amount ;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
