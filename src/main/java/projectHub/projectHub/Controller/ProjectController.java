package projectHub.projectHub.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectHub.projectHub.Dto.ProjectDTO;
import projectHub.projectHub.Entity.Project;
import projectHub.projectHub.Entity.User;
import projectHub.projectHub.Entity.UserGroup;
import projectHub.projectHub.Service.ProjectService;
import projectHub.projectHub.Service.UserGroupService;
import projectHub.projectHub.mappers.ProjectMapper;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserGroupService userGroupService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        projectDTO.setId(null);
        UserGroup userGroup = userGroupService.findById(projectDTO.getGroupId()).orElse(null);
        if (userGroup == null) {
            return ResponseEntity.badRequest().build();
        }
        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());
        project.setGroup(userGroup);
        project.setRepoLink(projectDTO.getRepoLink());
        project.setStatus(projectDTO.getStatus());
        Project savedProject = projectService.save(project);
        ProjectDTO savedDTO = ProjectMapper.toDTO(savedProject);
        return ResponseEntity.ok(savedDTO);
    }
    @PutMapping
    public ResponseEntity<?> updateProject(@RequestBody ProjectDTO project) {
        UserGroup group = userGroupService.findById(project.getGroupId()).orElse(null);
        if (group == null) {
            return ResponseEntity.badRequest().body("Group not found");
        }
        Project existingProject = projectService.findById(project.getId()).orElse(null);
        if (existingProject == null) {
            return ResponseEntity.badRequest().body("Project not found");
        }
        existingProject.setTitle(project.getTitle());
        existingProject.setDescription(project.getDescription());
        existingProject.setGroup(group);
        existingProject.setRepoLink(project.getRepoLink());
        existingProject.setStatus(project.getStatus());
        Project updatedProject = projectService.save(existingProject);
        ProjectDTO updatedDTO = ProjectMapper.toDTO(updatedProject);


        return ResponseEntity.ok(updatedDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll().stream().map(ProjectMapper::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Integer id) {
        ProjectDTO projectDTO = ProjectMapper.toDTO(projectService.findById(id).orElse(null));
        if (projectDTO == null) {
            return ResponseEntity.badRequest().body("Project not found");
        }
        return ResponseEntity.ok(projectDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByGroup(@PathVariable Integer groupId) {
        return ResponseEntity.ok(projectService.findByGroupId(groupId).stream().map(ProjectMapper::toDTO).toList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(projectService.findAllByUserId(userId).stream().map(ProjectMapper::toDTO).toList());
    }
}