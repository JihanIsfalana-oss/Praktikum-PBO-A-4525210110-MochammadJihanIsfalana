package shop.inahuy.admin.domain;

import static org.junit.jupiter.api.Assertions.*;

import shop.inahuy.admin.exception.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void inactiveProductCannotBePurchased() {
        Product product = ProductType.EBOOK.create("Lama", new BigDecimal("1000"), "Umum");
        product.deactivate();
        assertThrows(ProductNotActiveException.class, product::ensurePurchasable);
    }

    @Test
    void factoryCreatesSubtypeWithItsDeliveryType() {
        Product template = ProductType.TEMPLATE.create("Template", new BigDecimal("45000"), "Bisnis");
        assertInstanceOf(TemplateProduct.class, template);
        assertEquals("DOWNLOAD_ZIP", template.deliveryType());
    }
}