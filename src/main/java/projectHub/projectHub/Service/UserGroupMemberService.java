package projectHub.projectHub.Service;

import projectHub.projectHub.Entity.UserGroupMember;

import java.util.List;

public interface UserGroupMemberService {
    UserGroupMember save(UserGroupMember member);
    void delete(UserGroupMember member);
    List<UserGroupMember> findByUserId(Integer userId);
    List<UserGroupMember> findByGroupId(Integer groupId);
}
