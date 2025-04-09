import javax.swing.*;
import java.awt.event.ActionEvent;

public class AutomatedTestBattleship {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI();
            gui.createAndShowGUI();

            new Timer(100, e -> runTest(gui)).start();
        });
    }

    private static void runTest(GameGUI gui) {
        BattleshipGame game = new BattleshipGame();
        game.startNewGame();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (game.getBoard().getCell(row, col).hasShip()) {
                    gui.getButton(row, col).doClick();
                    System.out.println("Simulated HIT at: " + row + ", " + col);
                    return;
                }
            }
        }
    }
}
