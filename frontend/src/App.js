import React, { useState, useEffect } from 'react';
import './App.css';
import GameBoard from './components/GameBoard';
import GameHistory from './components/GameHistory';
import GameSetup from './components/GameSetup';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

function App() {
  const [currentGame, setCurrentGame] = useState(null);
  const [gameHistory, setGameHistory] = useState([]);
  const [showHistory, setShowHistory] = useState(false);
  const [gameSetup, setGameSetup] = useState({
    boardSize: 3,
    player1Type: 'HUMAN',
    player2Type: 'AI'
  });

  useEffect(() => {
    fetchGameHistory();
  }, []);

  const fetchGameHistory = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/games`);
      setGameHistory(response.data);
    } catch (error) {
      console.error('Error fetching game history:', error);
    }
  };

  const createNewGame = async () => {
    try {
      const response = await axios.post(`${API_BASE_URL}/games`, gameSetup);
      setCurrentGame(response.data);
      setShowHistory(false);
      fetchGameHistory();
    } catch (error) {
      console.error('Error creating new game:', error);
    }
  };

  const makeMove = async (row, col) => {
    if (!currentGame || currentGame.gameStatus !== 'IN_PROGRESS') return;

    try {
      const response = await axios.post(`${API_BASE_URL}/games/${currentGame.id}/move`, {
        row,
        col
      });
      setCurrentGame(response.data);

      // If next player is AI, make AI move
      if (response.data.gameStatus === 'IN_PROGRESS') {
        const currentPlayer = response.data.currentPlayer;
        const isAIPlayer = (currentPlayer === 'X' && response.data.player1Type === 'AI') ||
                          (currentPlayer === 'O' && response.data.player2Type === 'AI');
        
        if (isAIPlayer) {
          setTimeout(async () => {
            try {
              const aiResponse = await axios.post(`${API_BASE_URL}/games/${currentGame.id}/ai-move`);
              setCurrentGame(aiResponse.data);
              fetchGameHistory();
            } catch (error) {
              console.error('Error making AI move:', error);
            }
          }, 1000);
        }
      } else {
        fetchGameHistory();
      }
    } catch (error) {
      console.error('Error making move:', error);
    }
  };

  const loadGameForReplay = async (gameId) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/games/${gameId}`);
      setCurrentGame(response.data);
      setShowHistory(false);
    } catch (error) {
      console.error('Error loading game for replay:', error);
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>XO Game</h1>
        <p>Play Tic-Tac-Toe with AI opponent and replay functionality</p>
      </header>

      <div className="App-content">
        {!currentGame ? (
          <div className="game-setup-container">
            <GameSetup 
              gameSetup={gameSetup}
              setGameSetup={setGameSetup}
              onCreateGame={createNewGame}
            />
          </div>
        ) : (
          <div className="game-container">
            <div className="game-info">
              <h2>Game #{currentGame.id}</h2>
              <p>Board Size: {currentGame.boardSize}x{currentGame.boardSize}</p>
              <p>Status: {currentGame.gameStatus}</p>
              {currentGame.winner && (
                <p className="winner">
                  {currentGame.winner === 'DRAW' ? 'Draw!' : `Winner: ${currentGame.winner}`}
                </p>
              )}
              <div className="game-controls">
                <button onClick={() => setCurrentGame(null)}>New Game</button>
                <button onClick={() => setShowHistory(!showHistory)}>
                  {showHistory ? 'Hide History' : 'Show History'}
                </button>
              </div>
            </div>

            <GameBoard 
              game={currentGame}
              onMakeMove={makeMove}
            />

            {showHistory && (
              <GameHistory 
                games={gameHistory}
                onLoadGame={loadGameForReplay}
              />
            )}
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
