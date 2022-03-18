package se.itello.services;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import se.itello.eample.payments.interfaces.IPaymentService;
import se.itello.example.models.FilePayment;
import se.itello.example.models.OpeningPost;
import se.itello.example.models.PaymentPost;

public class Betalningsservice implements IPaymentService {
	private FilePayment payments = new FilePayment();
	
	private static final String OPENINGPOST = "O";
	private static final String PAYMENTPOST = "B";

	
	@Override
	public FilePayment parsePayments(List<String> fileRows) throws ParseException, Exception{
		ParseOpeningLine(fileRows.get(0));
		fileRows.remove(0);
		parsePaymentsPosts(fileRows);
		  
		return payments;	
	}

	private void parsePaymentsPosts(List<String> fileRows) throws Exception {
	   
		for (String postLine : fileRows) {   	 
			String firstLetter = String.valueOf(postLine.charAt(0));
			if(firstLetter.equals(PAYMENTPOST)) {
				PaymentPost paymentPost = new PaymentPost();    	
		        String stringAmount = postLine.substring(1, 15).trim().replace(',', '.');
		        BigDecimal amount = new BigDecimal(stringAmount);
		        paymentPost.setAmount(amount);	        
		        String reference = postLine.substring(15, 50).trim();
		        paymentPost.setReference(reference);		   
		        payments.getPaymentPosts().add(paymentPost);
			}else
				throw new Exception("Invalid PostType for paymentpost");
	    }
	}

	private void ParseOpeningLine(String openingPost ) throws Exception{
		String firstLetter = String.valueOf(openingPost.charAt(0));
		if(firstLetter.equals(OPENINGPOST)) {
		    payments.getOpningPost().setAccountNumber(openingPost.substring(1,16).trim());
		    
		    String stringAmount = openingPost.substring(16,30).trim().replaceAll(",",".");
		    BigDecimal amount = new BigDecimal(stringAmount);
	        payments.getOpningPost().setAmount(amount);
	
	        payments.getOpningPost().setNumberOfPaymentPosts(Integer.valueOf(openingPost.substring(30,40).trim()));
	          
	        DateFormat format = new SimpleDateFormat("yyyyMMdd");
	        Date date = format.parse(openingPost.substring(40,48));
	        payments.getOpningPost().setPaymentDate(date);
	          
	        payments.getOpningPost().setCurrency(openingPost.substring(48,51));
		}else
			throw new Exception("Invalid PostType for openingpost");
	}
	   

	
	
	
}
