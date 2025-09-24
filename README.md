# XO Game - Tic-Tac-Toe with AI and Replay

A comprehensive Tic-Tac-Toe game built with Java Spring Boot backend and React frontend, featuring configurable board sizes, AI opponent using minimax algorithm, and game replay functionality.

## Features

### Core Features
- ✅ **Configurable Board Size**: Play on any NxN board (3x3, 4x4, 5x5, 6x6, etc.)
- ✅ **Game History & Replay**: All games are saved to database with full replay capability
- ✅ **AI Opponent**: Intelligent bot using minimax algorithm for optimal play
- ✅ **Modern UI**: Beautiful, responsive React frontend with glassmorphism design
- ✅ **Real-time Gameplay**: Smooth game flow with automatic AI moves

### Technical Features
- **Backend**: Java Spring Boot with JPA/Hibernate
- **Database**: H2 in-memory database with automatic schema creation
- **Frontend**: React with modern CSS and responsive design
- **AI Algorithm**: Minimax with alpha-beta pruning for optimal performance
- **REST API**: Clean RESTful API design with proper error handling

## Project Structure

```
xo-game/
├── src/main/java/com/digio/xogame/
│   ├── XoGameApplication.java          # Spring Boot main class
│   ├── controller/
│   │   └── GameController.java         # REST API endpoints
│   ├── model/
│   │   ├── Game.java                   # Game entity
│   │   └── GameMove.java               # Game move entity
│   ├── repository/
│   │   ├── GameRepository.java         # Game data access
│   │   └── GameMoveRepository.java     # Move data access
│   └── service/
│       ├── GameService.java            # Game business logic
│       └── AIService.java              # AI minimax algorithm
├── src/main/resources/
│   └── application.properties         # Database configuration
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   │   ├── GameBoard.js            # Game board component
│   │   │   ├── GameHistory.js          # Game history component
│   │   │   └── GameSetup.js            # Game setup component
│   │   ├── App.js                      # Main React app
│   │   └── index.js                    # React entry point
│   └── package.json                    # Frontend dependencies
└── pom.xml                             # Maven dependencies
```

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Node.js 16 or higher
- npm or yarn

### Backend Setup (Spring Boot)

1. **Navigate to project root directory**
   ```bash
   cd xo-game
   ```

2. **Install dependencies and run the application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. **Verify backend is running**
   - Backend will be available at: `http://localhost:8080`
   - H2 Database Console: `http://localhost:8080/h2-console`
   - API Documentation: `http://localhost:8080/api/games`

### Frontend Setup (React)

1. **Navigate to frontend directory**
   ```bash
   cd frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Start the development server**
   ```bash
   npm start
   ```

4. **Verify frontend is running**
   - Frontend will be available at: `http://localhost:3000`
   - The app will automatically open in your browser

## How to Play

### Starting a New Game
1. **Choose Board Size**: Select from 3x3, 4x4, 5x5, or 6x6
2. **Select Player Types**: Choose Human or AI for each player
3. **Click "Start Game"** to begin

### Gameplay
- **Human vs Human**: Take turns clicking on empty cells
- **Human vs AI**: Make your move, AI responds automatically
- **AI vs AI**: Watch two AI players compete (great for testing)

### Game History & Replay
- **View History**: Click "Show History" to see all previous games
- **Replay Games**: Click "Replay" on any finished game to watch the moves
- **Continue Games**: Click "Continue" on in-progress games

## API Endpoints

### Game Management
- `POST /api/games` - Create new game
- `GET /api/games` - Get all games
- `GET /api/games/{id}` - Get specific game
- `GET /api/games/ai` - Get games with AI players

### Game Actions
- `POST /api/games/{id}/move` - Make a move
- `POST /api/games/{id}/ai-move` - Make AI move
- `GET /api/games/{id}/moves` - Get game moves for replay

### Example API Usage
```bash
# Create a new 4x4 game with AI opponent
curl -X POST http://localhost:8080/api/games \
  -H "Content-Type: application/json" \
  -d '{"boardSize": 4, "player1Type": "HUMAN", "player2Type": "AI"}'

# Make a move
curl -X POST http://localhost:8080/api/games/1/move \
  -H "Content-Type: application/json" \
  -d '{"row": 0, "col": 0}'
```

## Algorithm Design

### AI Minimax Algorithm
The AI uses the minimax algorithm with the following characteristics:

