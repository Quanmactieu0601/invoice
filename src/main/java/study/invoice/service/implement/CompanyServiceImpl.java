package study.invoice.service.implement;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.invoice.domain.Company;
import study.invoice.domain.User;
import study.invoice.dto.CompanyDTO;
import study.invoice.dto.UserDTO;
import study.invoice.enm.RoleName;
import study.invoice.repository.CompanyRepository;
import study.invoice.security.userpincal.UserPrinciple;
import study.invoice.service.CompanyService;
import study.invoice.service.Mapping.MappingHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserServiceImpl userService;

    @Override
    public List<CompanyDTO> getCompanyByUser(User user) {
        List<RoleName> authorities = user.getAuthorities().stream().map(role -> role.getName()).toList();
        List<Company> companyList = new ArrayList<>();
        if(authorities.contains(RoleName.ROOT)){
            companyList = companyRepository.findAll();
        }
        else{
            Company currenCom = companyRepository.findById(user.getComID()).orElseThrow( () -> new RuntimeException("Can not get curren Company"));
            companyList.add(currenCom);
            if(authorities.contains(RoleName.SUPER_ADMIN)){
                List<Company> childrenCompany = companyRepository.getByParentComID(currenCom.getId());
                companyList.addAll(childrenCompany);
            }
        }
        return MappingHelper.map(companyList, CompanyDTO.class);
    }

    @Override
    public CompanyDTO getCompanyById(Long id, User user) {
        List<RoleName> authorities = user.getAuthorities().stream().map(role -> role.getName()).toList();
        Company company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found Company"));

        if(!authorities.contains(RoleName.ROOT)){
            if(authorities.contains(RoleName.SUPER_ADMIN)){
                if(company.getId() != user.getComID() && company.getParentComID() != user.getComID()){
                    throw new RuntimeException("Your account do not have permission to access this company");
                }
            }
            else{
                if (company.getId() != user.getComID()){
                    throw new RuntimeException("Your account do not have permission to access this company");
                }
            }
        }
        return MappingHelper.map(company, CompanyDTO.class);
    }

    public Company createParentCompany(CompanyDTO companyDTO) {
        Company newCom = new Company();
        newCom.setName(companyDTO.getName());
        newCom.setComTaxCode(companyDTO.getComTaxCode());
        newCom.setAddress(companyDTO.getAddress());
        newCom.setEmail(companyDTO.getEmail());
        newCom.setPhone(companyDTO.getPhone());
        newCom.setParentComID(null);
        newCom = companyRepository.save(newCom);

        this.createSuperAdmin(newCom);
        this.createAdmin(newCom);
        return newCom;
    }

    public Company createCompany(CompanyDTO companyDTO) {
        Company newCom = new Company();
        newCom.setName(companyDTO.getName());
        newCom.setComTaxCode(companyDTO.getComTaxCode());
        newCom.setAddress(companyDTO.getAddress());
        newCom.setEmail(companyDTO.getEmail());
        newCom.setPhone(companyDTO.getPhone());
        newCom.setParentComID(null);
        newCom = companyRepository.save(newCom);

        this.createAdmin(newCom);
        return newCom;
    }

    @Override
    public Company updateCompany(CompanyDTO companyDTO, User user) {
        Company company = companyRepository.findById(companyDTO.getId()).orElseThrow(
                () -> new RuntimeException("Can not found this company")
        );
        if(!this.checkRole(company, user)){
            throw new RuntimeException("Your account do not have permission to access this company");
        }

        company.setName(companyDTO.getName());
        company.setComTaxCode(companyDTO.getComTaxCode());
        company.setAddress(companyDTO.getAddress());
        company.setEmail(companyDTO.getEmail());
        company.setPhone(companyDTO.getPhone());
        company.setParentComID(null);
        company = companyRepository.save(company);

        return company;
    }

    @Override
    public void deleteMultiple(List<Long> ids, User user) {
        List<Company> companyList = companyRepository.findAllById(ids);
        List<Company> errCom = companyList.stream().map(
                com -> {
                    if(!this.checkRole(com, user)) {
                        return com;
                    }
                    else{
                        return null;
                    }
                }).collect(Collectors.toList());

        if(errCom != null || errCom.stream().count() > 0){
            throw new RuntimeException(String.format("Your account do not have permission to access company have id in {0}", StringUtils.join(errCom.stream().map(com -> com.getId()).collect(Collectors.toList()), ",")));
        }

        companyRepository.deleteAllById(ids);
    }

    @Override
    public void delete(Long id, User user) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can not found this company")
        );
        if(!this.checkRole(company, user)){
            throw new RuntimeException("Your account do not have permission to access this company");
        }
        companyRepository.delete(company);
    }

    private void createAdmin(Company newCom) {
        UserDTO admin = new UserDTO();
        admin.setLogin("admin_" + newCom.getComTaxCode());
        admin.setFirstName("admin");
        admin.setPassword("admin" + newCom.getComTaxCode());
        admin.setAuthorities(new HashSet<String>(Arrays.asList(RoleName.ADMIN.toString())));

        userService.createUser(admin);
    }

    private void createSuperAdmin(Company newCom) {
        UserDTO sAdmin = new UserDTO();
        sAdmin.setLogin("sadmin_" + newCom.getComTaxCode());
        sAdmin.setFirstName("sadmin");
        sAdmin.setPassword("sadmin" + newCom.getComTaxCode());
        sAdmin.setAuthorities(new HashSet<String>(Arrays.asList(RoleName.SUPER_ADMIN.toString())));

        userService.createUser(sAdmin);
    }

    private boolean checkRole(Company com, User user){
        List<RoleName> authorities = user.getAuthorities().stream().map(role -> role.getName()).toList();
        if(!authorities.contains(RoleName.ROOT)){
            if(authorities.contains(RoleName.SUPER_ADMIN)){
                if(com.getId() != user.getComID() && com.getParentComID() != user.getComID()){
                    return false;
                }
            }
            else if(authorities.contains(RoleName.ADMIN)){
                if (com.getId() != user.getComID()){
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return true;
    }
}
