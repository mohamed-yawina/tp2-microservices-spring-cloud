package ma.youssfi.inventoryservice.web;

import ma.youssfi.inventoryservice.entities.Product;
import ma.youssfi.inventoryservice.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductRepository repository;

    public ProductRestController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
    }
}