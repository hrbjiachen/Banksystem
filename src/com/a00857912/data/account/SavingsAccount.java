package com.a00857912.data.account;

/**
 * SavingsAccount data class
 * 
 * @author a00857912
 * @version 1.0
 */
public class SavingsAccount extends Account {
	
	public static final double MIN_AMOUNT = 100.0;

	/**
	 * Default constructor
	 */
	public SavingsAccount() {
		super();
	}

	/**
	 * Overloaded constructor
	 * 
	 * @param balance
	 *            the balance to set
	 * @param accountNumber
	 *            the account number to set
	 */
	public SavingsAccount(double balance, String accountNumber) {
		super(balance, accountNumber);
	}
		

	/**
	 * @return the minAmount constant as a double
	 */
	public static double getMinAmount() {
		return MIN_AMOUNT;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see Account#subtractFromBalance(double)
	 */
	@Override
	public void subtractFromBalance(double amount) {

		if (getBalance() - amount >= MIN_AMOUNT) {
			
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SavingsAccount [toString()=" + super.toString() + "]";
	}

	
}
