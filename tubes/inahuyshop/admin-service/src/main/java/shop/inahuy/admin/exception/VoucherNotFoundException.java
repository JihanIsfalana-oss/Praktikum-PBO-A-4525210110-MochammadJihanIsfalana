package shop.inahuy.admin.exception;

public class VoucherNotFoundException extends RuntimeException {

    private final String code;

    public VoucherNotFoundException(String code) {
        super("[NOT FOUND] Voucher : " + code);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}