package shop.inahuy.admin.application;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.inahuy.admin.domain.Product;
import shop.inahuy.admin.domain.ProductRepository;
import shop.inahuy.admin.domain.ProductType;
import shop.inahuy.admin.exception.ProductNotFoundException;

@Service
public class ProductService {

    private final ProductRepository products;

    public ProductService(ProductRepository products) {
        this.products = products;
    }

    public List<Product> list() {
        return products.findAll();
    }

    public Product get(Long id) {
        return products.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public Product create(String type, String name, BigDecimal price, String category) {
        Product product = ProductType.valueOf(type).create(name, price, category);
        return products.save(product);
    }

    @Transactional
    public Product update(Long id, String name, BigDecimal price, String category) {
        Product product = get(id);
        product.update(name, price, category);
        return products.save(product);
    }

    @Transactional
    public Product deactivate(Long id) {
        Product product = get(id);
        product.deactivate();
        return products.save(product);
    }
}