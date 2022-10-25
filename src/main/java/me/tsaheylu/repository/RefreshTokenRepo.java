package me.tsaheylu.repository;

import me.tsaheylu.dto.CommentDTO;
import me.tsaheylu.model.Comment;
import me.tsaheylu.model.RefreshToken;
import me.tsaheylu.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepo extends CrudRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);

}

