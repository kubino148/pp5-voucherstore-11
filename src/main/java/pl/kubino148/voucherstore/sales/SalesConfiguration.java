package pl.kubino148.voucherstore.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kubino148.payment.payu.PayU;
import pl.kubino148.payment.payu.PayUApiCredentials;
import pl.kubino148.payment.payu.http.NetHttpClientPayuHttp;
import pl.kubino148.voucherstore.productcatalog.ProductCatalogFacade;
import pl.kubino148.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.kubino148.voucherstore.sales.offer.OfferMaker;
import pl.kubino148.voucherstore.sales.ordering.ReservationRepository;
import pl.kubino148.voucherstore.sales.payment.PayUPaymentGateway;
import pl.kubino148.voucherstore.sales.payment.PaymentGateway;
import pl.kubino148.voucherstore.sales.productd.ProductCatalogProductDetailsProvider;
import pl.kubino148.voucherstore.sales.productd.ProductDetailsProvider;

import java.util.UUID;

@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(ProductCatalogFacade productCatalogFacade, OfferMaker offerMaker, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        var alwaysSameCustomer = UUID.randomUUID().toString();

        return new SalesFacade(
                new InMemoryBasketStorage(),
                productCatalogFacade,
                () -> alwaysSameCustomer,
                (productId) -> true,
                offerMaker,
                paymentGateway,
                reservationRepository
        );
    }

    @Bean
    PaymentGateway payUPaymentGateway(PayU payU) {
        return new PayUPaymentGateway(payU);
    }

    @Bean
    PayU payU() {
        return new PayU(
            PayUApiCredentials.sandbox(),
            new NetHttpClientPayuHttp()
        );
    }

    @Bean
    OfferMaker offerMaker(ProductDetailsProvider productDetailsProvider) {
        return new OfferMaker(productDetailsProvider);
    }

    @Bean
    ProductDetailsProvider productDetailsProvider(ProductCatalogFacade productCatalogFacade) {
        return new ProductCatalogProductDetailsProvider(productCatalogFacade);
    }
}
