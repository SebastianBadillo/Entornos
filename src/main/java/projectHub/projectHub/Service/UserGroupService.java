package projectHub.projectHub.Service;

import projectHub.projectHub.Entity.UserGroup;

import java.util.List;
import java.util.Optional;

public interface UserGroupService {
    UserGroup save(UserGroup group);
    Optional<UserGroup> findById(Integer id);
    List<UserGroup> findAll();
    void deleteById(Integer id);
}
