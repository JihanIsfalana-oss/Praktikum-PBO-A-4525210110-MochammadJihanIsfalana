package shop.inahuy.admin.adapter.web;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.inahuy.admin.adapter.web.ApiRequests.StatusUpdateRequest;
import shop.inahuy.admin.application.BusinessSummary;
import shop.inahuy.admin.application.MonitoringService;
import shop.inahuy.admin.application.OrderView;

@RestController
@RequestMapping("/api/admin")
public class MonitoringController {

    private final MonitoringService monitoring;

    public MonitoringController(MonitoringService monitoring) {
        this.monitoring = monitoring;
    }

    @GetMapping("/monitoring/summary")
    public BusinessSummary summary() {
        return monitoring.summary();
    }

    @GetMapping("/orders")
    public List<OrderView> orders() {
        return monitoring.orders();
    }

    @PatchMapping("/orders/{id}/status")
    public OrderView updateStatus(@PathVariable Long id, @Valid @RequestBody StatusUpdateRequest request) {
        return monitoring.updateOrderStatus(id, request.status());
    }
}