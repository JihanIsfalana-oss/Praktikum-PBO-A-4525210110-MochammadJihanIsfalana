package shop.inahuy.admin.domain;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository {
    Optional<Voucher> findByCode(String code);
    List<Voucher> findAll();
    Voucher save(Voucher voucher);
}