package se.itello.example.models;

import java.math.BigDecimal;

public class EndPost {
	private BigDecimal amount;
	private int NumberOfPaymentPosts;
	
	BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	int getNumberOfPaymentPosts() {
		return NumberOfPaymentPosts;
	}
	
	public void setNumberOfPaymentPosts(int numberOfPaymentPosts) {
		NumberOfPaymentPosts = numberOfPaymentPosts;
	}


}
