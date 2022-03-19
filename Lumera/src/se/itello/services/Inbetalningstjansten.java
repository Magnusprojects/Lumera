package se.itello.services;

import java.math.BigDecimal;
import java.util.List;

import se.itello.eample.payments.interfaces.IPaymentService;
import se.itello.example.models.FilePayment;
import se.itello.example.models.PaymentPost;

/*
 * En implementation för att läsa in betalningar av filtypen inbetalningstjansten.
 */

public class Inbetalningstjansten implements IPaymentService {
private FilePayment payments = new FilePayment();
	
	private static final String OPENINGPOST = "00";
	private static final String PAYMENTPOST = "30";
	private static final String ENDPOST = "99";
	
	@Override
	public FilePayment parsePayments(List<String> fileRows) throws Exception {
		ParseOpeningLine(fileRows.get(0));
		fileRows.remove(0);
		parsePaymentsPosts(fileRows);	
		parseEndPost(fileRows.get(fileRows.size() - 1));
			
		return payments;	
	}

	private void parseEndPost(String endPost) throws Exception {
		  String postType = String.valueOf(endPost.charAt(0)) + String.valueOf(endPost.charAt(1));
          if(postType.equals(ENDPOST)){      	  	
        		String stringAmount = endPost.substring(2,22).trim().replaceAll(",",".");
		        BigDecimal amount = new BigDecimal(stringAmount);
		        payments.getEndPost().setAmount(amount);	   
                payments.getEndPost().setNumberOfPaymentPosts(Integer.valueOf(endPost.substring(30,38).trim()));
          }else
        	  throw new Exception("Invalid PostType for endpost");	
	}

	private void ParseOpeningLine(String openingPost) throws Exception {
		  String postType = String.valueOf(openingPost.charAt(0)) + String.valueOf(openingPost.charAt(1));
          if(postType.equals(OPENINGPOST)){
        	   payments.getOpningPost().setAccountNumber(openingPost.substring(10,24).trim()); 		   
          }else
  			throw new Exception("Invalid PostType for openingpost");	
	}
	
	private void parsePaymentsPosts(List<String> fileRows) {
		for (String postLine : fileRows) {   	 
			  String postType = String.valueOf(postLine.charAt(0)) + String.valueOf(postLine.charAt(1));
			if(postType.equals(PAYMENTPOST)) {
				PaymentPost paymentPost = new PaymentPost();    	
				String stringAmount = postLine.substring(2,22).trim().replaceAll(",",".");
		        BigDecimal amount = new BigDecimal(stringAmount);
		        paymentPost.setAmount(amount);	        
		        String reference = postLine.substring(40,65).trim();
		        paymentPost.setReference(reference);	        
		        payments.getPaymentPosts().add(paymentPost);     
			}else 
				break;			
		}
	}


}
