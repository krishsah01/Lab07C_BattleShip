public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()->{
            GameGUI game = new GameGUI();
            game.createAndShowGUI();
        });

    }
}
