package View;

import javax.swing.JFrame;

import java.awt.FlowLayout;

/**
 * The `Main` class is the entry point of the program. It creates and displays
 * the main window of the game and starts the game thread.
 */

public class Main {

    public static void main(String[] args) {
        /**
         * The main method that creates and displays the game window and starts the
         * game thread.
         *
         * @param args command-line arguments (not used in this program)
         */
        JFrame frame = new JFrame("My Farm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        InfoPanel infoPanel = gamePanel.getInfoPanel();

        frame.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        frame.setSize(gamePanel.getScreenWidth() + infoPanel.getScreenWidth() + 15, gamePanel.getScreenHeight());

        frame.add(gamePanel);
        frame.add(infoPanel);
        frame.setVisible(true);

        gamePanel.startGameThread();
    }
}
