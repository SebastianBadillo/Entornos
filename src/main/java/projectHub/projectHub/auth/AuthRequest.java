package projectHub.projectHub.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String program;
    private String description;
}
