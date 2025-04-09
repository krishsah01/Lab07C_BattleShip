public class Cell {
    private int row;
    private int col;
    private boolean hasShip;
    private boolean revealed;
    private CellState state;

    public enum CellState {
        BLANK,
        MISS,
        HIT
    }

    public Cell() {
        this(0, 0);
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.hasShip = false;
        this.revealed = false;
        this.state = CellState.BLANK;
    }

    public void reveal() {
        if (!revealed) {
            this.revealed = true;
            this.state = hasShip ? CellState.HIT : CellState.MISS;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean hasShip() {
        return hasShip;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public CellState getState() {
        return state;
    }
}
