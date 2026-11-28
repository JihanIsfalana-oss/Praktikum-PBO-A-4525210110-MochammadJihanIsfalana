package shop.inahuy.admin.application;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.inahuy.admin.domain.MembershipPlan;
import shop.inahuy.admin.domain.MembershipPlanRepository;

@Service
public class MembershipService {

    private final MembershipPlanRepository plans;

    public MembershipService(MembershipPlanRepository plans) {
        this.plans = plans;
    }

    public List<MembershipPlan> list() {
        return plans.findAll();
    }

    public MembershipPlan get(Long id) {
        return plans.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("[NOT FOUND] Paket : " + id));
    }

    @Transactional
    public MembershipPlan create(String name, BigDecimal price, int durationDays, int discountPercent) {
        return plans.save(new MembershipPlan(name, price, durationDays, discountPercent));
    }
}