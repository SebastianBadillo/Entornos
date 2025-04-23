package projectHub.projectHub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projectHub.projectHub.Entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByGroupId(Integer projectId);
    List<Project> findByStatus(Project.Status status);

    @Query(value = """
    SELECT p FROM Project p
    WHERE p.group.id IN (
        SELECT ugm.group.id FROM UserGroupMember ugm WHERE ugm.user.id = :userId
    )
    OR p.group.leader.id = :userId
    """)
    List<Project> findAllByUserId(@Param("userId") Integer userId);
}
