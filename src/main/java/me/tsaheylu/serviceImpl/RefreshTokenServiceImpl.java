package me.tsaheylu.serviceImpl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.TokenType;
import me.tsaheylu.exception.EmailVefifyException;
import me.tsaheylu.exception.TokenRefreshException;
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
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            logger.debug("Refresh token was expired", token);
            if (token.getType() == TokenType.AUTH.getId()) {
                throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
            }

            if (token.getType() == TokenType.VERIFY.getId()) {
                throw new EmailVefifyException(token.getToken(), "token was expired. Please make a new verify request");
            }
        }

        return token;
    }

    @Transactional
    @Override
    public int deleteByUserId(Long userId) {
        return refreshTokenRepo.deleteByUser(userRepo.findById(userId).get());
    }
}
