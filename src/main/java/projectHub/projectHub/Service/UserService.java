package projectHub.projectHub.Service;

import projectHub.projectHub.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Integer userId);
    List<User> findAll();
    void deleteById(Integer userId);
    Optional<User> findByEmail(String email);
}
