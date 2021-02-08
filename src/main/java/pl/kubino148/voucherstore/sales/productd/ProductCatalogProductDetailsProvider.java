package pl.kubino148.voucherstore.sales.productd;

import pl.kubino148.voucherstore.productcatalog.ProductCatalogFacade;

public class ProductCatalogProductDetailsProvider implements ProductDetailsProvider {
    private final ProductCatalogFacade productCatalogFacade;

    public ProductCatalogProductDetailsProvider(ProductCatalogFacade productCatalogFacade) {
        this.productCatalogFacade = productCatalogFacade;
    }

    @Override
    public ProductDetails getByProductId(String productId) {
        var product = productCatalogFacade.getById(productId);

        return new ProductDetails(
                product.getId(),
                product.getDescription(),
                product.getPrice());
    }
}
