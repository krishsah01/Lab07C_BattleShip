public class BattleshipGame {
    private Board board;
    private ShipPlacer shipPlacer;
    private ScoreTracker scoreTracker;
    private boolean gameOver;

    public BattleshipGame() {
        this.board = new Board();
        this.shipPlacer = new ShipPlacer(board);
        this.scoreTracker = new ScoreTracker();
        this.gameOver = false;
        startNewGame();
    }

    public void startNewGame() {
        board.clear();
        shipPlacer.placeAllShips();
        scoreTracker.reset();
        gameOver = false;
    }

    public String fireAt(int row, int col) {
        if (gameOver || board.isCellRevealed(row, col)) {
            return "IGNORED";
        }

        boolean isHit = board.fireMissile(row, col);

        if (isHit) {
            scoreTracker.registerHit();

            if (board.isShipSunk(row, col)) {
                if (board.allShipsSunk()) {
                    gameOver = true;
                    return "WIN";
                }
                return "SUNK";
            }

            return "HIT";
        } else {
            scoreTracker.registerMiss();
            if (scoreTracker.isStrikeLimitReached()) {
                gameOver = true;
                return "LOSS";
            }
            return "MISS";
        }
    }

    public Board getBoard() {
        return board;
    }

    public ScoreTracker getScoreTracker() {
        return scoreTracker;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
