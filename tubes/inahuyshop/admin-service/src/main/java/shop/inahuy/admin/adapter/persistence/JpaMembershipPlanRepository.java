package shop.inahuy.admin.adapter.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import shop.inahuy.admin.domain.MembershipPlan;
import shop.inahuy.admin.domain.MembershipPlanRepository;

@Repository
public class JpaMembershipPlanRepository implements MembershipPlanRepository {

    private final SpringDataMembershipPlanRepository delegate;

    public JpaMembershipPlanRepository(SpringDataMembershipPlanRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<MembershipPlan> findById(Long id) {
        return delegate.findById(id);
    }

    @Override
    public List<MembershipPlan> findAll() {
        return delegate.findAll();
    }

    @Override
    public MembershipPlan save(MembershipPlan plan) {
        return delegate.save(plan);
    }
}