package projectHub.projectHub.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectHub.projectHub.Entity.Project;
import projectHub.projectHub.Service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        project.setId(null);
        return ResponseEntity.ok(projectService.save(project));
    }
    @PutMapping
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.save(project));
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id) {
        return projectService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Project>> getProjectsByGroup(@PathVariable Integer groupId) {
        return ResponseEntity.ok(projectService.findByGroupId(groupId));
    }
}