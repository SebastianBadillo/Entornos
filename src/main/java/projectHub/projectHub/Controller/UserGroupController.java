package projectHub.projectHub.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectHub.projectHub.Entity.UserGroup;
import projectHub.projectHub.Service.UserGroupService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class UserGroupController {

    private final UserGroupService groupService;

    @PostMapping
    public ResponseEntity<UserGroup> createGroup(@RequestBody UserGroup group) {
        group.setId(null);
        return ResponseEntity.ok(groupService.save(group));
    }
    @PutMapping
    public ResponseEntity<UserGroup> updateGroup(@RequestBody UserGroup group) {
        return ResponseEntity.ok(groupService.save(group));
    }

    @GetMapping
    public ResponseEntity<List<UserGroup>> getAllGroups() {
        return ResponseEntity.ok(groupService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGroup> getGroupById(@PathVariable Integer id) {
        return groupService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer id) {
        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}