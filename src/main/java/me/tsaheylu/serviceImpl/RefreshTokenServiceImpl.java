package me.tsaheylu.serviceImpl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.TokenType;
import me.tsaheylu.exception.TokenExpiredException;
import me.tsaheylu.model.RefreshToken;
import me.tsaheylu.model.User;
import me.tsaheylu.repository.RefreshTokenRepo;
import me.tsaheylu.repository.UserRepo;
import me.tsaheylu.service.RefreshTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private static final Logger logger = LoggerFactory.getLogger(RefreshTokenServiceImpl.class);

    @Value("${tsahayluMe.app.jwtRefreshExpirationMs}")
    private long jwtRefreshExpirationMs;

    private final RefreshTokenRepo refreshTokenRepo;

    private final UserRepo userRepo;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepo.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(User user, TokenType tokenType) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setType(tokenType.getId());
        refreshToken = refreshTokenRepo.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) throws TokenExpiredException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            logger.debug("Refresh token was expired", token);
            throw new TokenExpiredException(token.getToken(), "token was expired. Please make a new verify request");
        }

        return token;
    }

    @Transactional
    @Override
    public int deleteByUserId(Long userId) {
        return refreshTokenRepo.deleteByUser(userRepo.findById(userId).get());
    }

    @Override
    public RefreshToken updateToken(RefreshToken refreshToken) {
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationMs));
        refreshToken = refreshTokenRepo.save(refreshToken);

        return refreshToken;
    }
}
