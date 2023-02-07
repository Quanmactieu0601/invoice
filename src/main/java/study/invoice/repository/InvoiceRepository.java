package study.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.invoice.domain.Invoice;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {
}
