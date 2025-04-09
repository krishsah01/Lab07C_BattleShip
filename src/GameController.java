import javax.swing.*;
import java.awt.*;

public class GameController {
    private GameGUI gui;
    private BattleshipGame game;

    public GameController(GameGUI gui) {
        this.gui = gui;
        this.game = new BattleshipGame();
    }

    public void startGame() {
        game.startNewGame();
        gui.resetBoard();
        updateStatus();
    }

    public void handleCellClick(int row, int col) {
        String result = game.fireAt(row, col);
        JButton btn = gui.getButton(row, col);
        System.out.println("Result at " + row + "," + col + " = " + result);

        switch (result) {
            case "HIT":
                btn.setBackground(Color.RED);
                btn.setText("X");
                break;
            case "MISS":
                btn.setBackground(Color.YELLOW);
                btn.setText("M");
                break;
            case "SUNK":
                btn.setBackground(Color.RED);
                btn.setText("X");
                gui.showMessage("You sunk a ship!");
                break;
            case "WIN":
                btn.setBackground(Color.RED);
                btn.setText("X");
                gui.showMessage("You win! Play again?");
                if (gui.confirmReset() == JOptionPane.YES_OPTION) startGame();
                return;
            case "LOSS":
                btn.setBackground(Color.YELLOW);
                btn.setText("M");
                gui.showMessage("You lost! Play again?");
                if (gui.confirmReset() == JOptionPane.YES_OPTION) startGame();
                return;
            default:
                return;
        }

        btn.setEnabled(false);
        updateStatus();
    }

    public void updateStatus() {
        ScoreTracker s = game.getScoreTracker();
        gui.updateLabels(s.getMissCount(), s.getStrikeCount(), s.getTotalHits());
    }

    public void handlePlayAgain() {
        if (gui.confirmReset() == JOptionPane.YES_OPTION) {
            startGame();
        }
    }

    public void handleQuit() {
        int confirm = JOptionPane.showConfirmDialog(null, "Quit the game?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}