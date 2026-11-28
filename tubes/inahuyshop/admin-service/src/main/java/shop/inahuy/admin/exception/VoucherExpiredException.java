package shop.inahuy.admin.exception;

import java.time.Instant;

public class VoucherExpiredException extends RuntimeException {

    private final String code;
    private final Instant expiredAt;

    public VoucherExpiredException(String code, Instant expiredAt) {
        super("[EXPIRED] Voucher " + code + " sudah kedaluwarsa pada " + expiredAt);
        this.code = code;
        this.expiredAt = expiredAt;
    }

    public String getCode() {
        return code;
    }

    public Instant getExpiredAt() {
        return expiredAt;
    }
}