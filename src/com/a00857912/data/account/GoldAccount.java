package com.a00857912.data.account;
/**
 * GoldAccount data class
 * 
 * @author a00857912
 * @version 1.0
 */
public class GoldAccount extends Account {

	private boolean overdraft;

	public static final double INTEREST_RATE = 0.025;
	public static final double FEE = 10.0;
	public static final double OVERDRAFT_AMT = -5000.0;

	/**
	 * Default constructor
	 */
	public GoldAccount() {
		super();
	}

	/**
	 * Overloaded constructor
	 * 
	 * @param balance
	 *            the balance to set
	 * @param accountNumber
	 *            the account number to set
	 * 
	 */
	public GoldAccount(double balance, String accountNumber) {
		super(balance, accountNumber);
		overdraft = false;
	}

	/**
	 * 
	 * @return the interestRate as double
	 */
	public double getInterestRate() {

		return INTEREST_RATE;
	}

	/**
	 * 
	 * @return the inOverdraft as boolean
	 */
	public boolean isOverdraft() {

		return overdraft;
	}

	/**
	 * 
	 * @param inOverdraft
	 *            the inOverdraft to set
	 */
	public void setOverdraft(boolean overdraft) {

		this.overdraft = overdraft;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Account#subtractFromBalance(double)
	 */
	@Override
	public void subtractFromBalance(double amount) {

		if (getBalance() - amount >= GoldAccount.OVERDRAFT_AMT) {
			
			super.subtractFromBalance(amount);	

		}
	}
	
	/*
	 * Subclass implementation for abstract class method 
	 */
	public  void addTransaction(String transactionInfo){
		if(transactionInfo != null){
			accountRecords.add(transactionInfo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoldAccount [interestRate=" + INTEREST_RATE + ", overdraft=" + overdraft + ", toString()="
				+ super.toString() + "]";
	}

}
