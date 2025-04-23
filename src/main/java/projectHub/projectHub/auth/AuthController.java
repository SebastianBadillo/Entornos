package projectHub.projectHub.auth;

import com.fasterxml.jackson.core.Base64Variant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectHub.projectHub.Entity.User;
import projectHub.projectHub.Service.UserService;

import java.io.InputStream;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody User request){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserDetails user = userService.loadUserByUsername(request.getEmail());
        /* Cambio 1*/
        User userInfo = userService.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token, userInfo.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
      String token = authService.register(request);
        /* Cambio 1*/
      User userInfo = userService.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
      return ResponseEntity.ok(new AuthResponse(token, userInfo.getId()));
    }

}
