package com.digio.xogame.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "board_size")
    private Integer boardSize;
    
    @Column(name = "current_player")
    private String currentPlayer;
    
    @Column(name = "game_status")
    private String gameStatus;
    
    @Column(name = "winner")
    private String winner;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameMove> moves;
    
    @Column(name = "board_state", length = 1000)
    private String boardState;
    
    @Column(name = "player1_type")
    private String player1Type; // "HUMAN" or "AI"
    
    @Column(name = "player2_type")
    private String player2Type; // "HUMAN" or "AI"
    
    // Constructors
    public Game() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    public Game(Integer boardSize, String player1Type, String player2Type) {
        this();
        this.boardSize = boardSize;
        this.currentPlayer = "X";
        this.gameStatus = "IN_PROGRESS";
        this.player1Type = player1Type;
        this.player2Type = player2Type;
        this.boardState = initializeBoard(boardSize);
    }
    
    private String initializeBoard(Integer size) {
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < size * size; i++) {
            board.append("-");
        }
        return board.toString();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getBoardSize() {
        return boardSize;
    }
    
    public void setBoardSize(Integer boardSize) {
        this.boardSize = boardSize;
    }
    
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    public String getGameStatus() {
        return gameStatus;
    }
    
    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
    
    public String getWinner() {
        return winner;
    }
    
    public void setWinner(String winner) {
        this.winner = winner;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public List<GameMove> getMoves() {
        return moves;
    }
    
    public void setMoves(List<GameMove> moves) {
        this.moves = moves;
    }
    
    public String getBoardState() {
        return boardState;
    }
    
    public void setBoardState(String boardState) {
        this.boardState = boardState;
    }
    
    public String getPlayer1Type() {
        return player1Type;
    }
    
    public void setPlayer1Type(String player1Type) {
        this.player1Type = player1Type;
    }
    
    public String getPlayer2Type() {
        return player2Type;
    }
    
    public void setPlayer2Type(String player2Type) {
        this.player2Type = player2Type;
    }
}
