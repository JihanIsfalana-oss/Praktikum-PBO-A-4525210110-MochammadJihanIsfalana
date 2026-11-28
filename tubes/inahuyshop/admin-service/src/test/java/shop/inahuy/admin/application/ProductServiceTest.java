package shop.inahuy.admin.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.inahuy.admin.domain.Product;
import shop.inahuy.admin.domain.ProductRepository;
import shop.inahuy.admin.domain.ProductStatus;
import shop.inahuy.admin.domain.ProductType;
import shop.inahuy.admin.exception.ProductNotFoundException;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository repository;

    @Test
    void missingProductThrowsNotFound() {
        when(repository.findById(42L)).thenReturn(Optional.empty());
        ProductService service = new ProductService(repository);

        ProductNotFoundException ex = assertThrows(ProductNotFoundException.class, () -> service.get(42L));
        assertEquals(42L, ex.getProductId().longValue());
    }

    @Test
    void deactivateSetsStatusInactiveAndSaves() {
        Product product = ProductType.EBOOK.create("Buku", new BigDecimal("50000"), "Umum");
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.save(product)).thenReturn(product);
        ProductService service = new ProductService(repository);

        Product result = service.deactivate(1L);

        assertEquals(ProductStatus.INACTIVE, result.getStatus());
        verify(repository).save(product);
    }
}