package shop.inahuy.admin.application;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import shop.inahuy.admin.exception.CustomerServiceUnavailableException;
import shop.inahuy.admin.exception.OrderNotFoundException;

@Component
public class CustomerOrderClient {

    private final RestClient restClient;

    public CustomerOrderClient(RestClient.Builder builder, @Value("${customer.base-url}") String baseUrl) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(2));
        factory.setReadTimeout(Duration.ofSeconds(3));
        this.restClient = builder.baseUrl(baseUrl).requestFactory(factory).build();
    }

    public BusinessSummary fetchSummary() {
        return call(() -> restClient.get()
                .uri("/api/internal/orders/summary")
                .retrieve()
                .body(BusinessSummary.class));
    }

    public List<OrderView> fetchOrders() {
        return call(() -> restClient.get()
                .uri("/api/internal/orders")
                .retrieve()
                .body(new ParameterizedTypeReference<List<OrderView>>() {}));
    }

    public OrderView updateStatus(Long orderId, String status) {
        return call(() -> restClient.patch()
                .uri("/api/internal/orders/{id}/status", orderId)
                .body(Map.of("status", status))
                .retrieve()
                .onStatus(s -> s.value() == 404, (req, res) -> {
                    throw new OrderNotFoundException(orderId);
                })
                .body(OrderView.class));
    }

    private <T> T call(Supplier<T> action) {
        try {
            return action.get();
        } catch (ResourceAccessException ex) {
            throw new CustomerServiceUnavailableException(
                    "customer-service tidak dapat dihubungi: " + ex.getMessage());
        } catch (HttpServerErrorException ex) {
            throw new CustomerServiceUnavailableException(
                    "customer-service mengembalikan " + ex.getStatusCode());
        }
    }
}