package se.itello.example.models;

import java.math.BigDecimal;

import java.util.Date;

public class OpeningPost {
	private String postType;
	private String accountNumber;
	private BigDecimal amount;
	private int NumberOfPaymentPosts;
	private Date paymentDate;
	private String currency;
	
	String getPostType() {
		return postType;
	}
	
	public void setPostType(String postType) {
		this.postType = postType;
	}
	
	String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
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
	
	Date getPaymentDate() {
		return paymentDate;
	}
	
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	  
}
