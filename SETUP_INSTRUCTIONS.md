# Quick Setup Instructions

## Prerequisites
- Java 17+
- Maven 3.6+
- Node.js 16+
- Git

## Quick Start

### 1. Clone the Repository
```bash
git clone <your-github-repo-url>
cd xo-game
```

### 2. Start Backend (Terminal 1)
```bash
# Install dependencies and run Spring Boot
mvn clean install
mvn spring-boot:run
```
Backend will be available at: http://localhost:8080

### 3. Start Frontend (Terminal 2)
```bash
cd frontend
npm install
npm start
```
Frontend will be available at: http://localhost:3000

### 4. Play the Game!
1. Open http://localhost:3000 in your browser
2. Choose board size (3x3 to 6x6)
3. Select player types (Human/AI)
4. Click "Start Game" and enjoy!

## Features Demonstrated
✅ **Configurable Board Size**: Any NxN board  
✅ **AI Opponent**: Unbeatable minimax algorithm  
✅ **Game History**: All games saved to database  
✅ **Replay Function**: Watch any finished game  
✅ **Modern UI**: Beautiful glassmorphism design  

## API Testing
```bash
# Create new game
curl -X POST http://localhost:8080/api/games \
  -H "Content-Type: application/json" \
  -d '{"boardSize": 4, "player1Type": "HUMAN", "player2Type": "AI"}'

# View all games
curl http://localhost:8080/api/games
```

## Database Access
- H2 Console: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`, Password: `password`

## Troubleshooting
- **Port conflicts**: Change ports in application.properties and package.json
- **Java version**: Ensure Java 17+ is installed
- **Node version**: Ensure Node.js 16+ is installed
- **Dependencies**: Run `mvn clean install` and `npm install`

Happy Gaming! 🎮