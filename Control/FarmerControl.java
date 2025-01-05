package Control;

import View.GamePanel;
import Model.Farmer;
import Model.Tile;

import java.awt.image.BufferedImage;

import java.awt.Graphics2D;

import java.util.ArrayList;

/**
 * The FarmerControl class manages the behavior and movement of the farmer
 * object in the game. It receives input from the user through the KeyControl
 * class and uses it to move the farmer around the game screen. The
 * FarmerControl class also interacts with other game objects, such as the Tile
 * and Crop classes, through the TileControl and ToolControl classes to allow
 * the farmer to plow, water, and fertilize crops, and remove rocks from the
 * game screen. The FarmerControl class also checks for collisions with
 * obstacles on the game screen to prevent the farmer from moving through them.
 */
public class FarmerControl {
    private Farmer farmer;
    private GamePanel gamePanel;
    private KeyControl keyControl;
    private ToolControl toolControl;
    private Tile[][] farmTiles;
    private ArrayList<Integer> forCollisions;

    /**
     * Constructor for farmer control
     * 
     * @param gamePanel
     * @param keyControl
     * @param tileControl
     * @param toolControl
     */
    public FarmerControl(GamePanel gamePanel, KeyControl keyControl, TileControl tileControl, ToolControl toolControl) {
        this.farmer = new Farmer();
        this.gamePanel = gamePanel;
        this.keyControl = keyControl;
        this.toolControl = toolControl;

        this.farmTiles = new Tile[5][10];
        this.forCollisions = new ArrayList<Integer>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                this.farmTiles[i][j] = tileControl.getFarm().getTile(i, j);
                if (!this.farmTiles[i][j].getIsPath()) {
                    System.out.println("ROCKS at " + (i + 2) + " " + (j + 3));
                    this.forCollisions.add(i + 2);
                    this.forCollisions.add(j + 3);
                }
            }
        }
    }

    /**
     * Updates the rock collisions
     */
    public void updatePath() {
        this.forCollisions.clear();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (!this.farmTiles[i][j].getIsPath()) {
                    System.out.println("ROCKS at " + (i + 2) + " " + (j + 3));
                    this.forCollisions.add(i + 2);
                    this.forCollisions.add(j + 3);
                }
            }
        }
    }

    /**
     * Checks for collisions with rocks
     */
    public void update() {
        if (keyControl.upPressed || keyControl.downPressed || keyControl.leftPressed || keyControl.rightPressed) {
            if (keyControl.upPressed && isColliding() != "up" && this.farmer.getY() > 0) {
                this.farmer.moveUp();
            } else if (keyControl.downPressed && isColliding() != "down"
                    && this.farmer.getY() < this.gamePanel.getScreenHeight() - this.gamePanel.getTileSize()) {
                this.farmer.moveDown();
            } else if (keyControl.leftPressed && isColliding() != "left"
                    && this.farmer.getX() > this.gamePanel.getTileSize()) {
                this.farmer.moveLeft();
            } else if (keyControl.rightPressed && isColliding() != "right"
                    && this.farmer.getX() < this.gamePanel.getScreenWidth() - (this.gamePanel.getTileSize() * 2)) {
                this.farmer.moveRight();
            }
            this.farmer.render();
        }

        int[] facingTile = this.farmer.getFacingRowCol();
        if (facingTile[0] >= 3 && facingTile[0] < 13 && facingTile[1] >= 2 && facingTile[1] < 7) {
            Tile currentTile = this.farmTiles[facingTile[1] - 2][facingTile[0] - 3];
            if (toolControl.plowPressed) {
                this.farmer.getTool("Plow").useTool(currentTile, farmer);
            } else if (toolControl.waterPressed) {
                this.farmer.getTool("Watering Can").useTool(currentTile, farmer);
            } else if (toolControl.fertPressed) {
                this.farmer.getTool("Fertilizer").useTool(currentTile, farmer);
            } else if (toolControl.pickaxePressed) {
                this.farmer.getTool("Pickaxe").useTool(currentTile, farmer);
                this.updatePath();
            } else if (toolControl.shovelPressed) {
                this.farmer.getTool("Shovel").useTool(currentTile, farmer);
                toolControl.shovelPressed = false;
            }

            if (keyControl.plantPressed) {
                this.farmer.plantSeed(facingTile[1] - 2, facingTile[0] - 3, farmTiles);
            } else if (keyControl.pressed1) {
                this.farmer.setSeed("Turnip");
            } else if (keyControl.pressed2) {
                this.farmer.setSeed("Carrot");
            } else if (keyControl.pressed3) {
                this.farmer.setSeed("Potato");
            } else if (keyControl.pressed4) {
                this.farmer.setSeed("Rose");
            } else if (keyControl.pressed5) {
                this.farmer.setSeed("Tulips");
            } else if (keyControl.pressed6) {
                this.farmer.setSeed("Sunflower");
            } else if (keyControl.pressed7) {
                this.farmer.setSeed("Mango");
            } else if (keyControl.pressed8) {
                this.farmer.setSeed("Apple");
            }

            if (keyControl.hPressed) {
                this.farmer.harvestCrop(currentTile);
            }
        }

        if (keyControl.uPressed && this.farmer.getLevel() >= 5 && this.farmer.getFarmerType().equals("farmer")
                && this.farmer.getObjectCoins() >= 200) {
            this.farmer.setFarmerType("registered");
        } else if (keyControl.uPressed && this.farmer.getLevel() >= 10
                && this.farmer.getFarmerType().equals("registered") && this.farmer.getObjectCoins() >= 300) {
            this.farmer.setFarmerType("distinguished");
        } else if (keyControl.uPressed && this.farmer.getLevel() >= 15
                && this.farmer.getFarmerType().equals("distinguished") && this.farmer.getObjectCoins() >= 400) {
            this.farmer.setFarmerType("legendary");
        }
    }

    /**
     * Checks for collisions with rocks
     * 
     * @return the direction of the collision
     */
    public String isColliding() {
        int tileSize = this.gamePanel.getTileSize();
        int tileMove = this.farmer.getSpeed();

        for (int i = 0; i < this.forCollisions.size(); i += 2) {
            int obstacleRow = this.forCollisions.get(i);
            int obstacleCol = this.forCollisions.get(i + 1);

            switch (this.farmer.getDirection()) {
                case "up":
                    if (this.farmer.getX() + tileSize - 16 >= obstacleCol * tileSize &&
                            this.farmer.getX() <= obstacleCol * tileSize + tileSize - 16 &&
                            this.farmer.getY() - tileMove <= (obstacleRow + 1) * tileSize - 16 &&
                            this.farmer.getY() - tileMove >= obstacleRow * tileSize)
                        return "up";
                    break;
                case "down":
                    if (this.farmer.getX() + tileSize - 16 >= obstacleCol * tileSize &&
                            this.farmer.getX() <= obstacleCol * tileSize + tileSize - 16 &&
                            this.farmer.getY() + tileMove >= (obstacleRow - 1) * tileSize &&
                            this.farmer.getY() + tileMove <= obstacleRow * tileSize)
                        return "down";
                    break;
                case "left":
                    if (this.farmer.getY() + tileSize - 16 >= obstacleRow * tileSize &&
                            this.farmer.getY() <= obstacleRow * tileSize + tileSize - 16 &&
                            this.farmer.getX() - tileMove <= (obstacleCol + 1) * tileSize - 16 &&
                            this.farmer.getX() - tileMove >= obstacleCol * tileSize)
                        return "left";
                    break;
                case "right":
                    if (this.farmer.getY() + tileSize - 16 >= obstacleRow * tileSize &&
                            this.farmer.getY() <= obstacleRow * tileSize + tileSize - 16 &&
                            this.farmer.getX() + tileMove >= (obstacleCol - 1) * tileSize + 16 &&
                            this.farmer.getX() + tileMove <= obstacleCol * tileSize)
                        return "right";
                    break;
                default:
                    break;
            }
        }

        return null;
    }

    /**
     * Checks for collisions with trees
     * 
     * @param g2
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        BufferedImage tileSelect = this.farmer.getDefaultImage('f');
        int imageNum = this.farmer.getSpriteNum();
        int[] facingTile = this.farmer.getFacingRowCol();

        switch (this.farmer.getDirection()) {
            case "up":
                image = this.farmer.getBackImage(imageNum);
                break;
            case "down":
                image = this.farmer.getFrontImage(imageNum);
                break;
            case "left":
                image = this.farmer.getLeftImage(imageNum);
                break;
            case "right":
                image = this.farmer.getRightImage(imageNum);
                break;
        }

        if (facingTile[0] > 2 && facingTile[0] < 13 && facingTile[1] > 1 && facingTile[1] < 7) {
            g2.drawImage(tileSelect, facingTile[0] * gamePanel.getTileSize(), facingTile[1] * gamePanel.getTileSize(),
                    gamePanel.getTileSize(),
                    gamePanel.getTileSize(), null);
        }

        g2.drawImage(image, this.farmer.getX(), this.farmer.getY(), gamePanel.getTileSize(), gamePanel.getTileSize(),
                null);
    }

    /**
     * Gets the farmer
     * 
     * @return the farmer
     */
    public Farmer getFarmer() {
        return this.farmer;
    }
}
