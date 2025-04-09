import javax.swing.*;
import java.util.ArrayList;

public class Board {
    private Cell[][] grid;
    ArrayList<Ship> ships;

    public Board() {
        grid = new Cell[10][10];
        ships = new ArrayList<>();
        initializeGrid();
    }
    private void initializeGrid(){
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                grid[row][col] = new Cell(row, col);
            }
        }
    }

    public void clear(){
        ships.clear();
        initializeGrid();
    }
    public boolean isCellRevealed(int row, int col) {
        return grid[row][col].isRevealed();
    }

    public boolean fireMissile(int row, int col) {
        Cell cell = grid[row][col];
        cell.reveal();
        return cell.hasShip();
    }

    public void placeShip(Ship ship) {
        ships.add(ship);
        for (Cell cell : ship.getOccupiedCells()) {
            int row = cell.getRow();
            int col = cell.getCol();
            grid[row][col].setHasShip(true);
        }
    }

    public boolean isShipSunk(int row, int col) {
        for (Ship ship : ships) {
            if (ship.occupiesCell(row, col)) {
                return ship.isSunk(grid);
            }
        }
        return false;
    }

    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk(grid)) {
                return false;
            }
        }
        return true;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }
}
