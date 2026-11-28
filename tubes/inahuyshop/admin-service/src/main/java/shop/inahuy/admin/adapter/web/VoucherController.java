package shop.inahuy.admin.adapter.web;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import shop.inahuy.admin.adapter.web.ApiRequests.VoucherRequest;
import shop.inahuy.admin.application.VoucherService;
import shop.inahuy.admin.domain.Voucher;

@RestController
public class VoucherController {

    public record VoucherResponse(String code, String type, BigDecimal discountValue,
                                  Instant validUntil, int quota, int usedCount) {

        static VoucherResponse from(Voucher v) {
            return new VoucherResponse(v.getCode(), v.getType().name(), v.getDiscountValue(),
                    v.getValidUntil(), v.getQuota(), v.getUsedCount());
        }
    }

    private final VoucherService vouchers;

    public VoucherController(VoucherService vouchers) {
        this.vouchers = vouchers;
    }

    @GetMapping("/api/admin/vouchers")
    public List<VoucherResponse> list() {
        return vouchers.list().stream().map(VoucherResponse::from).toList();
    }

    @PostMapping("/api/admin/vouchers")
    @ResponseStatus(HttpStatus.CREATED)
    public VoucherResponse create(@Valid @RequestBody VoucherRequest request) {
        return VoucherResponse.from(vouchers.create(request.code(), request.type(),
                request.discountValue(), request.validUntil(), request.quota()));
    }

    @GetMapping("/api/internal/vouchers/{code}")
    public VoucherResponse validate(@PathVariable String code) {
        return VoucherResponse.from(vouchers.validate(code));
    }

    @PostMapping("/api/internal/vouchers/{code}/redeem")
    public VoucherResponse redeem(@PathVariable String code) {
        return VoucherResponse.from(vouchers.redeem(code));
    }
}