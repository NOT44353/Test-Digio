package com.digio.xogame.repository;

import com.digio.xogame.model.GameMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameMoveRepository extends JpaRepository<GameMove, Long> {
    
    List<GameMove> findByGameIdOrderByMoveNumberAsc(Long gameId);
    
    @Query("SELECT gm FROM GameMove gm WHERE gm.game.id = :gameId ORDER BY gm.moveNumber ASC")
    List<GameMove> findMovesByGameIdOrderByMoveNumber(@Param("gameId") Long gameId);
}
