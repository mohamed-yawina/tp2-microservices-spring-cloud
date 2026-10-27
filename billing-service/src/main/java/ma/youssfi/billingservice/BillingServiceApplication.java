package ma.youssfi.billingservice;

import ma.youssfi.billingservice.entities.Bill;
import ma.youssfi.billingservice.entities.ProductItem;
import ma.youssfi.billingservice.repositories.BillRepository;
import ma.youssfi.billingservice.repositories.ProductItemRepository;
import ma.youssfi.billingservice.feign.CustomerRestClient;
import ma.youssfi.billingservice.feign.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			BillRepository billRepository,
			ProductItemRepository productItemRepository,
			CustomerRestClient customerClient,
			InventoryRestClient inventoryClient) {

		return args -> {
			// Vérifier la communication avec customer-service
			var customer = customerClient.getCustomerById(1L);
			System.out.println("Customer : " + customer);

			// Vérifier la communication avec inventory-service
			var product1 = inventoryClient.getProductById(1L);
			var product2 = inventoryClient.getProductById(2L);

			// Créer une facture
			Bill bill = billRepository.save(
					Bill.builder()
							.billingDate(new Date())
							.customerId(customer.getId())
							.build()
			);

			// Ajouter deux lignes de produits
			productItemRepository.save(
					ProductItem.builder()
							.productId(product1.getId())
							.price(product1.getPrice())
							.quantity(2)
							.bill(bill)
							.build()
			);

			productItemRepository.save(
					ProductItem.builder()
							.productId(product2.getId())
							.price(product2.getPrice())
							.quantity(1)
							.bill(bill)
							.build()
			);

			System.out.println("Facture créée : " + bill.getId());
		};
	}
}