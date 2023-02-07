package study.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.invoice.domain.ProductInv;

@Repository
public interface ProductInvRepository extends JpaRepository<ProductInv, Long> {
}
