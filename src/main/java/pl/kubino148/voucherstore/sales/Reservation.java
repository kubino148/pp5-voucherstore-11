package pl.kubino148.voucherstore.sales;

import pl.kubino148.voucherstore.sales.offer.Offer;

import java.util.UUID;

public class Reservation {
    public static Reservation of(Offer currentOffer, ClientData clientData) {
        return new Reservation();
    }

    public String getId() {
        return UUID.randomUUID().toString();
    }
}
