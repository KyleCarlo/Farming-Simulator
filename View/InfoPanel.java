package View;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Dimension;

import java.awt.Color;

/**
 * The `InfoPanel` class represents a panel that displays game information.
 * It contains three text areas that can be updated with new information.
 */
public class InfoPanel extends JPanel {
    // fields for the screen width and height, and a reference to the game panel
    private int screenWidth;
    private int screenHeight;
    private GamePanel gamePanel;

    // text areas for displaying game information
    private JTextArea textArea1, textArea2, textArea3;

    /**
     * Constructs a new `InfoPanel` instance with the given game panel.
     *
     * @param gamePanel the game panel to be associated with this info panel
     */
    public InfoPanel(GamePanel gamePanel) {
        // save the game panel reference and initialize the screen size
        this.gamePanel = gamePanel;
        this.screenWidth = 300;
        this.screenHeight = this.gamePanel.getScreenHeight();

        // set the panel's size and border, and set the background color to black
        this.setPreferredSize(new Dimension(this.screenWidth, screenHeight));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.black);

        // create the text areas and add them to the panel
        this.textArea1 = new JTextArea(5, 30);
        this.textArea2 = new JTextArea(5, 30);
        this.textArea3 = new JTextArea(5, 30);
        this.add(this.textArea1);
        this.add(this.textArea2);
        this.add(this.textArea3);

        // set the text areas to be non-editable and non-focusable
        this.textArea1.setEditable(false);
        this.textArea1.setFocusable(false);
        this.textArea2.setEditable(false);
        this.textArea2.setFocusable(false);
        this.textArea3.setEditable(false);
        this.textArea3.setFocusable(false);
    }

    /**
     * Updates the text in the text areas with the given strings.
     *
     * @param info1 the text to be displayed in the first text area
     * @param info2 the text to be displayed in the second text area
     * @param info3 the text to be displayed in the third text area
     */
    public void updateText(String info1, String info2, String info3) {
        this.textArea1.setText(info1);
        this.textArea2.setText(info2);
        this.textArea3.setText(info3);
    }

    /**
     * Returns the screen width of this info panel.
     *
     * @return the screen width of this info panel
     */
    public int getScreenWidth() {
        return this.screenWidth;
    }

    /**
     * Returns the screen height of this info panel.
     *
     * @return the screen height of this info panel
     */
    public int getScreenHeight() {
        return this.screenHeight;
    }
}
