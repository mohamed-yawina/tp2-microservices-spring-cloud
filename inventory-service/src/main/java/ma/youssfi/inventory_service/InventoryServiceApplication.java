package ma.youssfi.inventoryservice;

import ma.youssfi.inventoryservice.entities.Product;
import ma.youssfi.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository repository) {
		return args -> {
			repository.save(Product.builder()
					.name("Computer").price(8500).quantity(10).build());

			repository.save(Product.builder()
					.name("Printer").price(2500).quantity(15).build());

			repository.save(Product.builder()
					.name("Smartphone").price(4200).quantity(20).build());

			repository.findAll().forEach(System.out::println);
		};
	}
}