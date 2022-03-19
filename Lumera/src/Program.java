import java.io.IOException;

import se.itello.eample.payments.interfaces.IPaymentReceiver;
import se.itello.services.ConsolePaymentReceiver;
import se.itello.services.PaymentHandler;

public class Program {

	//Anger vilken klass som ska emot och hantera betalningar.
	//Denna implementation kan enkelt ändras genom att skapa ny klass som implementerar IPaymentReceiver och instansiera den nya klassen nedanför.
	static IPaymentReceiver paymentReceiver = new ConsolePaymentReceiver();	
	
	public static void main(String args[]) {
		try {
			
			//Tar emot betalningsfiler, läser, parsar, utför och skickar till en paymentReceiver.
			PaymentHandler paymentHandler = new PaymentHandler(paymentReceiver);
			paymentHandler.ReadFilePayments("Exempelfil_betalningsservice.txt");
			
			paymentHandler.ReadFilePayments("Exempelfil_inbetalningstjansten.txt");
		} catch (Exception e) {
			//Om fel uppstår någonstans skickas detta felmeddelande till en PaymentReceiver.
			paymentReceiver.LogError(e.getMessage());
		}
	}
}
