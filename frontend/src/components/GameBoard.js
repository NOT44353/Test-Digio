import React, { useState, useEffect } from 'react';
import './GameBoard.css';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const GameBoard = ({ game, onMakeMove }) => {
  const [moves, setMoves] = useState([]);
  const [currentMoveIndex, setCurrentMoveIndex] = useState(-1);
  const [isReplaying, setIsReplaying] = useState(false);

  useEffect(() => {
    if (game) {
      fetchMoves();
    }
  }, [game]);

  const fetchMoves = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/games/${game.id}/moves`);
      setMoves(response.data);
      setCurrentMoveIndex(response.data.length - 1);
    } catch (error) {
      console.error('Error fetching moves:', error);
    }
  };

  const getBoardState = () => {
    if (game.gameStatus === 'FINISHED' && moves.length > 0) {
      // For finished games, show the final state
      return game.boardState;
    }
    
    if (currentMoveIndex >= 0 && moves.length > 0) {
      // For replay, reconstruct board state up to current move
      const board = Array(game.boardSize * game.boardSize).fill('-');
      for (let i = 0; i <= currentMoveIndex; i++) {
        const move = moves[i];
        const index = move.row * game.boardSize + move.col;
        board[index] = move.player;
      }
      return board.join('');
    }
    
    return game.boardState;
  };

  const handleCellClick = (row, col) => {
    if (game.gameStatus !== 'IN_PROGRESS' || isReplaying) return;
    onMakeMove(row, col);
  };

  const startReplay = () => {
    if (moves.length === 0) return;
    setIsReplaying(true);
    setCurrentMoveIndex(-1);
    
    const replayInterval = setInterval(() => {
      setCurrentMoveIndex(prev => {
        if (prev >= moves.length - 1) {
          clearInterval(replayInterval);
          setIsReplaying(false);
          return moves.length - 1;
        }
        return prev + 1;
      });
    }, 1000);
  };

  const resetReplay = () => {
    setIsReplaying(false);
    setCurrentMoveIndex(moves.length - 1);
  };

  const boardState = getBoardState();
  const boardSize = game.boardSize;

  return (
    <div className="game-board-container">
      <div className="game-board" style={{ gridTemplateColumns: `repeat(${boardSize}, 1fr)` }}>
        {Array.from({ length: boardSize * boardSize }, (_, index) => {
          const row = Math.floor(index / boardSize);
          const col = index % boardSize;
          const cellValue = boardState[index];
          
          return (
            <div
              key={index}
              className={`cell ${cellValue !== '-' ? 'filled' : ''} ${game.gameStatus !== 'IN_PROGRESS' || isReplaying ? 'disabled' : ''}`}
              onClick={() => handleCellClick(row, col)}
            >
              {cellValue !== '-' ? cellValue : ''}
            </div>
          );
        })}
      </div>

      {game.gameStatus === 'FINISHED' && moves.length > 0 && (
        <div className="replay-controls">
          <button onClick={startReplay} disabled={isReplaying}>
            {isReplaying ? 'Replaying...' : 'Start Replay'}
          </button>
          <button onClick={resetReplay} disabled={!isReplaying}>
            Reset
          </button>
          <div className="replay-info">
            Move {currentMoveIndex + 1} of {moves.length}
          </div>
        </div>
      )}
    </div>
  );
};

export default GameBoard;
