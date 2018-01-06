package com.a00857912.defination;
import java.util.HashMap;

/**
 * 
 */

/**
 * This is an interface that will be implemented by the BankReport class
 * @author a00857912
 * @version 1.0
 *
 */
public interface Reporter {

	/**
	 * Display record by prefix
	 * @param records the records as a Hashmap
	 * @param prefix as a string
	 */
	void displayByCode(HashMap records, String prefix);
	
	/**
	 * Display record by prefix
	 * @param records the records as a Hashmap
	 * @param prefix as a string
	 */	
	void displayAllCodes(HashMap records);
	
	
	/**
	 * Display inactive codes
	 * 
	 * @param records as a Hashmap
	 */
	void displayInactiveCodes(HashMap records);
}
