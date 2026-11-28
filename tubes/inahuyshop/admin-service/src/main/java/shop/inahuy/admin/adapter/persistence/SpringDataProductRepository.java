package shop.inahuy.admin.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.inahuy.admin.domain.Product;

interface SpringDataProductRepository extends JpaRepository<Product, Long> {
}