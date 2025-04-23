package projectHub.projectHub.Dto;

import java.time.LocalDateTime;

public class UserGroupResponseDTO {
    private Integer id;
    private String name;
    private UserDTO leader;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
