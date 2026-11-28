package shop.inahuy.admin.domain;

import java.math.BigDecimal;

public enum ProductType {
    EBOOK {
        @Override
        public Product create(String name, BigDecimal price, String category) {
            return new EbookProduct(name, price, category, ProductStatus.ACTIVE);
        }
    },
    TEMPLATE {
        @Override
        public Product create(String name, BigDecimal price, String category) {
            return new TemplateProduct(name, price, category, ProductStatus.ACTIVE);
        }
    },
    DESIGN_ASSET {
        @Override
        public Product create(String name, BigDecimal price, String category) {
            return new DesignAssetProduct(name, price, category, ProductStatus.ACTIVE);
        }
    };

    public abstract Product create(String name, BigDecimal price, String category);
}