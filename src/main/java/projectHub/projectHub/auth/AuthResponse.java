package projectHub.projectHub.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    /* Cambio 2 */
    private Integer userId;

}