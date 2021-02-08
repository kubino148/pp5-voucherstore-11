package pl.kubino148.voucherstore.sales;

import pl.kubino148.voucherstore.productcatalog.ProductCatalogConfiguration;
import pl.kubino148.voucherstore.productcatalog.ProductCatalogFacade;
import pl.kubino148.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.kubino148.voucherstore.sales.offer.OfferMaker;
import pl.kubino148.voucherstore.sales.ordering.InMemoryReservationRepository;
import pl.kubino148.voucherstore.sales.ordering.ReservationRepository;
import pl.kubino148.voucherstore.sales.payment.InMemoryPaymentGateway;
import pl.kubino148.voucherstore.sales.payment.PaymentGateway;
import pl.kubino148.voucherstore.sales.productd.ProductDetails;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesTestCase {

    protected ProductCatalogFacade productCatalog;
    protected InMemoryBasketStorage basketStorage;
    protected Inventory alwaysExistsInventory;
    protected CurrentCustomerContext currentCustomerContext;
    protected String customerId;
    protected OfferMaker offerMaker;
    protected PaymentGateway paymentGateway;
    protected ReservationRepository reservationRepository;

    protected CurrentCustomerContext thereIsCurrentCustomerContext() {
        return () -> customerId;
    }

    protected Inventory thereIsInventory() {
        return productId -> true;
    }

    protected InMemoryBasketStorage thereIsBasketStore() {
        return new InMemoryBasketStorage();
    }

    protected ProductCatalogFacade thereIsProductCatalog() {
        return new ProductCatalogConfiguration().productCatalogFacade();
    }

    protected PaymentGateway thereIsInMemoryPaymentGateway() {
        return new InMemoryPaymentGateway();
    }

    protected String thereIsProductAvailable() {
        var id = productCatalog.createProduct();
        productCatalog.applyPrice(id, BigDecimal.valueOf(10));
        productCatalog.updateDetails(id, "lego", "http://picture");

        return id;
    }

    protected SalesFacade thereIsSalesModule() {
        return new SalesFacade(
                basketStorage,
                productCatalog,
                currentCustomerContext,
                alwaysExistsInventory,
                offerMaker,
                paymentGateway,
                reservationRepository
        );
    }

    protected OfferMaker thereIsOfferMaker(ProductCatalogFacade productCatalogFacade) {
        return new OfferMaker(productId -> {
            var product = productCatalogFacade.getById(productId);

            return new ProductDetails(product.getId(), product.getDescription(), product.getPrice());
        });
    }

    protected String thereIsCustomerWhoIsDoingSomeShopping() {
        return UUID.randomUUID().toString();
    }

    protected ReservationRepository thereIsInMemoryReservationRepository() {
        return new InMemoryReservationRepository();
    }
}
