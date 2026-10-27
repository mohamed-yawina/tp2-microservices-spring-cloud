package ma.youssfi.billingservice.repositories;

import ma.youssfi.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}