package projectHub.projectHub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectHub.projectHub.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
