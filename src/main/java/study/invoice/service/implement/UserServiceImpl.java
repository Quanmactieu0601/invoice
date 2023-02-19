package study.invoice.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.invoice.domain.Company;
import study.invoice.domain.Role;
import study.invoice.domain.User;
import study.invoice.dto.UserDTO;
import study.invoice.dto.request.ChangePasswordForm;
import study.invoice.enm.RoleName;
import study.invoice.repository.CompanyRepository;
import study.invoice.repository.UserRepository;
import study.invoice.service.Mapping.MappingHelper;
import study.invoice.service.UserService;
import study.invoice.service.error.NotFoundUserName;
import study.invoice.service.error.UsernameAlreadyUsedException;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Optional<User> findByName(String login) {
        return this.userRepository.findByLogin(login);
    }

    @Override
    public Boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    public User createUser(UserDTO userDTO) {
        if(userDTO.getLogin() == null || userDTO.getLogin().trim() == ""){
            throw new NotFoundUserName();
        }
        if(existsByLogin(userDTO.getLogin())){
            throw new UsernameAlreadyUsedException();
        }

        User user = new User();
        user.setActivated(true);
        user.setComID(userDTO.getComID());
        user.setLogin(userDTO.getLogin());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCountry(userDTO.getCountry());
        user.setPhone(userDTO.getPhone());
        user.setPersonalID(userDTO.getPersonalID());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Set<Role> roles = new HashSet<Role>();
        userDTO.getAuthorities().forEach(auth -> {
            switch (auth.toUpperCase(Locale.ENGLISH)){
                case "ROOT":
                    Role rootRole = roleService.findByName(RoleName.ROOT).orElseThrow(() -> new RuntimeException("Role ROOT not found"));
                    roles.add(rootRole);
                    break;
                case "SUPER_ADMIN":
                    Role superAdminRole = roleService.findByName(RoleName.SUPER_ADMIN).orElseThrow(() -> new RuntimeException("Role SUPER ADMIN not found"));
                    roles.add(superAdminRole);
                    break;
                case "ADMIN":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role ADMIN not found"));
                    roles.add(adminRole);
                    break;
                case "STAFF":
                    Role staffRole = roleService.findByName(RoleName.STAFF).orElseThrow(() -> new RuntimeException("Role STAFF not found"));
                    roles.add(staffRole);
                    break;
                case "USER":
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role USER not found"));
                    roles.add(userRole);
                    break;
                default:
                    throw new RuntimeException("Can not support Role" + auth);
            }
        });
        user.setAuthorities(roles);
        userRepository.save(user);

        return user;
    }

    @Override
    public List<UserDTO> getAllByComID(Long comID) {
        List<User> userList = new ArrayList<>();
        if(comID == null){
            userList = userRepository.findAll();
        }
        else {
            userList = userRepository.findByComID(comID);
            List<Company> listCompany = companyRepository.getByParentComID(comID);
            for(Company com : listCompany ){
                userList.addAll(userRepository.findByComID(com.getId()));
            }
        }
        return MappingHelper.map(userList, UserDTO.class);
    }

    @Override
    public UserDTO getByIdAndComID(Long id, Long comID) {
        User user = userRepository.findByIdAndComID(id, comID).orElseThrow(() -> new RuntimeException("User không thuộc quản lý của công ty"));
        return MappingHelper.map(user, UserDTO.class);
    }

    @Override
    public User updateUser(UserDTO userDTO, User user) {
        User userUpdate = userRepository.findByLogin(userDTO.getLogin()).orElseThrow(() -> new RuntimeException("Không tồn tại user trong hệ thống"));

        userUpdate.setActivated(userDTO.isActivated());
        userUpdate.setFirstName(userDTO.getFirstName());
        userUpdate.setLastName(userDTO.getLastName());
        userUpdate.setEmail(userDTO.getEmail());
        userUpdate.setCountry(userDTO.getCountry());
        userUpdate.setPhone(userDTO.getPhone());
        userUpdate.setPersonalID(userDTO.getPersonalID());
        userUpdate.setLastModifiedBy(user.getLogin());
        userUpdate.setLastModifiedDate(LocalDateTime.now());
        Set<Role> roles = new HashSet<Role>();
        userDTO.getAuthorities().forEach(auth -> {
            switch (auth.toUpperCase(Locale.ENGLISH)){
                case "ROOT":
                    Role rootRole = roleService.findByName(RoleName.ROOT).orElseThrow(() -> new RuntimeException("Role ROOT not found"));
                    roles.add(rootRole);
                    break;
                case "SUPER_ADMIN":
                    Role superAdminRole = roleService.findByName(RoleName.SUPER_ADMIN).orElseThrow(() -> new RuntimeException("Role SUPER ADMIN not found"));
                    roles.add(superAdminRole);
                    break;
                case "ADMIN":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role ADMIN not found"));
                    roles.add(adminRole);
                    break;
                case "STAFF":
                    Role staffRole = roleService.findByName(RoleName.STAFF).orElseThrow(() -> new RuntimeException("Role STAFF not found"));
                    roles.add(staffRole);
                    break;
                case "USER":
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role USER not found"));
                    roles.add(userRole);
                    break;
                default:
                    throw new RuntimeException("Can not support Role " + auth);
            }
        });
        userUpdate.setAuthorities(roles);
        userRepository.save(userUpdate);

        return userUpdate;
    }

    @Override
    public void deleteMultiple(List<Long> ids, User user) {
        List<User> userList = userRepository.findAllById(ids);
        if(userList.stream().anyMatch( u -> u.getComID() != user.getComID())){
            throw new RuntimeException("Tồn tại User không thuộc quyền quản lý của công ty");
        }

        userRepository.deleteAllInBatch(userList);
    }

    @Override
    public void delete(Long id, User user) {
        User userDelete = userRepository.getById(id);
        if(userDelete != null){
            if(userDelete.getComID() != user.getComID()){
                throw new RuntimeException("User không thuộc quản lý của công ty");
            }
            userRepository.delete(userDelete);
        }
    }

    @Override
    public List<UserDTO> filter(UserDTO userDTO, User user) {
        return null;
    }

    @Override
    public User changePassword(ChangePasswordForm changePasswordForm, User currenUser) {
        if(currenUser.getPassword() != passwordEncoder.encode(changePasswordForm.getPassword())){
            throw new RuntimeException("Mật khẩu hiện tại không đúng");
        }
        if(changePasswordForm.getPassword() != changePasswordForm.getRemindPassword()){
            throw new RuntimeException("Mật khẩu không trùng khớp");
        }

        currenUser.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
        userRepository.save(currenUser);
        return currenUser;
    }
}
