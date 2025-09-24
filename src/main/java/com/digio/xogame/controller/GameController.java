package com.digio.xogame.controller;

import com.digio.xogame.model.Game;
import com.digio.xogame.model.GameMove;
import com.digio.xogame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Map<String, Object> request) {
        Integer boardSize = (Integer) request.get("boardSize");
        String player1Type = (String) request.get("player1Type");
        String player2Type = (String) request.get("player2Type");
        
        Game game = gameService.createNewGame(boardSize, player1Type, player2Type);
        return ResponseEntity.ok(game);
    }
    
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable Long gameId) {
        return gameService.getGameById(gameId)
                .map(game -> ResponseEntity.ok(game))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }
    
    @GetMapping("/ai")
    public ResponseEntity<List<Game>> getGamesWithAI() {
        List<Game> games = gameService.getGamesWithAI();
        return ResponseEntity.ok(games);
    }
    
    @PostMapping("/{gameId}/move")
    public ResponseEntity<Game> makeMove(@PathVariable Long gameId, @RequestBody Map<String, Object> request) {
        try {
            Integer row = (Integer) request.get("row");
            Integer col = (Integer) request.get("col");
            
            Game game = gameService.makeMove(gameId, row, col);
            return ResponseEntity.ok(game);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{gameId}/ai-move")
    public ResponseEntity<Game> makeAIMove(@PathVariable Long gameId) {
        try {
            Game game = gameService.makeAIMove(gameId);
            return ResponseEntity.ok(game);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/{gameId}/moves")
    public ResponseEntity<List<GameMove>> getGameMoves(@PathVariable Long gameId) {
        List<GameMove> moves = gameService.getGameMoves(gameId);
        return ResponseEntity.ok(moves);
    }
}
