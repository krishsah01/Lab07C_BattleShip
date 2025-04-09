import java.util.ArrayList;

public class Ship {
    private int size;
    private boolean isVertical;
    private int startRow, startCol;
    private ArrayList<Cell> occupiedCells;

    public Ship(int size, int startRow, int startCol, boolean isVertical) {
        this.size = size;
        this.startRow = startRow;
        this.startCol = startCol;
        this.isVertical = isVertical;
        this.occupiedCells = new ArrayList<>();
    }

    public void addOccupiedCell(Cell cell) {
        occupiedCells.add(cell);
    }

    public ArrayList<Cell> getOccupiedCells() {
        return occupiedCells;
    }

    public boolean occupiesCell(int row, int col) {
        System.out.println(row+ " " + col);
        for (Cell cell : occupiedCells) {
            if (cell.getRow() == row && cell.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    public boolean isSunk(Cell[][] grid) {
        for (Cell cell : occupiedCells) {
            if (!grid[cell.getRow()][cell.getCol()].isRevealed()) {
                return false;
            }
        }
        return true;
    }
}
