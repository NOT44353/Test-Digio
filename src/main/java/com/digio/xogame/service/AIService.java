package com.digio.xogame.service;

import com.digio.xogame.model.Game;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    
    public int[] getBestMove(Game game) {
        String boardState = game.getBoardState();
        int boardSize = game.getBoardSize();
        String aiPlayer = game.getCurrentPlayer();
        String humanPlayer = aiPlayer.equals("X") ? "O" : "X";
        
        int[] bestMove = {-1, -1};
        int bestScore = Integer.MIN_VALUE;
        
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (isValidMove(boardState, boardSize, row, col)) {
                    // Make the move
                    String newBoardState = makeMove(boardState, boardSize, row, col, aiPlayer);
                    
                    // Evaluate the move
                    int score = minimax(newBoardState, boardSize, 0, false, aiPlayer, humanPlayer);
                    
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }
        
        return bestMove;
    }
    
    private int minimax(String boardState, int boardSize, int depth, boolean isMaximizing, String aiPlayer, String humanPlayer) {
        String result = checkGameResult(boardState, boardSize);
        
        if (result != null) {
            if (result.equals(aiPlayer)) {
                return 10 - depth; // Prefer faster wins
            } else if (result.equals(humanPlayer)) {
                return depth - 10; // Prefer slower losses
            } else if (result.equals("DRAW")) {
                return 0;
            }
        }
        
        if (isMaximizing) {
            int maxScore = Integer.MIN_VALUE;
            for (int row = 0; row < boardSize; row++) {
                for (int col = 0; col < boardSize; col++) {
                    if (isValidMove(boardState, boardSize, row, col)) {
                        String newBoardState = makeMove(boardState, boardSize, row, col, aiPlayer);
                        int score = minimax(newBoardState, boardSize, depth + 1, false, aiPlayer, humanPlayer);
                        maxScore = Math.max(maxScore, score);
                    }
                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (int row = 0; row < boardSize; row++) {
                for (int col = 0; col < boardSize; col++) {
                    if (isValidMove(boardState, boardSize, row, col)) {
                        String newBoardState = makeMove(boardState, boardSize, row, col, humanPlayer);
                        int score = minimax(newBoardState, boardSize, depth + 1, true, aiPlayer, humanPlayer);
                        minScore = Math.min(minScore, score);
                    }
                }
            }
            return minScore;
        }
    }
    
    private boolean isValidMove(String boardState, int boardSize, int row, int col) {
        int index = row * boardSize + col;
        return boardState.charAt(index) == '-';
    }
    
    private String makeMove(String boardState, int boardSize, int row, int col, String player) {
        int index = row * boardSize + col;
        return boardState.substring(0, index) + player + boardState.substring(index + 1);
    }
    
    private String checkGameResult(String boardState, int boardSize) {
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
