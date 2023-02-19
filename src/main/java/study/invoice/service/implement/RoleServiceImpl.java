package study.invoice.service.implement;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.invoice.domain.Role;
import study.invoice.enm.RoleName;
import study.invoice.repository.RoleRepository;
import study.invoice.service.RoleService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Boolean existsByName(RoleName name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public void create(Set<RoleName> roleNames) {
        Set<Role> roles = new HashSet<>();
        roleNames.forEach(item -> {
            roles.add(new Role(item));
        });
        roleRepository.saveAll(roles);
    }

}
