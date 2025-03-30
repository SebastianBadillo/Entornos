package projectHub.projectHub.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectHub.projectHub.Entity.User;
import projectHub.projectHub.Entity.UserGroup;
import projectHub.projectHub.Entity.UserGroupMember;
import projectHub.projectHub.Entity.UserGroupMemberId;
import projectHub.projectHub.Service.UserGroupMemberService;

import java.util.List;

@RestController
@RequestMapping("/api/group-members")
@RequiredArgsConstructor
public class UserGroupMemberController {

    private final UserGroupMemberService memberService;

    // Añadir un usuario a un grupo
    @PostMapping
    public ResponseEntity<UserGroupMember> addMember(@RequestBody UserGroupMember member) {
        return ResponseEntity.ok(memberService.save(member));
    }

    // Obtener todos los miembros de un grupo
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<UserGroupMember>> getMembersByGroup(@PathVariable Integer groupId) {
        return ResponseEntity.ok(memberService.findByGroupId(groupId));
    }

    // Obtener todos los grupos a los que pertenece un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserGroupMember>> getGroupsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(memberService.findByUserId(userId));
    }

    // Eliminar un usuario de un grupo
    @DeleteMapping("/user/{userId}/group/{groupId}")
    public ResponseEntity<Void> removeMember(@PathVariable Integer userId, @PathVariable Integer groupId) {
        User user = new User();
        user.setId(userId);

        UserGroup group = new UserGroup();
        group.setId(groupId);

        UserGroupMember member = new UserGroupMember();
        member.setUser(user);
        member.setGroup(group);

        memberService.delete(member);
        return ResponseEntity.noContent().build();
    }
}