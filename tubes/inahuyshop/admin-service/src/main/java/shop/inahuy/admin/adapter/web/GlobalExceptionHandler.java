package shop.inahuy.admin.adapter.web;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.inahuy.admin.exception.CustomerServiceUnavailableException;
import shop.inahuy.admin.exception.OrderNotFoundException;
import shop.inahuy.admin.exception.ProductNotActiveException;
import shop.inahuy.admin.exception.ProductNotFoundException;
import shop.inahuy.admin.exception.VoucherExpiredException;
import shop.inahuy.admin.exception.VoucherNotFoundException;
import shop.inahuy.admin.exception.VoucherQuotaExceededException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> productNotFound(ProductNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage(), "productId", ex.getProductId());
    }

    @ExceptionHandler(ProductNotActiveException.class)
    public ResponseEntity<Map<String, Object>> productNotActive(ProductNotActiveException ex) {
        return error(HttpStatus.CONFLICT, ex.getMessage(), "productId", ex.getProductId());
    }

    @ExceptionHandler(VoucherNotFoundException.class)
    public ResponseEntity<Map<String, Object>> voucherNotFound(VoucherNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage(), "code", ex.getCode());
    }

    @ExceptionHandler(VoucherExpiredException.class)
    public ResponseEntity<Map<String, Object>> voucherExpired(VoucherExpiredException ex) {
        return error(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), "expiredAt", ex.getExpiredAt().toString());
    }

    @ExceptionHandler(VoucherQuotaExceededException.class)
    public ResponseEntity<Map<String, Object>> voucherQuota(VoucherQuotaExceededException ex) {
        return error(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), "quota", ex.getQuota());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, Object>> orderNotFound(OrderNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage(), "orderId", ex.getOrderId());
    }

    @ExceptionHandler(CustomerServiceUnavailableException.class)
    public ResponseEntity<Map<String, Object>> customerUnavailable(CustomerServiceUnavailableException ex) {
        return error(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage(), "service", "customer-service");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> invalid(MethodArgumentNotValidException ex) {
        return error(HttpStatus.BAD_REQUEST, "Validasi input gagal", "reason", "VALIDATION_FAILED");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> badRequest(IllegalArgumentException ex) {
        return error(HttpStatus.BAD_REQUEST, ex.getMessage(), "reason", "BAD_REQUEST");
    }

    private ResponseEntity<Map<String, Object>> error(HttpStatus status, String message, String key, Object value) {
        return ResponseEntity.status(status).body(Map.of("message", message, key, value));
    }
}