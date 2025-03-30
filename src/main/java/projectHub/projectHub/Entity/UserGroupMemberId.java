package projectHub.projectHub.Entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserGroupMemberId implements Serializable {

    private Integer user;
    private Integer group;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroupMemberId that)) return false;
        return Objects.equals(user, that.user) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, group);
    }
}
