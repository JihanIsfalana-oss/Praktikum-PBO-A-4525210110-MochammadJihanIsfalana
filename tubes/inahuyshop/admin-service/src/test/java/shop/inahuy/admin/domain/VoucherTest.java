package shop.inahuy.admin.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import shop.inahuy.admin.exception.VoucherExpiredException;
import shop.inahuy.admin.exception.VoucherQuotaExceededException;

class VoucherTest {

    private static final Instant NOW = Instant.parse("2026-11-26T00:00:00Z");

    @Test
    void expiredVoucherIsRejectedWithExpiryDate() {
        Voucher voucher = new Voucher("LAMA", VoucherType.FIXED, BigDecimal.TEN,
                Instant.parse("2026-01-01T00:00:00Z"), 5);
        VoucherExpiredException ex = assertThrows(VoucherExpiredException.class,
                () -> voucher.assertUsable(NOW));
        assertEquals("LAMA", ex.getCode());
    }

    @Test
    void redeemFailsWhenQuotaIsExhausted() {
        Voucher voucher = new Voucher("HABIS", VoucherType.FIXED, BigDecimal.TEN,
                Instant.parse("2030-01-01T00:00:00Z"), 1);
        voucher.redeem(NOW);
        VoucherQuotaExceededException ex = assertThrows(VoucherQuotaExceededException.class,
                () -> voucher.redeem(NOW));
        assertEquals(1, ex.getQuota());
    }
}