package me.tsaheylu.service;

import me.tsaheylu.common.TokenType;
import me.tsaheylu.model.RefreshToken;
import me.tsaheylu.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(User user, TokenType tokenType);

    RefreshToken verifyExpiration(RefreshToken token);

    @Transactional
    int deleteByUserId(Long userId);

    RefreshToken updateToken(RefreshToken refreshToken);
}
