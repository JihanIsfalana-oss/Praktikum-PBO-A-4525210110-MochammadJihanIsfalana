package shop.inahuy.admin.adapter.web;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import shop.inahuy.admin.adapter.web.ApiRequests.ProductRequest;
import shop.inahuy.admin.application.ProductService;

@RestController
public class ProductController {

    private final ProductService products;

    public ProductController(ProductService products) {
        this.products = products;
    }

    @GetMapping("/api/admin/products")
    public List<ProductResponse> list() {
        return products.list().stream().map(ProductResponse::from).toList();
    }

    @GetMapping("/api/admin/products/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return ProductResponse.from(products.get(id));
    }

    @PostMapping("/api/admin/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody ProductRequest request) {
        return ProductResponse.from(
                products.create(request.type(), request.name(), request.price(), request.category()));
    }

    @PutMapping("/api/admin/products/{id}")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return ProductResponse.from(
                products.update(id, request.name(), request.price(), request.category()));
    }

    @DeleteMapping("/api/admin/products/{id}")
    public ProductResponse deactivate(@PathVariable Long id) {
        return ProductResponse.from(products.deactivate(id));
    }

    @GetMapping("/api/internal/products")
    public List<ProductResponse> internalList() {
        return list();
    }

    @GetMapping("/api/internal/products/{id}")
    public ProductResponse internalGet(@PathVariable Long id) {
        return get(id);
    }
}