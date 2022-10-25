package me.tsaheylu.component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtUtil implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static final String CLAIM_KEY_USERNAME = "sub";

    @Value("${tsahayluMe.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    /**
     * SECRET 是签名密钥，只生成一次即可，生成方法：
     * Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
     * String secretString = Encoders.BASE64.encode(key.getEncoded()); # 本文使用 BASE64 编码
     */
    @Value("${tsahayluMe.app.jwtSecret}")
    private String jwtSecret;

    private SecretKey getSecretKey() {
        byte[] encodeKey = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(encodeKey);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(16);

        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        SecretKey secretKey = getSecretKey();
        return Jwts.builder().setClaims(claims).setExpiration(new Date(Instant.now().toEpochMilli() + jwtExpirationMs)).signWith(secretKey).compact();
    }

    public Boolean validateToken(String token) {
//        User user = (User) userDetails;
//        String username = getUsernameFromToken(token);
//        return (username.equals(user.getEmail())) && !isTokenExpired(token);

        try {
            Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;

    }

    private Claims getClaimsFromToken(String token) throws ExpiredJwtException {
//            Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        SecretKey secretKey = getSecretKey();
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        Claims claims = jws.getBody();
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

    public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();

        List<SimpleGrantedAuthority> roles = null;

        Boolean isAdmin = claims.get("isAdmin", Boolean.class);
        Boolean isUser = claims.get("isUser", Boolean.class);

        if (isAdmin != null && isAdmin) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        if (isUser != null && isAdmin) {
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return roles;

    }
}
