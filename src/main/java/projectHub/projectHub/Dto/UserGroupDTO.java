package projectHub.projectHub.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserGroupDTO {
    private Integer id;
    private String name;
    private Integer leaderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
