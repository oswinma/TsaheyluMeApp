package me.tsaheylu.component;

import io.jsonwebtoken.*;
import me.tsaheylu.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenComponent implements Serializable {

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final long EXPIRATION_TIME = 12 * 3600 * 1000;

    private static final String SECRET = "167e5226-20ec-47e6-8cd7-0e9074490d52";

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(16);

        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        return Jwts.builder().setClaims(claims).setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getEmail())) && !isTokenExpired(token);
    }

    private Claims getClaimsFromToken(String token) throws ExpiredJwtException {

        Claims claims = null;
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            claims = jws.getBody();

        } catch (ExpiredJwtException e) {
            throw e;
        }
        return claims;
    }

    public String getUsernameFromToken(String token) throws ExpiredJwtException {

        Claims claims = getClaimsFromToken(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
