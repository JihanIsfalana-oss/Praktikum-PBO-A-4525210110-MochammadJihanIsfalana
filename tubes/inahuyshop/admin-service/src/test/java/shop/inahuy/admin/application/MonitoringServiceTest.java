package shop.inahuy.admin.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.inahuy.admin.exception.CustomerServiceUnavailableException;

@ExtendWith(MockitoExtension.class)
class MonitoringServiceTest {

    @Mock
    CustomerOrderClient client;

    @Test
    void summaryPassesThroughClientResult() {
        BusinessSummary expected = new BusinessSummary(Map.of("PAID", 3L), new BigDecimal("250000"), 1L);
        when(client.fetchSummary()).thenReturn(expected);

        assertSame(expected, new MonitoringService(client).summary());
    }

    @Test
    void customerOutagePropagatesAsUnavailable() {
        when(client.fetchSummary()).thenThrow(new CustomerServiceUnavailableException("down"));
        MonitoringService service = new MonitoringService(client);

        assertThrows(CustomerServiceUnavailableException.class, service::summary);
    }
}