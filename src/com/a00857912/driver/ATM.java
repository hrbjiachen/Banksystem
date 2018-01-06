package com.a00857912.driver;

import com.a00857912.data.account.Account;

import com.a00857912.data.account.ChequingAccount;
import com.a00857912.data.account.GoldAccount;
import com.a00857912.data.account.SavingsAccount;
import com.a00857912.data.customer.BankCustomer;
import com.a00857912.database.Bank;
import com.a00857912.report.BankReporter;
import com.a00857912.tool.InputReader;

/**
 * ATM class, The COMP 1451 Assignment application driver
 */

/**
 * @author A00857912
 * @version 1.0
 *
 */
public class ATM {
	private InputReader reader;
	private String accountNumber;
	private String passcode;
	private boolean customerVerified;

	private Bank theBank;
	private BankCustomer currentCustomer;
	private Account currentAccount;

	private final int GOLD_AGE = 65;

	/**
	 * Default constructor. Calls the initialize() method to seed the Bank with
	 * some BankCustomers. Calls the run() method to perform the primary program
	 * functions.
	 */
	public ATM() {
		theBank = new Bank();
		initialize();
		run();
	}

	/**
	 * Main method calls the class default constructor.
	 * 
	 * @param args
	 *            for program arguments (not used)
	 */
	public static void main(String[] args) {
		new ATM();

	}

	/**
	 * The primary application processor. All application functions are called
	 * from here. Starts by seeding the Bank class HashMap with BankCustomers,
	 * and display a menu of choices. Uses a loop to prompt users to perform
	 * banking transactions. Must use switch/case selection to determine uses
	 * choices.
	 */
	public void runAsAdmin() {

		reader = new InputReader();
		boolean exit = false;
		BankReporter reporter = new BankReporter();

		while (!exit) {
			System.out.println("");
			System.out.println("Hello Admin.");
			System.out.println("");
			System.out.println("Choose one of the following options:");
			System.out.println("1 - Display account information by prefix");
			System.out.println("2 - Display all active accounts");
			System.out.println("3 - Display all inactive accounts");
			System.out.println("4 - Display total value of all accounts ");
			System.out.println("5 - Exit");
			System.out.print("> ");
			int choice = reader.getIntInput();

			switch (choice) {
			case 1:

				System.out.println("Please enter the prefix:");
				String prefix = reader.getStringInput();

				reporter.displayByCode(theBank.theBank, prefix);
				break;
			case 2:

				reporter.displayAllCodes(theBank.theBank);
				break;

			case 3:

				reporter.displayInactiveCodes(theBank.theBank);
				break;
			case 4:

				reporter.displayAccountTotals(theBank.theBank);
				break;

			case 5:
				System.out.println("");

				System.out.println("Displaying all the accounts in the bank.");
				Bank.displayAllCustomers();
				System.exit(0);

			default:
				System.out.println("KA-BOOM!!!");
				System.exit(0);
			}
		}
	}

	/**
	 * 
	 */
	public void run() {

		reader = new InputReader();
		boolean exit = false;

		System.out.println("Welcome to Bullwinkle's Bank.");

		while (!exit) {
			System.out.println("");
			System.out.println("Choose one of the following options:");
			System.out.println("1 - Sign In");
			System.out.println("2 - Deposit");
			System.out.println("3 - Withdraw");
			System.out.println("4 - Display Account Info");
			System.out.println("5 - Exit");
			System.out.print("> ");
			int choice = reader.getIntInput();

			switch (choice) {
			case 1:
				verifyCustomer();
				break;
			case 2:
				transactDeposit();
				break;
			case 3:
				transactWithdraw();
				break;
			case 4:
				displayAccountInformation();
				break;
			case 5:
				shutDown();
			default:
				System.out.println("KA-BOOM!!!");
				System.exit(0);
			}
		}
	}

