package study.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.invoice.domain.InvoiceTemplate;

import java.util.List;

@Repository
public interface InvoiceTemplateRepository extends JpaRepository<InvoiceTemplate, Long> {
    List<InvoiceTemplate> getByComID(Long comID);
    Boolean existsByComIDAndPattern(Long comID, String pattern);
}
