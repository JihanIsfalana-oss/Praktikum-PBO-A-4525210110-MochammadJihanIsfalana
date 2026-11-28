package shop.inahuy.admin.exception;

public class ProductNotFoundException extends RuntimeException {

    private final Long productId;

    public ProductNotFoundException(Long productId) {
        super("[NOT FOUND] Produk : " + productId);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}