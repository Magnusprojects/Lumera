package se.itello.example.models;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.itello.eample.payments.interfaces.IPaymentService;

/*
 * Representerar en hel fil med betalningar.
 * Varje fil kommer att parsas till ett objekt av FilePayment från en klass som implementerar IPaymentService.
 */

public class FilePayment {

	private OpeningPost openingPost = new OpeningPost();
	private EndPost endPost = new EndPost();
	private List<PaymentPost> paymentPosts = new ArrayList<PaymentPost>();


	public OpeningPost getOpningPost() {
		return openingPost;
	}

	public void setOpningPost(OpeningPost openingPost) {
		this.openingPost = openingPost;
	}

	public List<PaymentPost> getPaymentPosts() {
		return paymentPosts;
	}

	public void setPaymentPosts(List<PaymentPost> paymentPosts) {
		this.paymentPosts = paymentPosts;
	}

	public EndPost getEndPost() {
		return endPost;
	}

	public void setEndPost(EndPost endPost) {
		this.endPost = endPost;
	}

	public String getAccountNumber() {
		if(openingPost.getAccountNumber() != null)
			return openingPost.getAccountNumber();
		else 
			return "";
	}
	
	public BigDecimal getAmount() throws Exception {
		if(openingPost.getAmount() != null && endPost.getAmount() == null)
			return openingPost.getAmount();
		else if(openingPost.getAmount() == null && endPost.getAmount() != null)
			return endPost.getAmount();
		else if(openingPost.getAmount() != null && endPost.getAmount() != null)
			if(openingPost.getAmount().equals(endPost.getAmount()))
				return openingPost.getAmount();
			else 
				throw new Exception("Two different amount in openingPost and endPost");
		else 
			return BigDecimal.valueOf(0);
	}
	
	public int getNumberOfPaymentPosts() throws Exception {
		if(openingPost.getNumberOfPaymentPosts() != 0 && endPost.getNumberOfPaymentPosts() == 0)
			return openingPost.getNumberOfPaymentPosts();
		else if(openingPost.getNumberOfPaymentPosts() == 0 && endPost.getNumberOfPaymentPosts() != 0)
			return endPost.getNumberOfPaymentPosts();
		else if(openingPost.getNumberOfPaymentPosts() != 0 && endPost.getNumberOfPaymentPosts() != 0) {
			if(openingPost.getNumberOfPaymentPosts() == endPost.getNumberOfPaymentPosts())
				return openingPost.getNumberOfPaymentPosts();
			else 
				throw new Exception("Two different number of payment posts in openingPost and endPost");
		}
		else 
			return 0;
	}
	
	public Date getPaymentDate() {		
		if(openingPost.getPaymentDate() != null)
			return openingPost.getPaymentDate();
		else {
	     	return new Date();
		}		
	}
	
	public String getCurrency() {		
		if(openingPost.getCurrency() != null)
			return openingPost.getCurrency();
		else 
	     	return "SEK"; 	
	}

}
