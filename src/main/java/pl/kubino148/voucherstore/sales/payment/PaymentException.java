package pl.kubino148.voucherstore.sales.payment;

import pl.kubino148.payment.payu.exceptions.PayUException;

public class PaymentException extends IllegalStateException {
    public PaymentException(PayUException e) {
        super(e);
    }
}
