package ma.youssfi.billingservice.web;

import ma.youssfi.billingservice.entities.Bill;
import ma.youssfi.billingservice.entities.ProductItem;
import ma.youssfi.billingservice.feign.CustomerRestClient;
import ma.youssfi.billingservice.feign.InventoryRestClient;
import ma.youssfi.billingservice.models.Customer;
import ma.youssfi.billingservice.models.Product;
import ma.youssfi.billingservice.repositories.BillRepository;
import ma.youssfi.billingservice.repositories.ProductItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bills")
public class BillRestController {

    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClient customerClient;
    private final InventoryRestClient inventoryClient;

    public BillRestController(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClient customerClient,
            InventoryRestClient inventoryClient) {

        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
    }

    @GetMapping
    public List<Map<String, Object>> getAllBills() {
        return billRepository.findAll().stream()
                .map(this::buildBillResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getBillById(@PathVariable Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Facture introuvable"));

        return buildBillResponse(bill);
    }

    private Map<String, Object> buildBillResponse(Bill bill) {

        Customer customer = customerClient.getCustomerById(
                bill.getCustomerId());

        List<ProductItem> items = productItemRepository.findAll()
                .stream()
                .filter(item -> item.getBill().getId().equals(bill.getId()))
                .toList();

        List<Map<String, Object>> details = items.stream().map(item -> {
            Product product = inventoryClient.getProductById(
                    item.getProductId());

            Map<String, Object> detail = new LinkedHashMap<>();
            detail.put("product", product);
            detail.put("price", item.getPrice());
            detail.put("quantity", item.getQuantity());
            detail.put("amount", item.getPrice() * item.getQuantity());
            return detail;
        }).toList();

        double total = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("id", bill.getId());
        result.put("billingDate", bill.getBillingDate());
        result.put("customer", customer);
        result.put("productItems", details);
        result.put("total", total);

        return result;
    }
}