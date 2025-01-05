package Control;

import Model.Crop;
import Model.Tile;
import View.GamePanel;

import java.awt.Graphics2D;

/**
 * The CropControl class is used to control the behavior and rendering of Crop
 * objects in the game. It contains a reference to the GamePanel and a 2D array
 * of Tile objects representing the game's farm. It has a draw method that is
 * used to render the Crop objects on the GamePanel using the Graphics2D object
 * passed as an argument.
 */
public class CropControl {
    private GamePanel gamePanel;
    private Tile[][] tiles;

    /**
     * Constructor for crop control
     * 
     * @param gamePanel
     * @param tileControl
     */
    public CropControl(GamePanel gamePanel, TileControl tileControl) {
        this.gamePanel = gamePanel;
        this.tiles = new Tile[5][10];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                this.tiles[i][j] = tileControl.getFarm().getTile(i, j);
            }
        }
    }

    /**
     * Draw the crops on the game panel
     * 
     * @param g2d
     */
    public void draw(Graphics2D g2d) {
        Crop[][] crops = new Crop[5][10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                crops[i][j] = this.tiles[i][j].getCrop();
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.tiles[i][j].getCrop() != null) {
                    g2d.drawImage(crops[i][j].getDefaultImage('0'), this.tiles[i][j].getX(),
                            this.tiles[i][j].getY(),
                            gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                }
            }
        }
    }
}
