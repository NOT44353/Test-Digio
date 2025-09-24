package com.digio.xogame.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_moves")
public class GameMove {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;
    
    @Column(name = "player")
    private String player;
    
    @Column(name = "row_position")
    private Integer row;
    
    @Column(name = "col_position")
    private Integer col;
    
    @Column(name = "move_number")
    private Integer moveNumber;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructors
    public GameMove() {
        this.createdAt = LocalDateTime.now();
    }
    
    public GameMove(Game game, String player, Integer row, Integer col, Integer moveNumber) {
        this();
        this.game = game;
        this.player = player;
        this.row = row;
        this.col = col;
        this.moveNumber = moveNumber;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Game getGame() {
        return game;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    public String getPlayer() {
        return player;
    }
    
    public void setPlayer(String player) {
        this.player = player;
    }
    
    public Integer getRow() {
        return row;
    }
    
    public void setRow(Integer row) {
        this.row = row;
    }
    
    public Integer getCol() {
        return col;
    }
    
    public void setCol(Integer col) {
        this.col = col;
    }
    
    public Integer getMoveNumber() {
        return moveNumber;
    }
    
    public void setMoveNumber(Integer moveNumber) {
        this.moveNumber = moveNumber;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
