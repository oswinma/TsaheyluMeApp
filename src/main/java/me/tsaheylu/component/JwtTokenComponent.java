package me.tsaheylu.component;

import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import me.tsaheylu.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenComponent implements Serializable {

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final long EXPIRATION_TIME = 12 * 3600 * 1000;

    /**
     * SECRET 是签名密钥，只生成一次即可，生成方法：
     * Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
     * String secretString = Encoders.BASE64.encode(key.getEncoded()); # 本文使用 BASE64 编码
     * */
    private static final String SECRET = "cuAihCz53DZRjZwbsGcZJ2Ai6At+T142uphtJMsk7iQ=";


    private SecretKey getSecretKey() {
        byte[] encodeKey = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(encodeKey);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(16);

        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        SecretKey secretKey = getSecretKey();
        return Jwts.builder().setClaims(claims).setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME)).signWith(secretKey).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getEmail())) && !isTokenExpired(token);
    }

    private Claims getClaimsFromToken(String token) throws ExpiredJwtException {

        Claims claims = null;
        try {
//            Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            SecretKey secretKey = getSecretKey();

            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
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
