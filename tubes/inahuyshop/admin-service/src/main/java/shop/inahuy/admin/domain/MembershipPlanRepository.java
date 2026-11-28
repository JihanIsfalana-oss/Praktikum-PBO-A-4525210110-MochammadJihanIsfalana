package shop.inahuy.admin.domain;

import java.util.List;
import java.util.Optional;

public interface MembershipPlanRepository {
    Optional<MembershipPlan> findById(Long id);
    List<MembershipPlan> findAll();
    MembershipPlan save(MembershipPlan plan);
}