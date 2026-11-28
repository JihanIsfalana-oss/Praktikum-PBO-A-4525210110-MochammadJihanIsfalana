package shop.inahuy.admin.domain;

public enum ProductStatus {
    ACTIVE,
    INACTIVE;

    public boolean isPurchasable() {
        return this == ACTIVE;
    }
}