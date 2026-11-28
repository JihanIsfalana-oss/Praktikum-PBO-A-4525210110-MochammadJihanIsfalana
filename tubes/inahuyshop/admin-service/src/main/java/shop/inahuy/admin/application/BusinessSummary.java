package shop.inahuy.admin.application;

import java.math.BigDecimal;
import java.util.Map;

public record BusinessSummary(Map<String, Long> ordersByStatus, BigDecimal paidRevenue, long activeMembers) {
}