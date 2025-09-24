package com.digio.xogame.repository;

import com.digio.xogame.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    
    @Query("SELECT g FROM Game g ORDER BY g.createdAt DESC")
    List<Game> findAllOrderByCreatedAtDesc();
    
    @Query("SELECT g FROM Game g WHERE g.gameStatus = :status ORDER BY g.createdAt DESC")
    List<Game> findByGameStatusOrderByCreatedAtDesc(@Param("status") String status);
    
    @Query("SELECT g FROM Game g WHERE g.player1Type = 'AI' OR g.player2Type = 'AI' ORDER BY g.createdAt DESC")
    List<Game> findGamesWithAI();
}
