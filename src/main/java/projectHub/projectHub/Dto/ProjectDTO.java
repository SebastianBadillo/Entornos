package projectHub.projectHub.Dto;

import lombok.Data;
import projectHub.projectHub.Entity.Project;
@Data
public class ProjectDTO {
    private Integer id;
    private String title;
    private String description;
    private String repoLink;
    private Integer groupId;
    private Project.Status status;
}
