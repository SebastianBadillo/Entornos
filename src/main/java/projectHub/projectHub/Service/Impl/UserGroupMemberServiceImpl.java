package projectHub.projectHub.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectHub.projectHub.Entity.UserGroupMember;
import projectHub.projectHub.Repository.UserGroupMemberRepository;
import projectHub.projectHub.Service.UserGroupMemberService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupMemberServiceImpl implements UserGroupMemberService {
    private final UserGroupMemberRepository repository;

    @Override
    public UserGroupMember save(UserGroupMember member) {
        return repository.save(member);
    }

    @Override
    public void delete(UserGroupMember member) {
        repository.delete(member);
    }

    @Override
    public List<UserGroupMember> findByUserId(Integer userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<UserGroupMember> findByGroupId(Integer groupId) {
        return repository.findByGroupId(groupId);
    }
}
