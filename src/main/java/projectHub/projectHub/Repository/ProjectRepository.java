package projectHub.projectHub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectHub.projectHub.Entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByGroupId(Integer projectId);
    List<Project> findByStatus(Project.Status status);
}
