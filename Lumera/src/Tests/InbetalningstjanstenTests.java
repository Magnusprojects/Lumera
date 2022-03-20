package Tests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import se.itello.helper.FileHelper;
import se.itello.services.Inbetalningstjansten;

public class InbetalningstjanstenTests {
	
	@Test
	public void TestPaymentsForInbetalningstjansten() throws ParseException, Exception {
		List<String> FileRows = FileHelper.getFileRows("Exempelfil_inbetalningstjansten.txt");
		Inbetalningstjansten inbetalningstjansten = new Inbetalningstjansten();
		inbetalningstjansten.parsePayments(FileRows);

		assertEquals(inbetalningstjansten.getPayments().getAccountNumber(),"12341234567897");
		assertEquals(inbetalningstjansten.getPayments().getCurrency(),"SEK");
		
		assertEquals(inbetalningstjansten.getPayments().getPaymentPosts().get(0).getAmount(),new BigDecimal("400000"));
		assertEquals(inbetalningstjansten.getPayments().getPaymentPosts().get(0).getReference(),"9876543210");
		assertEquals(inbetalningstjansten.getPayments().getPaymentPosts().get(1).getAmount(),new BigDecimal("100000"));
		assertEquals(inbetalningstjansten.getPayments().getPaymentPosts().get(1).getReference(),"9876543210");
		assertEquals(inbetalningstjansten.getPayments().getPaymentPosts().get(2).getAmount(),new BigDecimal("1030000"));
		assertEquals(inbetalningstjansten.getPayments().getPaymentPosts().get(2).getReference(),"9876543210");		
	}	
}
