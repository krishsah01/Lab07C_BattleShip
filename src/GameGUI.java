import javax.swing.*;
import java.awt.*;

class GameGUI {
    private JFrame frame;
    private JPanel boardPanel;
    private JButton[][] buttons;
    private JLabel missLabel, strikeLabel, hitLabel;
    private JButton playAgainButton, quitButton;
    private GameController controller;

    public void createAndShowGUI() {
        frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        controller = new GameController(this);
        boardPanel = new JPanel(new GridLayout(10, 10));
        buttons = new JButton[10][10];

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                JButton button = new JButton();
                button.setBackground(Color.CYAN);
                button.setOpaque(true);
                button.setContentAreaFilled(true);
                button.setBorderPainted(false);
                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 👈 Add this

                int r = row;
                int c = col;
                button.addActionListener(e -> controller.handleCellClick(r, c));

                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }


        JPanel statusPanel = new JPanel(new GridLayout(1, 3));
        missLabel = new JLabel("Misses: 0");
        strikeLabel = new JLabel("Strikes: 0");
        hitLabel = new JLabel("Hits: 0");
        statusPanel.add(missLabel);
        statusPanel.add(strikeLabel);
        statusPanel.add(hitLabel);

        JPanel controlPanel = new JPanel();
        playAgainButton = new JButton("Play Again");
        quitButton = new JButton("Quit");

        playAgainButton.addActionListener(e -> controller.handlePlayAgain());
        quitButton.addActionListener(e -> controller.handleQuit());

        controlPanel.add(playAgainButton);
        controlPanel.add(quitButton);

        frame.add(statusPanel, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setSize(600, 700);
        frame.setVisible(true);

        controller.startGame();
    }

    public JButton getButton(int row, int col) {
        return buttons[row][col];
    }

    public void updateLabels(int misses, int strikes, int hits) {
        missLabel.setText("Misses: " + misses);
        strikeLabel.setText("Strikes: " + strikes);
        hitLabel.setText("Hits: " + hits);
    }

    public int confirmReset() {
        return JOptionPane.showConfirmDialog(frame, "Are you sure you want to play again?", "Confirm", JOptionPane.YES_NO_OPTION);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(frame, msg);
    }

    public void resetBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                JButton btn = buttons[row][col];
                btn.setBackground(Color.CYAN);
                btn.setEnabled(true);
                btn.setText("");
            }
        }
    }
}
