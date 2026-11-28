package shop.inahuy.admin.adapter.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.inahuy.admin.domain.Voucher;

interface SpringDataVoucherRepository extends JpaRepository<Voucher, Long> {

    Optional<Voucher> findByCode(String code);
}