package projectHub.projectHub.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projectHub.projectHub.Entity.User;
import projectHub.projectHub.Repository.UserRepository;
import projectHub.projectHub.Service.UserService;
import projectHub.projectHub.security.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(AuthRequest request) {

//        if (userService.findByEmail(request.getEmail()).isPresent()) {
//            throw new IllegalArgumentException("Email already in use.");
//        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setProgram(request.getProgram());
        user.setDescription(request.getDescription());
        userService.save(user);
        return jwtService.generateToken(new CustomUserDetails( user));
    }
}
