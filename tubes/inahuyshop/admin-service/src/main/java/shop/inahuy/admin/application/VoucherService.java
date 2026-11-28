package shop.inahuy.admin.application;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.inahuy.admin.domain.Voucher;
import shop.inahuy.admin.domain.VoucherRepository;
import shop.inahuy.admin.domain.VoucherType;
import shop.inahuy.admin.exception.VoucherNotFoundException;

@Service
public class VoucherService {

    private final VoucherRepository vouchers;
    private final Clock clock;

    public VoucherService(VoucherRepository vouchers, Clock clock) {
        this.vouchers = vouchers;
        this.clock = clock;
    }

    public List<Voucher> list() {
        return vouchers.findAll();
    }

    @Transactional
    public Voucher create(String code, String type, BigDecimal value, Instant validUntil, int quota) {
        return vouchers.save(new Voucher(code, VoucherType.valueOf(type), value, validUntil, quota));
    }

    public Voucher validate(String code) {
        Voucher voucher = find(code);
        voucher.assertUsable(clock.instant());
        return voucher;
    }

    @Transactional
    public Voucher redeem(String code) {
        Voucher voucher = find(code);
        voucher.redeem(clock.instant());
        return vouchers.save(voucher);
    }

    private Voucher find(String code) {
        return vouchers.findByCode(code).orElseThrow(() -> new VoucherNotFoundException(code));
    }
}