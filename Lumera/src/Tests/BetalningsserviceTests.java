package Tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import se.itello.eample.payments.interfaces.IPaymentReceiver;
import se.itello.helper.FileHelper;
import se.itello.services.Betalningsservice;
import se.itello.services.ConsolePaymentReceiver;
import se.itello.services.PaymentHandler;

public class BetalningsserviceTests {

	@Test
	public void TestPaymentsForBetalningsservice() throws ParseException, Exception {
		List<String> FileRows = FileHelper.getFileRows("Exempelfil_betalningsservice.txt");
		Betalningsservice betalningsservice = new Betalningsservice();
		betalningsservice.parsePayments(FileRows);
		
		assertEquals(betalningsservice.getPayments().getAccountNumber(),"5555 5555555555");
		assertEquals(betalningsservice.getPayments().getCurrency(),"SEK");
		
		assertEquals(betalningsservice.getPayments().getPaymentPosts().get(0).getAmount(),new BigDecimal("3000"));
		assertEquals(betalningsservice.getPayments().getPaymentPosts().get(0).getReference(),"1234567890");
		assertEquals(betalningsservice.getPayments().getPaymentPosts().get(1).getAmount(),new BigDecimal("1000"));
		assertEquals(betalningsservice.getPayments().getPaymentPosts().get(1).getReference(),"2345678901");
		assertEquals(betalningsservice.getPayments().getPaymentPosts().get(2).getAmount(),new BigDecimal("300.10"));
		assertEquals(betalningsservice.getPayments().getPaymentPosts().get(2).getReference(),"3456789012");
		assertEquals(betalningsservice.getPayments().getPaymentPosts().get(3).getAmount(),new BigDecimal("400.07"));
		assertEquals(betalningsservice.getPayments().getPaymentPosts().get(3).getReference(),"4567890123");
		
	}

}
