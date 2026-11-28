package shop.inahuy.admin.application;

import java.math.BigDecimal;

public record OrderView(Long id, Long customerId, String status, BigDecimal total) {
}