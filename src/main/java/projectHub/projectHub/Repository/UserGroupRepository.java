package projectHub.projectHub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectHub.projectHub.Entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {
}
