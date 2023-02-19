package study.invoice.service;

import study.invoice.domain.Invoice;
import study.invoice.domain.User;
import study.invoice.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDTO> getAllByComIDAndPattern(Long comID, String pattern);

    InvoiceDTO getByIdAndComID(Long id, Long comID);

    Invoice createNew(InvoiceDTO invoiceDTO, User user);

    Invoice updateInvoice(InvoiceDTO invoiceDTO, User user);

    void deleteMultiple(List<Long> ids, User user);

    void delete(Long id, User user);
}
