import java.io.IOException;

import se.itello.eample.payments.interfaces.IPaymentReceiver;
import se.itello.services.ConsolePaymentReceiver;
import se.itello.services.PaymentHandler;

public class Program {

	static IPaymentReceiver paymentReceiver = new ConsolePaymentReceiver();	
	
	public static void main(String args[]) {
		try {
			PaymentHandler paymentHandler = new PaymentHandler(paymentReceiver);
			paymentHandler.ReadFilePayments("Exempelfil_betalningsservice.txt");
			
			paymentHandler.ReadFilePayments("Exempelfil_inbetalningstjansten.txt");
		} catch (Exception e) {
			paymentReceiver.LogError(e.getMessage());
		}
	}
}
