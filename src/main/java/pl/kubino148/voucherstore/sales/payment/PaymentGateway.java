package pl.kubino148.voucherstore.sales.payment;

import pl.kubino148.payment.payu.exceptions.PayUException;
import pl.kubino148.voucherstore.sales.ordering.Reservation;

public interface PaymentGateway {
    PaymentDetails registerFor(Reservation reservation) throws PayUException;

    boolean isTrusted(PaymentUpdateStatusRequest updateStatusRequest);
}
