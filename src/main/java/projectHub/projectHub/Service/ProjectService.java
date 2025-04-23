package projectHub.projectHub.Service;

import org.springframework.data.repository.query.Param;
import projectHub.projectHub.Entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project save(Project project);
    Optional<Project> findById(Integer id);
    List<Project> findAll();
    void deleteById(Integer id);
    List<Project> findByGroupId(Integer groupId);
    List<Project> findAllByUserId(@Param("userId") Integer userId);
}
