package shop.inahuy.admin.adapter.web;

import java.math.BigDecimal;
import shop.inahuy.admin.domain.Product;

public record ProductResponse(Long id, String name, BigDecimal price, String category,
                              String status, String deliveryType) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(),
                product.getCategory(), product.getStatus().name(), product.deliveryType());
    }
}