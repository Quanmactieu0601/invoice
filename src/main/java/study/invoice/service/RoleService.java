package study.invoice.service;

import study.invoice.domain.Role;
import study.invoice.enm.RoleName;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Optional<Role> findByName(RoleName name);
    Boolean existsByName(RoleName name);
    void create(Set<RoleName> roleNames);
}
