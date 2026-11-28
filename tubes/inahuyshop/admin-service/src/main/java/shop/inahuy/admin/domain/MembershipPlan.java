package shop.inahuy.admin.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "membership_plans")
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "duration_days", nullable = false)
    private int durationDays;

    @Column(name = "discount_percent", nullable = false)
    private int discountPercent;

    protected MembershipPlan() {
    }

    public MembershipPlan(String name, BigDecimal price, int durationDays, int discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Persentase diskon harus antara 0 dan 100");
        }
        this.name = name;
        this.price = price;
        this.durationDays = durationDays;
        this.discountPercent = discountPercent;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
}