import React from 'react';
import './GameHistory.css';

const GameHistory = ({ games, onLoadGame }) => {
  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleString();
  };

  const getGameStatusText = (game) => {
    if (game.gameStatus === 'IN_PROGRESS') {
      return 'In Progress';
    } else if (game.gameStatus === 'FINISHED') {
      if (game.winner === 'DRAW') {
        return 'Draw';
      } else {
        return `Winner: ${game.winner}`;
      }
    }
    return game.gameStatus;
  };

  const getPlayerTypes = (game) => {
    return `${game.player1Type} vs ${game.player2Type}`;
  };

  return (
    <div className="game-history">
      <h3>Game History</h3>
      <div className="history-list">
        {games.length === 0 ? (
          <p className="no-games">No games played yet</p>
        ) : (
          games.map(game => (
            <div key={game.id} className="history-item">
              <div className="game-info">
                <div className="game-header">
                  <span className="game-id">Game #{game.id}</span>
                  <span className="game-date">{formatDate(game.createdAt)}</span>
                </div>
                <div className="game-details">
                  <span className="board-size">{game.boardSize}x{game.boardSize}</span>
                  <span className="player-types">{getPlayerTypes(game)}</span>
                  <span className={`game-status ${game.gameStatus.toLowerCase()}`}>
                    {getGameStatusText(game)}
                  </span>
                </div>
              </div>
              <button 
                className="load-game-btn"
                onClick={() => onLoadGame(game.id)}
              >
                {game.gameStatus === 'FINISHED' ? 'Replay' : 'Continue'}
              </button>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default GameHistory;
