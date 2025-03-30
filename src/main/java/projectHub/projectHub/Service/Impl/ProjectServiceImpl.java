package projectHub.projectHub.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectHub.projectHub.Entity.Project;
import projectHub.projectHub.Repository.ProjectRepository;
import projectHub.projectHub.Service.ProjectService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;

    @Override
    public Project save(Project project) {
        return repository.save(project);
    }

    @Override
    public Optional<Project> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Project> findByGroupId(Integer groupId) {
        return repository.findByGroupId(groupId);
    }
}
