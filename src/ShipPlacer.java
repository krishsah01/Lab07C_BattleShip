import java.util.Random;

public class ShipPlacer {
    private Board board;
    private Random random = new Random();
    private static final int[] SHIP_SIZES = {5, 4, 3, 3, 2};

    public ShipPlacer(Board board) {
        this.board = board;
    }

    public void placeAllShips() {
        for (int size : SHIP_SIZES) {
            boolean placed = false;
            while (!placed) {
                boolean isVertical = random.nextBoolean();
                int row = random.nextInt(10);
                int col = random.nextInt(10);

                if (canPlaceShip(size, row, col, isVertical)) {
                    Ship ship = new Ship(size, row, col, isVertical);
                    for (int i = 0; i < size; i++) {
                        int r = isVertical ? row + i : row;
                        int c = isVertical ? col : col + i;
                        Cell cell = board.getCell(r, c);
                        ship.addOccupiedCell(cell);
                    }
                    board.placeShip(ship);
                    placed = true;
                }
            }

        }

    }
    private boolean canPlaceShip(int size, int row, int col, boolean isVertical) {
        int end = isVertical ? row + size : col + size;
        if (end > 10) return false;

        for (int i = 0; i < size; i++) {
            int r = isVertical ? row + i : row;
            int c = isVertical ? col : col + i;
            if (board.getCell(r, c).hasShip()) {
                return false;
            }
        }
        return true;
    }
}
