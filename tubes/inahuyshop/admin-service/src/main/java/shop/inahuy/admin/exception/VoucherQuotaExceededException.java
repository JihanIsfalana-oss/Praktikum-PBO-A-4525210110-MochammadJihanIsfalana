package shop.inahuy.admin.exception;

public class VoucherQuotaExceededException extends RuntimeException {

    private final String code;
    private final int quota;

    public VoucherQuotaExceededException(String code, int quota) {
        super("[QUOTA EXCEEDED] Kuota voucher " + code + " sudah habis (" + quota + ")");
        this.code = code;
        this.quota = quota;
    }

    public String getCode() {
        return code;
    }

    public int getQuota() {
        return quota;
    }
}