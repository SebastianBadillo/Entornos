package projectHub.projectHub.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectHub.projectHub.Entity.UserGroup;
import projectHub.projectHub.Repository.UserGroupRepository;
import projectHub.projectHub.Service.UserGroupService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGroupServiceImpl implements UserGroupService {
    private final UserGroupRepository userGroupRepository;

    @Override
    public UserGroup save(UserGroup group) {
        return userGroupRepository.save(group);
    }

    @Override
    public Optional<UserGroup> findById(Integer id) {
        return userGroupRepository.findById(id);
    }

    @Override
    public List<UserGroup> findAll() {
        return userGroupRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        userGroupRepository.deleteById(id);
    }
}
