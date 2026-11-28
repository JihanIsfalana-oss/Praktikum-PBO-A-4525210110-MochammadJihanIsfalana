package shop.inahuy.admin.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import shop.inahuy.admin.exception.VoucherExpiredException;
import shop.inahuy.admin.exception.VoucherQuotaExceededException;

@Entity
@Table(name = "vouchers")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoucherType type;

    @Column(name = "discount_value", nullable = false, precision = 12, scale = 2)
    private BigDecimal discountValue;

    @Column(name = "valid_until", nullable = false)
    private Instant validUntil;

    @Column(nullable = false)
    private int quota;

    @Column(name = "used_count", nullable = false)
    private int usedCount;

    protected Voucher() {
    }

    public Voucher(String code, VoucherType type, BigDecimal discountValue, Instant validUntil, int quota) {
        this.code = code;
        this.type = type;
        this.discountValue = discountValue;
        this.validUntil = validUntil;
        this.quota = quota;
        this.usedCount = 0;
    }

    public void assertUsable(Instant now) {
        if (now.isAfter(validUntil)) {
            throw new VoucherExpiredException(code, validUntil);
        }
        if (usedCount >= quota) {
            throw new VoucherQuotaExceededException(code, quota);
        }
    }

    public void redeem(Instant now) {
        assertUsable(now);
        usedCount++;
    }

    public BigDecimal discountFor(BigDecimal subtotal) {
        return type.apply(subtotal, discountValue);
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public VoucherType getType() {
        return type;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public int getQuota() {
        return quota;
    }

    public int getUsedCount() {
        return usedCount;
    }
}