package com.codewithson.Elearning.repositories;

import com.codewithson.Elearning.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);

    @Query("SELECT t FROM Token t WHERE t.user.id = ?1 AND t.expired = false AND t.revoked = false")
    List<Token> findAllValidTokenByUser(Long id);
}
