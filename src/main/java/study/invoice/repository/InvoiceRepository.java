package study.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.invoice.domain.Invoice;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByComIDAndPattern(Long comID, String pattern);

    Optional<Invoice> findByIdAndComID(Long id, Long comID);
}
