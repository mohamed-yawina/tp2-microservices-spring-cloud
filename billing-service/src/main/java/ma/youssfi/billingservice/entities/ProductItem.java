package ma.youssfi.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Bill bill;
}