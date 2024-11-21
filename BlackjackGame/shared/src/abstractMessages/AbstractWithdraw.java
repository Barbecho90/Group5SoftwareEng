package abstractMessages;

public abstract class AbstractWithdraw extends AbstractMessage {

	private double amount;
	private boolean withdrawAll;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isWithdrawAll() {
		return withdrawAll;
	}

	public void setWithdrawAll(boolean withdrawAll) {
		this.withdrawAll = withdrawAll;
	}
}