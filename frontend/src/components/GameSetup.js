import React from 'react';
import './GameSetup.css';

const GameSetup = ({ gameSetup, setGameSetup, onCreateGame }) => {
  const handleBoardSizeChange = (e) => {
    setGameSetup({
      ...gameSetup,
      boardSize: parseInt(e.target.value)
    });
  };

  const handlePlayerTypeChange = (player, type) => {
    setGameSetup({
      ...gameSetup,
      [player]: type
    });
  };

  return (
    <div className="game-setup">
      <h2>Game Setup</h2>
      
      <div className="setup-section">
        <h3>Board Size</h3>
        <div className="board-size-options">
          {[3, 4, 5, 6].map(size => (
            <label key={size} className="board-size-option">
              <input
                type="radio"
                name="boardSize"
                value={size}
                checked={gameSetup.boardSize === size}
                onChange={handleBoardSizeChange}
              />
              <span>{size}x{size}</span>
            </label>
          ))}
        </div>
      </div>

      <div className="setup-section">
        <h3>Player 1 (X)</h3>
        <div className="player-type-options">
          <label className="player-type-option">
            <input
              type="radio"
              name="player1Type"
              value="HUMAN"
              checked={gameSetup.player1Type === 'HUMAN'}
              onChange={() => handlePlayerTypeChange('player1Type', 'HUMAN')}
            />
            <span>Human</span>
          </label>
          <label className="player-type-option">
            <input
              type="radio"
              name="player1Type"
              value="AI"
              checked={gameSetup.player1Type === 'AI'}
              onChange={() => handlePlayerTypeChange('player1Type', 'AI')}
            />
            <span>AI</span>
          </label>
        </div>
      </div>

      <div className="setup-section">
        <h3>Player 2 (O)</h3>
        <div className="player-type-options">
          <label className="player-type-option">
            <input
              type="radio"
              name="player2Type"
              value="HUMAN"
              checked={gameSetup.player2Type === 'HUMAN'}
              onChange={() => handlePlayerTypeChange('player2Type', 'HUMAN')}
            />
            <span>Human</span>
          </label>
          <label className="player-type-option">
            <input
              type="radio"
              name="player2Type"
              value="AI"
              checked={gameSetup.player2Type === 'AI'}
              onChange={() => handlePlayerTypeChange('player2Type', 'AI')}
            />
            <span>AI</span>
          </label>
        </div>
      </div>

      <button className="start-game-btn" onClick={onCreateGame}>
        Start Game
      </button>
    </div>
  );
};

export default GameSetup;
