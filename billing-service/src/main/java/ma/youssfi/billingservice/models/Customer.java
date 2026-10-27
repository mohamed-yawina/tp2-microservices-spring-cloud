package ma.youssfi.billingservice.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String email;
}