package com.digio.xogame.service;

import com.digio.xogame.model.Game;
import com.digio.xogame.model.GameMove;
import com.digio.xogame.repository.GameRepository;
import com.digio.xogame.repository.GameMoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private GameMoveRepository gameMoveRepository;
    
    @Autowired
    private AIService aiService;
    
    public Game createNewGame(Integer boardSize, String player1Type, String player2Type) {
        Game game = new Game(boardSize, player1Type, player2Type);
        return gameRepository.save(game);
    }
    
    public Optional<Game> getGameById(Long gameId) {
        return gameRepository.findById(gameId);
    }
    
    public List<Game> getAllGames() {
        return gameRepository.findAllOrderByCreatedAtDesc();
    }
    
    public List<Game> getGamesWithAI() {
        return gameRepository.findGamesWithAI();
    }
    
    public Game makeMove(Long gameId, Integer row, Integer col) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        
        if (!"IN_PROGRESS".equals(game.getGameStatus())) {
            throw new RuntimeException("Game is not in progress");
        }
        
        // Validate move
        if (!isValidMove(game, row, col)) {
            throw new RuntimeException("Invalid move");
        }
        
        // Make the move
        makeMoveOnBoard(game, row, col, game.getCurrentPlayer());
        
        // Save the move
        GameMove move = new GameMove(game, game.getCurrentPlayer(), row, col, 
                game.getMoves() != null ? game.getMoves().size() + 1 : 1);
        gameMoveRepository.save(move);
        
        // Check for win or draw
        String gameResult = checkGameResult(game);
        if (gameResult != null) {
            game.setGameStatus("FINISHED");
            game.setWinner(gameResult);
        } else {
            // Switch player
            game.setCurrentPlayer(game.getCurrentPlayer().equals("X") ? "O" : "X");
        }
        
        game.setUpdatedAt(LocalDateTime.now());
        return gameRepository.save(game);
    }
    
    public Game makeAIMove(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        
        if (!"IN_PROGRESS".equals(game.getGameStatus())) {
            throw new RuntimeException("Game is not in progress");
        }
        
        // Check if current player is AI
        String currentPlayer = game.getCurrentPlayer();
        boolean isAIPlayer = (currentPlayer.equals("X") && "AI".equals(game.getPlayer1Type())) ||
                           (currentPlayer.equals("O") && "AI".equals(game.getPlayer2Type()));
        
        if (!isAIPlayer) {
            throw new RuntimeException("Current player is not AI");
        }
        
        // Get AI move
        int[] aiMove = aiService.getBestMove(game);
        int row = aiMove[0];
        int col = aiMove[1];
        
        // Make the move
        makeMoveOnBoard(game, row, col, currentPlayer);
        
        // Save the move
        GameMove move = new GameMove(game, currentPlayer, row, col, 
                game.getMoves() != null ? game.getMoves().size() + 1 : 1);
        gameMoveRepository.save(move);
        
        // Check for win or draw
        String gameResult = checkGameResult(game);
        if (gameResult != null) {
            game.setGameStatus("FINISHED");
            game.setWinner(gameResult);
        } else {
            // Switch player
            game.setCurrentPlayer(currentPlayer.equals("X") ? "O" : "X");
        }
        
        game.setUpdatedAt(LocalDateTime.now());
        return gameRepository.save(game);
    }
    
    public List<GameMove> getGameMoves(Long gameId) {
        return gameMoveRepository.findMovesByGameIdOrderByMoveNumber(gameId);
    }
    
    private boolean isValidMove(Game game, Integer row, Integer col) {
        int boardSize = game.getBoardSize();
        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
            return false;
        }
        
        int index = row * boardSize + col;
        return game.getBoardState().charAt(index) == '-';
    }
    
    private void makeMoveOnBoard(Game game, Integer row, Integer col, String player) {
        int boardSize = game.getBoardSize();
        int index = row * boardSize + col;
        String boardState = game.getBoardState();
        String newBoardState = boardState.substring(0, index) + player + boardState.substring(index + 1);
        game.setBoardState(newBoardState);
    }
    
    private String checkGameResult(Game game) {
        String boardState = game.getBoardState();
        int boardSize = game.getBoardSize();
        
        // Check rows
        for (int row = 0; row < boardSize; row++) {
            if (checkLine(boardState, boardSize, row, 0, 0, 1)) {
                return String.valueOf(boardState.charAt(row * boardSize));
            }
        }
        
        // Check columns
        for (int col = 0; col < boardSize; col++) {
            if (checkLine(boardState, boardSize, 0, col, 1, 0)) {
                return String.valueOf(boardState.charAt(col));
            }
        }
        
        // Check main diagonal
        if (checkLine(boardState, boardSize, 0, 0, 1, 1)) {
            return String.valueOf(boardState.charAt(0));
        }
        
        // Check anti-diagonal
        if (checkLine(boardState, boardSize, 0, boardSize - 1, 1, -1)) {
            return String.valueOf(boardState.charAt(boardSize - 1));
        }
        
        // Check for draw
        if (!boardState.contains("-")) {
            return "DRAW";
        }
        
        return null;
    }
    
    private boolean checkLine(String boardState, int boardSize, int startRow, int startCol, int deltaRow, int deltaCol) {
        char firstChar = boardState.charAt(startRow * boardSize + startCol);
        if (firstChar == '-') return false;
        
        for (int i = 1; i < boardSize; i++) {
            int row = startRow + i * deltaRow;
            int col = startCol + i * deltaCol;
            if (boardState.charAt(row * boardSize + col) != firstChar) {
                return false;
            }
        }
        return true;
    }
}
