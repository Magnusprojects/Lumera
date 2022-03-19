package se.itello.example.models;

import java.math.BigDecimal;

/*
 * Representerar en betalningspost.
 */

public class PaymentPost {
	private String Posttype;
	private BigDecimal amount;
	private String reference;
	
	 
	public String getPosttype() {
		return Posttype;
	}
	public void setPosttype(String posttype) {
		Posttype = posttype;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}	
}
