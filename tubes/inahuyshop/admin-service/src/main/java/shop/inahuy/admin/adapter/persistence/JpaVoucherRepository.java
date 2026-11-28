package shop.inahuy.admin.adapter.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import shop.inahuy.admin.domain.Voucher;
import shop.inahuy.admin.domain.VoucherRepository;

@Repository
public class JpaVoucherRepository implements VoucherRepository {

    private final SpringDataVoucherRepository delegate;

    public JpaVoucherRepository(SpringDataVoucherRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<Voucher> findByCode(String code) {
        return delegate.findByCode(code);
    }

    @Override
    public List<Voucher> findAll() {
        return delegate.findAll();
    }

    @Override
    public Voucher save(Voucher voucher) {
        return delegate.save(voucher);
    }
}