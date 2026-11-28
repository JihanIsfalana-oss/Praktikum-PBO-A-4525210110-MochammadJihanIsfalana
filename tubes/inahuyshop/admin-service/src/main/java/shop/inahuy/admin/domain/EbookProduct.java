package shop.inahuy.admin.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("EBOOK")
public class EbookProduct extends Product {

    protected EbookProduct() {
    }

    public EbookProduct(String name, BigDecimal price, String category, ProductStatus status) {
        super(name, price, category, status);
    }

    @Override
    public String deliveryType() {
        return "PDF_READ";
    }
}