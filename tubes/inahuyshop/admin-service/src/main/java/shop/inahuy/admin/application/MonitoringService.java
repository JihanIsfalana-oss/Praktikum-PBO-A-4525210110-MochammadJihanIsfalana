package shop.inahuy.admin.application;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MonitoringService {

    private final CustomerOrderClient customerOrders;

    public MonitoringService(CustomerOrderClient customerOrders) {
        this.customerOrders = customerOrders;
    }

    public BusinessSummary summary() {
        return customerOrders.fetchSummary();
    }

    public List<OrderView> orders() {
        return customerOrders.fetchOrders();
    }

    public OrderView updateOrderStatus(Long orderId, String status) {
        return customerOrders.updateStatus(orderId, status);
    }
}