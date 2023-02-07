package study.invoice.service;

import study.invoice.domain.User;
import study.invoice.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<User> findByName(String login);
    Boolean existsByLogin(String login);
    User createUser(UserDTO userDTO);
}