#### **Algorithm Overview**
```java
public int minimax(String boardState, int boardSize, int depth, boolean isMaximizing, String aiPlayer, String humanPlayer) {
    // Base case: check for terminal states
    String result = checkGameResult(boardState, boardSize);
    if (result != null) {
        if (result.equals(aiPlayer)) return 10 - depth;      // AI wins
        if (result.equals(humanPlayer)) return depth - 10;   // Human wins  
        if (result.equals("DRAW")) return 0;                // Draw
    }
    
    // Recursive case: explore all possible moves
    if (isMaximizing) {
        // AI's turn - maximize score
        int maxScore = Integer.MIN_VALUE;
        for (each possible move) {
            int score = minimax(newBoardState, depth + 1, false);
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    } else {
        // Human's turn - minimize score
        int minScore = Integer.MAX_VALUE;
        for (each possible move) {
            int score = minimax(newBoardState, depth + 1, true);
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }
}
```

#### **Key Features**
- **Optimal Play**: AI always makes the best possible move
- **Depth Consideration**: Prefers faster wins and slower losses
- **Scalable**: Works efficiently on any board size
- **Unbeatable**: On 3x3 boards, AI cannot be beaten (only drawn)

#### **Scoring System**
- **AI Win**: +10 points (minus depth for faster wins)
- **Human Win**: -10 points (plus depth for slower losses)
- **Draw**: 0 points
- **Depth Penalty**: Encourages faster wins and slower losses

### Game Logic Architecture

#### **Board State Management**
```java
// Board represented as string: "X-O-X--O-"
// Index calculation: row * boardSize + col
private void makeMoveOnBoard(Game game, Integer row, Integer col, String player) {
    int index = row * game.getBoardSize() + col;
    String newBoardState = boardState.substring(0, index) + player + boardState.substring(index + 1);
    game.setBoardState(newBoardState);
}
```

#### **Win Detection Algorithm**
```java
private String checkGameResult(Game game) {
    // Check rows, columns, and diagonals
    for (int i = 0; i < boardSize; i++) {
        if (checkLine(boardState, boardSize, i, 0, 0, 1)) return winner; // Row
        if (checkLine(boardState, boardSize, 0, i, 1, 0)) return winner; // Column
    }
    if (checkLine(boardState, boardSize, 0, 0, 1, 1)) return winner;     // Main diagonal
    if (checkLine(boardState, boardSize, 0, boardSize-1, 1, -1)) return winner; // Anti-diagonal
    return boardState.contains("-") ? null : "DRAW";
}
```

## Database Schema

### Game Table
```sql
CREATE TABLE games (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    board_size INTEGER NOT NULL,
    current_player VARCHAR(1),
    game_status VARCHAR(20),
    winner VARCHAR(10),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    board_state VARCHAR(1000),
    player1_type VARCHAR(10),
    player2_type VARCHAR(10)
);
```

### Game Move Table
```sql
CREATE TABLE game_moves (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    game_id BIGINT REFERENCES games(id),
    player VARCHAR(1),
    row_position INTEGER,
    col_position INTEGER,
    move_number INTEGER,
    created_at TIMESTAMP
);
```

## Development Notes

### Performance Optimizations
- **Database Indexing**: Proper indexes on game_id and created_at
- **Lazy Loading**: Game moves loaded only when needed
- **Connection Pooling**: HikariCP for database connections
- **Caching**: Spring Boot's built-in caching for frequently accessed data

### Security Considerations
- **Input Validation**: All API inputs validated
- **SQL Injection Prevention**: JPA/Hibernate parameterized queries
- **CORS Configuration**: Proper cross-origin setup
- **Error Handling**: Graceful error responses

### Testing Strategy
- **Unit Tests**: Service layer business logic
- **Integration Tests**: API endpoint testing
- **AI Testing**: Minimax algorithm validation
- **Frontend Tests**: React component testing

## Troubleshooting

### Common Issues

1. **Backend won't start**
   - Check Java version (must be 17+)
   - Verify Maven installation
   - Check port 8080 availability

2. **Frontend won't start**
   - Check Node.js version (must be 16+)
   - Run `npm install` to install dependencies
   - Check port 3000 availability

3. **Database connection issues**
   - H2 console available at `/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`, Password: `password`

4. **AI not responding**
   - Check if current player is AI type
   - Verify game status is "IN_PROGRESS"
   - Check backend logs for errors

## Future Enhancements

### Potential Improvements
- **Alpha-Beta Pruning**: Optimize AI performance for larger boards
- **Difficulty Levels**: Easy, Medium, Hard AI opponents
- **Tournament Mode**: Multiple games with statistics
- **Mobile App**: React Native version
- **Real-time Multiplayer**: WebSocket support
- **Advanced Analytics**: Game statistics and insights

### Technical Debt
- **Error Handling**: More comprehensive error responses
- **Logging**: Structured logging with log levels
- **Monitoring**: Health checks and metrics
- **Documentation**: OpenAPI/Swagger documentation

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For questions or support, please contact the development team.

---

**Happy Gaming! 🎮**
