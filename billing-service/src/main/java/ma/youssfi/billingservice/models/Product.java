package ma.youssfi.billingservice.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}