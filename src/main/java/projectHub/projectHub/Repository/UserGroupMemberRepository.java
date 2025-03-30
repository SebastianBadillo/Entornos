package projectHub.projectHub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectHub.projectHub.Entity.UserGroupMember;

import java.util.List;

public interface UserGroupMemberRepository extends JpaRepository<UserGroupMember, Integer> {
    List<UserGroupMember> findByGroupId(Integer groupId);
    List<UserGroupMember> findByUserId(Integer userId);
}
