package projectHub.projectHub.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_group_members")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@IdClass(UserGroupMemberId.class)
public class UserGroupMember {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    private UserGroup group;
}
