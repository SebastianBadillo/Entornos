package projectHub.projectHub.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "d81984b25ce4d5a278669d11ea39b4ce4ba2040cf2ad9f0fcca246add2817b4c26fd4483f2baab254ea79e8c14b084a6728d2158f4c662478fd5602f375fbe3319b38551136f1d221ef65c5d156a5656a69e5d5f6f6dd4a8d3b566c6355068a5d61ef0ea28bb9de68ffcfd6d028c327905fabcbd51d3cdbb37bd0a4b0e3c0c865c4b23aad3834150814b63a229a3c2848421b37843fe2e31bc8b6c777e872c0bd249e5f1274d7cce587f0b48b2083fc21e4c779f40958dee90f3c2fc030519780a3bda1302d9920278403a1727ba167652f15fb63bdc1dd376e15eb5f07d9005cddc90c8aad0134b0805602eb4eba7e895a12e6e64d4f424b5c338c85ed3d301";

    public String extractToken(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
    private Claims extractAllClaims(String token) {
       return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
