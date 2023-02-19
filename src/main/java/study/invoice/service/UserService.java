package study.invoice.service;

import study.invoice.domain.User;
import study.invoice.dto.UserDTO;
import study.invoice.dto.request.ChangePasswordForm;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByName(String login);
    Boolean existsByLogin(String login);
    User createUser(UserDTO userDTO);

    List<UserDTO> getAllByComID(Long comID);

    UserDTO getByIdAndComID(Long id, Long comID);

    User updateUser(UserDTO userDTO, User user);

    void deleteMultiple(List<Long> ids, User user);

    void delete(Long id, User user);

    List<UserDTO> filter(UserDTO userDTO, User user);

    User changePassword(ChangePasswordForm changePasswordForm, User currenUser);
}
