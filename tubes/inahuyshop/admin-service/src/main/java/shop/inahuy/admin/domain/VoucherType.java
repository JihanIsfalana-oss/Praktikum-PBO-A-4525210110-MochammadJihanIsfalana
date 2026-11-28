package shop.inahuy.admin.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum VoucherType {
    PERCENT {
        @Override
        BigDecimal rawDiscount(BigDecimal total, BigDecimal value) {
            return total.multiply(value).divide(HUNDRED, 2, RoundingMode.HALF_UP);
        }
    },
    FIXED {
        @Override
        BigDecimal rawDiscount(BigDecimal total, BigDecimal value) {
            return value;
        }
    };

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    abstract BigDecimal rawDiscount(BigDecimal total, BigDecimal value);

    public BigDecimal apply(BigDecimal total, BigDecimal value) {
        BigDecimal discount = rawDiscount(total, value);
        if (discount.signum() < 0) {
            return BigDecimal.ZERO;
        }
        return discount.min(total);
    }
}