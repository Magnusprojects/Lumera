package se.itello.eample.payments.interfaces;

import java.text.ParseException;
import java.util.List;

import se.itello.example.models.FilePayment;

public interface IPaymentService {

	FilePayment parsePayments(List<String> fileRows) throws ParseException, Exception;

}
