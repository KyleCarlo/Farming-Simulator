package Control;

import java.awt.Graphics2D;

import Model.ReadFile;
import Model.Tile;
import Model.Farm;
import View.GamePanel;

/**
 * 
 * The TileControl class is used to manage the tiles in the game.
 * It creates a new Farm object, which is a two-dimensional array of Tile
 * objects.
 * It also reads a file to determine which tiles should be rocks, and sets their
 * isRock property accordingly.
 * The draw() method is used to render the tiles on the screen.
 * 
 * @author [your name]
 * @version [version number]
 * @since [what version of the program this class is in]
 */
public class TileControl {
    private GamePanel gamePanel;
    private Tile defaultTile;
    private Farm farm;
    private ReadFile file;
    private String map;
    private String[] lines;

    /**
     * Constructor for TileControl
     * 
     * @param gamePanel
     */
    public TileControl(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.defaultTile = new Tile();
        this.farm = new Farm();
        this.file = new ReadFile("tilemapping.txt");
        this.map = file.getContent();
        this.lines = map.split("\n");

        int tileSize = gamePanel.getTileSize();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                this.farm.getTile(i, j).setPos(j * tileSize + 3 * tileSize, i * tileSize + 2 * tileSize);

                if (lines[i + 2].charAt(j + 3) == '1') {
                    this.farm.getTile(i, j).setRock(true);
                }
            }
        }
    }

    /**
     * Renders the tiles on the screen.
     *
     * @param g2 the `Graphics2D` object used to draw on the screen
     */
    public void draw(Graphics2D g2) {

        int farmRow = 0, farmCol = 0;
        for (int i = 0; i < gamePanel.getMaxScreenRow(); i++) {
            for (int j = 0; j < gamePanel.getMaxScreenCol(); j++) {
                if (lines[i].charAt(j) != '0' && lines[i].charAt(j) != '1') {
                    g2.drawImage(defaultTile.getDefaultImage(lines[i].charAt(j)), j * gamePanel.getTileSize(),
                            i * gamePanel.getTileSize(),
                            gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                } else {
                    g2.drawImage(this.farm.getTile(farmRow, farmCol).getDefaultImage('0'),
                            j * gamePanel.getTileSize(),
                            i * gamePanel.getTileSize(),
                            gamePanel.getTileSize(), gamePanel.getTileSize(), null);

                    if (this.farm.getTile(farmRow, farmCol).getIsRock()) {
                        g2.drawImage(this.farm.getTile(farmRow, farmCol).getDefaultImage('1'),
                                j * gamePanel.getTileSize(),
                                i * gamePanel.getTileSize(),
                                gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                    } else if (this.farm.getTile(farmRow, farmCol).getIsPlowed()) {
                        g2.drawImage(this.farm.getTile(farmRow, farmCol).getDefaultImage('2'),
                                j * gamePanel.getTileSize(),
                                i * gamePanel.getTileSize(),
                                gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                        if (this.farm.getTile(farmRow, farmCol).getIsWatered()) {
                            g2.drawImage(this.farm.getTile(farmRow, farmCol).getDefaultImage('3'),
                                    j * gamePanel.getTileSize(),
                                    i * gamePanel.getTileSize(),
                                    gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                        }
                    }
                    farmCol++;
                }
            }
            if (i > 1) {
                farmRow++;
                farmCol = 0;
            }
        }
    }

    /**
     * Returns the farm object.
     * 
     * @return farm
     */
    public Farm getFarm() {
        return this.farm;
    }
}
