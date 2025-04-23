package projectHub.projectHub.mappers;

import projectHub.projectHub.Dto.UserGroupDTO;
import projectHub.projectHub.Entity.UserGroup;

public class UserGroupMapper {
    public static UserGroupDTO toDTO(UserGroup entity) {
        if (entity == null) return null;

        UserGroupDTO dto = new UserGroupDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getCreatedAt() != null) {
            dto.setLeaderId(entity.getLeader().getId());
        }
        return dto;
    }
}