	/**
	 * Adds Customer references to the Bank HashMap as seed data for testing.
	 */
	public void initialize() {

		BankCustomer[] customers = { new BankCustomer("Darby", "Dog", "123", 35),
				new BankCustomer("Myia", "Dog", "456", 12), new BankCustomer("Freckle", "Cat", "789", 65),
				new BankCustomer("Test", "Dog1", "123", 35), new BankCustomer("Test", "Dog2", "123", 35),
				new BankCustomer("Test", "Dog3", "123", 35) };

		Account[] accounts = { new SavingsAccount(100.0, "SA-123"), new ChequingAccount(50.0, "CH-123"),
				new GoldAccount(200.0, "GL-123"), new SavingsAccount(100.0, "SA-124"),
				new SavingsAccount(100.0, "SA-125"), new SavingsAccount(100.0, "CH-222") };

		for (int i = 0; i < customers.length; i++) {

			if (accounts[i] instanceof GoldAccount && customers[i].getAge() < GOLD_AGE) {

				customers[i].setAccount(new SavingsAccount(0.0, "SA-DEFAULT"));
				System.out.println("ERROR: Customer is too young to have a GoldAccount.\n"
						+ "Savings Account created instead. Try again after your next birthday.");

			} else {
				customers[i].setAccount(accounts[i]);
			}

		}

		for (BankCustomer customer : customers) {
			theBank.createAccount(customer);
		}

		theBank.deactivate("SA-124");
		theBank.deactivate("SA-125");
		theBank.deactivate("CH-222");

	}

	/**
	 * Performs a deposit into a BankCustomer's account. Checks to see if the
	 * user has signed in. If not, then verifyCustomer() is called and the menu
	 * is displayed again.
	 */
	public void transactDeposit() {

		if (customerVerified) {
			System.out.println("Enter the amount to deposit: ");
			double amount = reader.getDoubleInput();
			currentCustomer.getAccount().addToBalance(amount);

		} else {

			System.out.println("ERROR: You must LOGIN before you can perform a transaction.");
			verifyCustomer();
		}
	}

	/**
	 * Performs a withdrawal from a BankCustomer's account. Checks to see if the
	 * user has signed in. If not, then verifyCustomer() is called and the menu
	 * is displayed again.
	 */
	public void transactWithdraw() {

		if (customerVerified) {
			System.out.println("Enter the amount to withdraw: ");
			double amount = reader.getDoubleInput();

			currentCustomer.getAccount().subtractFromBalance(amount);

		} else {

			System.out.println("ERROR: You must LOGIN before you can perform a transaction.");
			verifyCustomer();
		}

	}

	/**
	 * Performs a withdrawal from a BankCustomer's account. Checks to see if the
	 * user has signed in. If not, then verifyCustomer() is called and the menu
	 * is displayed again.
	 */
	public void displayAccountInformation() {

		if (customerVerified) {
			System.out.println("Here is your information.");
			Bank.displayCustomerInformation(currentCustomer);
		} else {

			System.out.println("ERROR: You must LOGIN before you can perform a transaction.");
			verifyCustomer();
		}

	}

	/**
	 * To confirm a BankCustomer's account number and passcode. Called when the
	 * user is required to sign in to the application. Will set a boolean so the
	 * user does not have to sign in again during the session.
	 */
	public void verifyCustomer() {

		System.out.println("Enter Account Number: ");
		accountNumber = reader.getStringInput();
		System.out.println("Enter Passcode: ");
		passcode = reader.getStringInput();

		if (accountNumber.equals("admin") && passcode.equals("admin")) {

			runAsAdmin();

		} else {

			currentCustomer = Bank.theBank.get(accountNumber);
			currentAccount = currentCustomer.getAccount();

			if (currentCustomer != null) {
				if (passcode.equals(currentCustomer.getPasscode())) {

					customerVerified = true;
				} else {
					System.out.println("ERROR: Either account number or passcode is not correct.");
					run();
				}

			} else {
				System.out.println("ERROR: Either account number or passcode is not correct.");
				run();
			}
		}

	}

	/**
	 * Displays the final information for the current account along with the
	 * account transaction history. Then displays all the account data for all
	 * bank customer and terminates the program run.
	 */
	public void shutDown() {

		System.out.println("Thank you for banking at Bullwinkle's Bank");

		System.out.println("ACCOUNT SUMMARY:");
		if (currentCustomer != null) {
			Bank.displayCustomerInformation(currentCustomer);
			currentAccount.displayAccountRecords();
		}

		System.out.println("");

		System.out.println("Displaying all the accounts in the bank.");
		Bank.displayAllCustomers();
		System.exit(0);
	}
}
