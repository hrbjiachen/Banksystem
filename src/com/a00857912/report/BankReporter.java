package com.a00857912.report;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import com.a00857912.database.Bank;
import com.a00857912.defination.Reporter;

/**
 * 
 */

/**
 * implement the Reporter methods to display information about the Accounts
 * currently set up with the bank
 * 
 * @author a00857912
 * @version 1.0
 *
 */
public class BankReporter implements Reporter {

	/**
	 * * Display record by prefix/account type, Implementation for interface
	 * 
	 * @param records
	 *            the records as a HashMap<String, BankCustomer>();
	 * @param prefix
	 *            account number prefix as a string
	 */
	public void displayByCode(HashMap records, String prefix) {

		Bank.theBank = records;

		for (String accountNumber : Bank.theBank.keySet()) {
			if (accountNumber.contains(prefix)) {
				System.out.println(Bank.theBank.get(accountNumber).getAccount());
			}
		}
	}

	/**
	 * display all the active Accounts in the bank, Implementation for interface
	 * 
	 * @param records
	 *            the records as a HashMap<String, BankCustomer>()
	 */
	public void displayAllCodes(HashMap records) {

		Bank.theBank = records;

		Set<String> unsorted = Bank.theBank.keySet();
		Set<String> sorted = new TreeSet<String>(unsorted);

		for (String accountNumber : sorted) {
			if (Bank.theBank.get(accountNumber).getAccount().isActive()) {
				System.out.println(Bank.theBank.get(accountNumber).getAccount());
			}
		}
	}

	/**
	 * display only the inactive accounts in the bank
	 */
	public void displayInactiveCodes(HashMap records) {

		Bank.theBank = records;

		Set<String> sorted = new TreeSet<>(Collections.reverseOrder());
		for (String accountNumber : Bank.theBank.keySet()) {
			sorted.add(accountNumber);
		}

		for (String accountNumber : sorted) {
			if (!Bank.theBank.get(accountNumber).getAccount().isActive()) {
				System.out.println(Bank.theBank.get(accountNumber).getAccount());
			}
		}
	}

	/**
	 * display a formatted dollar value which is the sum of all account balances
	 * in the bank
	 */
	public void displayAccountTotals(HashMap theBank) {

		Bank.theBank = theBank;
		double total = 0.0;

		for (String accountNumber : Bank.theBank.keySet()) {

			total += Bank.theBank.get(accountNumber).getAccount().getBalance();
		}
		
		System.out.println("Total account balances: $" + String.format("%10.2f", total) );
	}

}
