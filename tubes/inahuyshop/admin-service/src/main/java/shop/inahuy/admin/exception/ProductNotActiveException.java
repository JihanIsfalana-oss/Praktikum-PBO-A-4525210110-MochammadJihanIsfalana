package shop.inahuy.admin.exception;

public class ProductNotActiveException extends RuntimeException {

    private final Long productId;

    public ProductNotActiveException(Long productId) {
        super("[NOT ACTIVE] Produk : " + productId);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}