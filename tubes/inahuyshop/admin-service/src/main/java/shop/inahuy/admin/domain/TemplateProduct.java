package shop.inahuy.admin.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("TEMPLATE")
public class TemplateProduct extends Product {

    protected TemplateProduct() {
    }

    public TemplateProduct(String name, BigDecimal price, String category, ProductStatus status) {
        super(name, price, category, status);
    }

    @Override
    public String deliveryType() {
        return "DOWNLOAD_ZIP";
    }
}