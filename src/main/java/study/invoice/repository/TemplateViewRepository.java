package study.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.invoice.domain.TemplateView;

@Repository
public interface TemplateViewRepository extends JpaRepository<TemplateView, Long> {
}
