package shop.inahuy.admin.adapter.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;

public final class ApiRequests {

    private ApiRequests() {
    }

    public record ProductRequest(@NotBlank String type, @NotBlank String name,
                                 @NotNull @Positive BigDecimal price, @NotBlank String category) {
    }

    public record VoucherRequest(@NotBlank String code, @NotBlank String type,
                                 @NotNull @Positive BigDecimal discountValue,
                                 @NotNull Instant validUntil, int quota) {
    }

    public record MembershipPlanRequest(@NotBlank String name, @NotNull @Positive BigDecimal price,
                                        int durationDays, int discountPercent) {
    }

    public record StatusUpdateRequest(@NotBlank String status) {
    }
}