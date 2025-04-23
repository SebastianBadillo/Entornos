package projectHub.projectHub.mappers;

import projectHub.projectHub.Dto.ProjectDTO;
import projectHub.projectHub.Entity.Project;

public class ProjectMapper {
    // Assuming you have a Project entity and ProjectDTO
     public static ProjectDTO toDTO(Project project) {
         if (project == null) return null;
         ProjectDTO dto = new ProjectDTO();
         dto.setId(project.getId());
         dto.setTitle(project.getTitle());
         dto.setDescription(project.getDescription());
         dto.setRepoLink(project.getRepoLink());
         dto.setGroupId(project.getGroup().getId());
         dto.setStatus(project.getStatus());
         return dto;
     }
}
