package se.itello.services;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import se.itello.eample.payments.interfaces.IPaymentReceiver;


/*
 * En implementation som visar hur inkommande betalningar ska hanteras. 
 * 
 */

public class ConsolePaymentReceiver implements IPaymentReceiver{
	   
	public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {

		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String date = dateFormat.format(paymentDate);

		System.out.println("New payment is started " + date + "\nFrom account number " + accountNumber + "\nWith the currency " + currency);
	}
		  
	public void payment(BigDecimal amount, String reference) {
		System.out.println("Amount " + amount + "\nReference " + reference);
	}
		
	public void endPaymentBundle() {
		   System.out.println("payments were successful\n");
	}

	@Override
	public void LogError(String message) {
		System.out.println(message);		
	}
}
