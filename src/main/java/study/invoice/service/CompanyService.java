package study.invoice.service;

import study.invoice.domain.Company;
import study.invoice.domain.User;
import study.invoice.dto.CompanyDTO;
import study.invoice.security.userpincal.UserPrinciple;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<CompanyDTO> getCompanyByUser(User user);

    CompanyDTO getCompanyById(Long id, User user);

    Company createParentCompany(CompanyDTO companyDTO);

    Company createCompany(CompanyDTO companyDTO);

    Company updateCompany(CompanyDTO companyDTO, User user);

    void deleteMultiple(List<Long> ids, User user);

    void delete(Long id, User user);
}
