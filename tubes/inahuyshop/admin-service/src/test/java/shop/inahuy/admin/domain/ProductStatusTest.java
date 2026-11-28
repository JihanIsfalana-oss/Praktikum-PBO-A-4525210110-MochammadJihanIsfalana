package shop.inahuy.admin.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductStatusTest {

    @Test
    void onlyActiveIsPurchasable() {
        assertTrue(ProductStatus.ACTIVE.isPurchasable());
        assertFalse(ProductStatus.INACTIVE.isPurchasable());
    }
}