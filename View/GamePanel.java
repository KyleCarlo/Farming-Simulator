package View;

import javax.swing.JPanel;

import java.awt.Dimension;

import Control.*;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.Color;

/**
 * The `GamePanel` class represents a panel that displays the game graphics
 * and updates the game state. It implements the `Runnable` interface to
 * be executed in a separate thread.
 */

public class GamePanel extends JPanel implements Runnable {
    // constants for the original tile size, scaling factor, maximum number of
    // screen columns and rows, and frame rate
    private final int originalTileSize = 16;
    private final int scale = 4;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 9;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;
    private final int FPS = 60;

    // fields for the key control, tool control, game thread, tile control, crop
    // control, farmer control, farm control, and info control
    private KeyControl keyControl = new KeyControl();
    private ToolControl toolControl = new ToolControl();
    private Thread gameThread;
    private TileControl tileControl = new TileControl(this);
    private CropControl cropControl = new CropControl(this, tileControl);
    private FarmerControl farmerControl = new FarmerControl(this, keyControl, tileControl, toolControl);
    private FarmControl farmControl = new FarmControl(tileControl.getFarm(), keyControl);
    private InfoControl infoControl = new InfoControl(farmerControl.getFarmer(), tileControl.getFarm());
    private InfoPanel infoPanel = new InfoPanel(this);

    /**
     * Constructs a new `GamePanel` instance.
     */
    public GamePanel() {
        // set the panel's size and background color, and enable double buffering
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        // add the key and tool controls as listeners, and make the panel focusable
        this.addKeyListener(keyControl);
        this.addKeyListener(toolControl);
        this.setFocusable(true);
    }

    /**
     * Starts the game thread.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Returns the tile size of the game.
     *
     * @return the tile size of the game
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Returns the maximum number of screen columns of the game.
     *
     * @return the maximum number of screen columns of the game
     */
    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    /**
     * Returns the maximum number of screen rows of the game.
     *
     * @return the maximum number of screen rows of the game
     */
    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    /**
     * Returns the screen width of the game.
     *
     * @return the screen width of the game
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Returns the screen height of the game.
     *
     * @return the screen height of the game
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * Returns the frames per second of the game.
     *
     * @return the frames per second of the game.
     */
    public int getFPS() {
        return FPS;
    }

    /**
     * Returns the key control of the game.
     * 
     * @return the frames per second of the game.
     */
    public FarmerControl getFarmerControl() {
        return farmerControl;
    }

    /**
     * Returns the tile control of the game.
     * 
     * @return the tile control of the game.
     */
    public TileControl getTileControl() {
        return tileControl;
    }

    /**
     * Returns the info panel of the game.
     * 
     * @return the tile control of the game.
     */
    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    // The `run` method is executed in a separate thread. It updates the game
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double diff = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            diff += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (diff >= 1) {
                update(); // update game state
                repaint(); // repainting the panel
                infoPanel.updateText(infoControl.getGenInfo(),
                        infoControl.getCropInfo(), infoControl.getOtherInfo()); // update info panel
                diff--;
            }
        }
    }

    /**
     * Updates the game state.
     */
    public void update() {
        farmerControl.update();
        farmControl.update();
        infoControl.setGenInfo();
        infoControl.setCropInfo(farmerControl.getFarmer().getSeed().getName());
        infoControl.setOtherInfo();
        if (infoControl.getOtherInfo() != null) {
            if (infoControl.getOtherInfo().equals("\n    GAME OVER")) {
                this.removeKeyListener(keyControl);
                this.removeKeyListener(toolControl);
            }
        }

    }

    /**
     * Draws the game graphics.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileControl.draw(g2);
        cropControl.draw(g2);
        farmerControl.draw(g2);

        g2.dispose();
    }
}
