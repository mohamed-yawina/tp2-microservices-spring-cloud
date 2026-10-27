package ma.youssfi.billingservice.repositories;

import ma.youssfi.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository
        extends JpaRepository<ProductItem, Long> {
}