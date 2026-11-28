package shop.inahuy.admin.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class VoucherTypeTest {

    @Test
    void percentTakesTenPercentOfTotal() {
        assertEquals(0, new BigDecimal("10000.00").compareTo(
                VoucherType.PERCENT.apply(new BigDecimal("100000"), new BigDecimal("10"))));
    }

    @Test
    void fixedDiscountIsCappedAtTotal() {
        assertEquals(0, new BigDecimal("15000").compareTo(
                VoucherType.FIXED.apply(new BigDecimal("15000"), new BigDecimal("20000"))));
    }
}