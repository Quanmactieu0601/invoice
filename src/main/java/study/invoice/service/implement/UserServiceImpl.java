package study.invoice.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.invoice.domain.Role;
import study.invoice.domain.User;
import study.invoice.dto.UserDTO;
import study.invoice.enm.RoleName;
import study.invoice.repository.UserRepository;
import study.invoice.service.UserService;
import study.invoice.service.error.NotFoundUserName;
import study.invoice.service.error.UsernameAlreadyUsedException;

import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
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
}
