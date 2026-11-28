package shop.inahuy.admin.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.inahuy.admin.domain.MembershipPlan;

interface SpringDataMembershipPlanRepository extends JpaRepository<MembershipPlan, Long> {
}