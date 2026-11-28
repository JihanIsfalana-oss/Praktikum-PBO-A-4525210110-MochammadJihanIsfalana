package shop.inahuy.admin.domain;

import shop.inahuy.admin.exception.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    protected Product() {
    }

    protected Product(String name, BigDecimal price, String category, ProductStatus status) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.status = status;
    }

    public void update(String name, BigDecimal price, String category) {
        if (price.signum() <= 0) {
            throw new IllegalArgumentException("Harga produk harus lebih dari nol");
        }
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public void deactivate() {
        this.status = ProductStatus.INACTIVE;
    }

    public void ensurePurchasable() {
        if (!status.isPurchasable()) {
            throw new ProductNotActiveException(id);
        }
    }

    public abstract String deliveryType();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public ProductStatus getStatus() {
        return status;
    }
}