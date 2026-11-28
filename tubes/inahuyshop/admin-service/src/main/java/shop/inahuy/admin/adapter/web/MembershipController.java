package shop.inahuy.admin.adapter.web;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import shop.inahuy.admin.adapter.web.ApiRequests.MembershipPlanRequest;
import shop.inahuy.admin.application.MembershipService;
import shop.inahuy.admin.domain.MembershipPlan;

@RestController
public class MembershipController {

    public record PlanResponse(Long id, String name, BigDecimal price,
                               int durationDays, int discountPercent) {

        static PlanResponse from(MembershipPlan p) {
            return new PlanResponse(p.getId(), p.getName(), p.getPrice(),
                    p.getDurationDays(), p.getDiscountPercent());
        }
    }

    private final MembershipService memberships;

    public MembershipController(MembershipService memberships) {
        this.memberships = memberships;
    }

    @GetMapping("/api/admin/membership-plans")
    public List<PlanResponse> list() {
        return memberships.list().stream().map(PlanResponse::from).toList();
    }

    @PostMapping("/api/admin/membership-plans")
    @ResponseStatus(HttpStatus.CREATED)
    public PlanResponse create(@Valid @RequestBody MembershipPlanRequest request) {
        return PlanResponse.from(memberships.create(request.name(), request.price(),
                request.durationDays(), request.discountPercent()));
    }

    @GetMapping("/api/internal/membership-plans/{id}")
    public PlanResponse internalGet(@PathVariable Long id) {
        return PlanResponse.from(memberships.get(id));
    }
}