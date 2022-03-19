import java.io.IOException;

import se.itello.eample.payments.interfaces.IPaymentReceiver;
import se.itello.services.ConsolePaymentReceiver;
import se.itello.services.PaymentHandler;

public class Program {

	//Anger vilken klass som ska emot och hantera betalningar.
	//Denna implementation kan enkelt �ndras genom att skapa ny klass som implementerar IPaymentReceiver och instansiera den nya klassen nedanf�r.
	static IPaymentReceiver paymentReceiver = new ConsolePaymentReceiver();	
	
	public static void main(String args[]) {
		try {
			
			//Tar emot betalningsfiler, l�ser, parsar, utf�r och skickar till en paymentReceiver.
			PaymentHandler paymentHandler = new PaymentHandler(paymentReceiver);
			paymentHandler.ReadFilePayments("Exempelfil_betalningsservice.txt");
			
			paymentHandler.ReadFilePayments("Exempelfil_inbetalningstjansten.txt");
		} catch (Exception e) {
			//Om fel uppst�r n�gonstans skickas detta felmeddelande till en PaymentReceiver.
			paymentReceiver.LogError(e.getMessage());
		}
	}
}
