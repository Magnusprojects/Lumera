package se.itello.services;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;

import se.itello.eample.payments.interfaces.IPaymentReceiver;
import se.itello.eample.payments.interfaces.IPaymentService;
import se.itello.example.models.FilePayment;
import se.itello.example.models.PaymentPost;

public class PaymentHandler {
	 private IPaymentReceiver paymentReceiver;

	 public PaymentHandler(IPaymentReceiver paymentReceiver) {
		 this.paymentReceiver = paymentReceiver;
	 }
	 	    
	 public void ReadFilePayments(String fileName) throws IOException {
	     List<String> FileRows = getFileRows(fileName);
	     FilePayment payments = new FilePayment();  
	     String className = getServiceClassName(fileName);
	     
	     try {	
	    	 Class c =Class.forName("se.itello.services." + className);  
	    	 Constructor constructor = c.getConstructor();		    	
	    	 IPaymentService paymentService = (IPaymentService) constructor.newInstance();
	    	 payments = paymentService.parsePayments(FileRows);
	         ExecutePayment(payments);
	     }catch(Exception e) {
	    	 paymentReceiver.LogError("Wrong read payment file: " + e.getMessage());
	    }
	 }
 
	  private String getServiceClassName(String fileName) {
		 String className = fileName.substring(fileName.indexOf("_") + 1);
		 className = className.substring(0, className.lastIndexOf('.'));		     
		 return className.substring(0, 1).toUpperCase() + className.substring(1);		 	  
	  }

	private List<String> getFileRows(String fileName) throws IOException {
	        BufferedReader file = new BufferedReader(new FileReader(fileName));
	        List<String> paymentRows = new ArrayList<>();
	        
	        boolean fileEnd = false;
	        while (!fileEnd){
	            String row = file.readLine();
	            if(row == null)
	                fileEnd = true;
	            else
	            	paymentRows.add(row);
	        }
	        file.close();
	        return paymentRows;
	    }
	    
	   private void ExecutePayment(FilePayment payments) {		   
	        paymentReceiver.startPaymentBundle(payments.getAccountNumber(), payments.getPaymentDate(), payments.getCurrency());  
	        for (PaymentPost paymentPost: payments.getPaymentPosts()) {
	            paymentReceiver.payment(paymentPost.getAmount(), paymentPost.getReference());
	        }
	        paymentReceiver.endPaymentBundle();
	    }
	
}
