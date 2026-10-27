package ma.youssfi.inventoryservice.repositories;

import ma.youssfi.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}