package shop.inahuy.admin.adapter.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import shop.inahuy.admin.domain.Product;
import shop.inahuy.admin.domain.ProductRepository;

@Repository
public class JpaProductRepository implements ProductRepository {

    private final SpringDataProductRepository delegate;

    public JpaProductRepository(SpringDataProductRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return delegate.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return delegate.findAll();
    }

    @Override
    public Product save(Product product) {
        return delegate.save(product);
    }
}