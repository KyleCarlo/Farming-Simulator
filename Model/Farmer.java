package Model;

import java.io.IOException;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.util.HashMap;

/**
 * The Farmer class is a class that represents the farmer in the game. It
 * extends from the Entity class and implements the Movable and Renderable
 * interfaces.
 */
public class Farmer extends Entity implements Movable, Renderable {
    /**
     * The Farmer class has several fields such as name, exp, level, objectCoins,
     * bonusEarnPerProd, seedCostReduction, waterBonusIncrease, fertBonusIncrease,
     * farmerType, and tools.
     * 
     * It also has a Seed instance variable called seed and a boolean called
     * isHarvesting that keeps track if the farmer is currently harvesting a crop.
     * 
     * The Farmer class also has a double array called latestEarnings that keeps
     * track of the farmer's latest earnings. The class provides methods for the
     * farmer to plant a seed, water a crop, fertilize a crop, use a tool, harvest a
     * crop, and move around the farm. It also has a method for rendering the
     * farmer's sprite.
     */
    private String name;
    private double exp = 400;
    private int level = 0;
    private int objectCoins = 400;
    private int bonusEarnPerProd = 0;
    private int seedCostReduction = 0;
    private int waterBonusIncrease = 0;
    private int fertBonusIncrease = 0;
    private String farmerType = "farmer";
    private HashMap<String, Tool> tools = new HashMap<String, Tool>();
    private Seed seed = new Seed("Turnip");
    private boolean isHarvesting = false;
    private double[] latestEarnings = { 0, 0 };

    /**
     * Constructs a `Farmer` object with default values.
     */
    public Farmer() {
        this.setDefaultValues();
        this.setFarmerImage();
    }

    /**
     * Sets the default values needed for the `Farmer` object.
     */
    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
        this.direction = "down";
        this.tools.put("Plow", new Tool("Plow"));
        this.tools.put("Watering Can", new Tool("Watering Can"));
        this.tools.put("Fertilizer", new Tool("Fertilizer"));
        this.tools.put("Pickaxe", new Tool("Pickaxe"));
        this.tools.put("Shovel", new Tool("Shovel"));
    }

    /**
     * Planting the seed action
     */
    public void plantSeed(int row, int col, Tile[][] farmTiles) {
        HashMap<String, Tile> adjTiles = new HashMap<>();
        Crop crop = new Crop(this.seed.getName(), this);
        Tile facingTile = farmTiles[row][col];
        if (this.objectCoins >= crop.getSeedCost() && facingTile.getIsPlowed() && facingTile.getCrop() == null) {
            if (!crop.getCropType().equals("fruit tree") && !facingTile.getIsBlock()) {
                facingTile.setCrop(crop);
                this.subCoins(crop.getSeedCost());
                if (facingTile.getIsWatered()) {
                    facingTile.getCrop().water();
                }
            } else if (row - 1 >= 0 && row + 1 < 10 && col - 1 >= 0 && col + 1 < 10) {
                adjTiles.put("left", farmTiles[row][col - 1]);
                adjTiles.put("right", farmTiles[row][col + 1]);
                adjTiles.put("up", farmTiles[row - 1][col]);
                adjTiles.put("down", farmTiles[row + 1][col]);
                adjTiles.put("upleft", farmTiles[row - 1][col - 1]);
                adjTiles.put("upright", farmTiles[row - 1][col + 1]);
                adjTiles.put("downleft", farmTiles[row + 1][col - 1]);
                adjTiles.put("downright", farmTiles[row + 1][col + 1]);

                if (col >= 0 && col < 10 && row >= 0 && row < 10 && adjTiles.get("left").getIsPlowed()
                        && adjTiles.get("right").getIsPlowed() && adjTiles.get("up").getIsPlowed()
                        && adjTiles.get("down").getIsPlowed() && adjTiles.get("upleft").getIsPlowed()
                        && adjTiles.get("upright").getIsPlowed() && adjTiles.get("downleft").getIsPlowed()
                        && adjTiles.get("downright").getIsPlowed() &&
                        adjTiles.get("left").getCrop() == null &&
                        adjTiles.get("right").getCrop() == null &&
                        adjTiles.get("up").getCrop() == null &&
                        adjTiles.get("down").getCrop() == null &&
                        adjTiles.get("upleft").getCrop() == null &&
                        adjTiles.get("upright").getCrop() == null &&
                        adjTiles.get("downleft").getCrop() == null &&
                        adjTiles.get("downright").getCrop() == null) {

                    for (String each : adjTiles.keySet()) {
                        adjTiles.get(each).setBlock(true);
                    }
                    facingTile.setCrop(crop);
                    this.subCoins(crop.getSeedCost());
                    if (facingTile.getIsWatered()) {
                        facingTile.getCrop().water();
                    }
                }
            }
        }
    }

    /**
     * Harvest the crop action
     *
     * @param tile
     */
    public void harvestCrop(Tile tile) {
        if (tile.getCrop() != null) {
            if (tile.getCrop().isHarvestable()) {
                Crop crop = tile.getCrop();
                crop.setProdAmount();

                this.latestEarnings[0] = crop.getSellingPrice();
                this.latestEarnings[1] = crop.getExpGain();

                this.addCoins(this.latestEarnings[0]);
                this.addExp(this.latestEarnings[1]);
                tile.setCrop(null);
                tile.setPlowed(false);
                tile.setPath(true);
                this.setIsHarvesting(true);

            }
        }
    }

    /**
     * Sets the seed on the `Farmer` object.
     * 
     * @param seedName
     */
    public void setSeed(String seedName) {
        this.seed = new Seed(seedName);
    }

    /**
     * Sets the `isHarvesting` boolean on the `Farmer` object.
     * 
     * @param isHarvesting
     */
    public void setIsHarvesting(boolean isHarvesting) {
        this.isHarvesting = isHarvesting;
    }

    /**
     * Gets the harvest status of the `Farmer` object.
     * 
     * @return harvest status
     */
    public boolean getIsHarvesting() {
        return this.isHarvesting;
    }

    /**
     * Gets the `Seed` object on the `Farmer` object.
     * 
     * @return seed
     */
    public Seed getSeed() {
        return this.seed;
    }

    /**
     * Gets the speed or movement of the farmer
     * 
     * @return speed of farmer
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Gets the `Tool` object on the `Farmer` object.
     * 
     * @param toolName
     * @return tool
     */
    public Tool getTool(String toolName) {
        return this.tools.get(toolName);
    }

    /**
     * Gets the experience of the `Farmer` object.
     * 
     * @return exp
     */
    public double getExp() {
        return this.exp;
    }

    /**
     * Gets the latest earnings for the information panel.
     * 
     * @return latestEarnings
     */
    public double[] getLatestEarnings() {
        return this.latestEarnings;
    }

    /**
     * Sets the farmer images for the farmer.
     */
    public void setFarmerImage() {
        try {
            front1 = ImageIO.read(getClass().getResource("/resources/farmer/afront1.png"));
            front2 = ImageIO.read(getClass().getResource("/resources/farmer/afront2.png"));
            back1 = ImageIO.read(getClass().getResource("/resources/farmer/aback1.png"));
            back2 = ImageIO.read(getClass().getResource("/resources/farmer/aback2.png"));
            left1 = ImageIO.read(getClass().getResource("/resources/farmer/aleft1.png"));
            left2 = ImageIO.read(getClass().getResource("/resources/farmer/aleft2.png"));
            right1 = ImageIO.read(getClass().getResource("/resources/farmer/aright1.png"));
            right2 = ImageIO.read(getClass().getResource("/resources/farmer/aright2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Override the Movable interface method.
     */
    @Override // Movable
    public void moveUp() {
        this.y -= this.speed;
        this.direction = "up";
    }

    public void moveDown() {
        this.y += this.speed;
        this.direction = "down";
    }

    public void moveLeft() {
        this.x -= this.speed;
        this.direction = "left";
    }

    public void moveRight() {
        this.x += this.speed;
        this.direction = "right";
    }

    /**
     * Gets the X coordinate of the `Farmer` object.
     * 
     * @return x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the Y coordinate of the `Farmer` object.
     * 
     * @return y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Gets the facing tile of the `Farmer` object.
     * 
     * @return int[] of the row and column of the tile the farmer is facing
     */
    public int[] getFacingRowCol() {
        int[] facingTile = new int[2];
        switch (this.direction) {
            case "up":
                facingTile[0] = (this.x - 24) / 64 + 1;
                facingTile[1] = (this.y - 24) / 64;
                break;
            case "down":
                facingTile[0] = (this.x + 32) / 64;
                facingTile[1] = (this.y + 64 + 24) / 64;
                break;
            case "left":
                facingTile[0] = (this.x - 24) / 64;
                facingTile[1] = (this.y - 24) / 64 + 1;
                break;
            case "right":
                facingTile[0] = (this.x + 64 + 24) / 64;
                facingTile[1] = (this.y - 24) / 64 + 1;
                break;
        }
        return facingTile;
    }

    /**
     * Changing the sprite of the farmer for animation
     */
    public void render() {
        spriteCounter++;
        if (spriteCounter > 8) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    /**
     * Override the Renderable interface method.
     */
    @Override // Renderable
    public BufferedImage getDefaultImage(char condition) {
        try {

            BufferedImage facingTile = ImageIO.read(getClass().getResource("/resources/farmer/facingTile.png"));
            return facingTile;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public BufferedImage getFrontImage(int num) {
        if (num == 1) {
            return front1;
        } else {
            return front2;
        }
    }

    public BufferedImage getBackImage(int num) {
        if (num == 1) {
            return back1;
        } else {
            return back2;
        }
    }

    public BufferedImage getLeftImage(int num) {
        if (num == 1) {
            return left1;
        } else {
            return left2;
        }
    }

    public BufferedImage getRightImage(int num) {
        if (num == 1) {
            return right1;
        } else {
            return right2;
        }
    }

    /**
     * Adds experience to the `Farmer` object.
     * 
     * @param exp
     */
    public void addExp(double exp) {
        this.exp += exp;
        this.level = (int) this.exp / 100;
    }

    /**
     * Adds coins to the `Farmer` object.
     * 
     * @param coins
     */
    public void addCoins(double coins) {
        this.objectCoins += coins;
    }

    /**
     * Subtracts coins from the `Farmer` object.
     * 
     * @param coins
     */
    public void subCoins(double coins) {
        this.objectCoins -= coins;
    }

    /**
     * Gets the coins of the `Farmer` object.
     * 
     * @param farmerType
     */
    public void setFarmerType(String farmerType) {
        this.farmerType = farmerType;
        switch (this.farmerType) {
            case "registered":
                if (this.objectCoins >= 200 && this.level >= 5) {
                    this.bonusEarnPerProd = 1;
                    this.seedCostReduction = 1;
                    this.subCoins(200);
                }
                break;
            case "distinguished":
                if (this.objectCoins >= 300 && this.level >= 10 && this.farmerType.equals("registered")) {
                    this.bonusEarnPerProd = 2;
                    this.seedCostReduction = 2;
                    this.waterBonusIncrease = 1;
                    this.subCoins(300);
                }
                break;
            case "legendary":
                if (this.objectCoins >= 400 && this.level >= 15 && this.farmerType.equals("distinguished")) {
                    this.bonusEarnPerProd = 3;
                    this.seedCostReduction = 3;
                    this.waterBonusIncrease = 2;
                    this.fertBonusIncrease = 1;
                    this.subCoins(500);
                }
                break;
        }
    }

    /**
     * Gets the name of the `Farmer` object.
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the level of the `Farmer` object.
     * 
     * @return level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Gets the coins of the `Farmer` object.
     * 
     * @return objectCoins
     */
    public int getObjectCoins() {
        return this.objectCoins;
    }

    /**
     * Gets the farmer type of the `Farmer` object.
     * 
     * @return farmerType
     */
    public String getFarmerType() {
        return this.farmerType;
    }

    /**
     * Gets the bonus earn per product of the `Farmer` object.
     * 
     * @return bonusEarnPerProd
     */
    public int getBonusEarnPerProd() {
        return this.bonusEarnPerProd;
    }

    /**
     * Gets the seed cost reduction of the `Farmer` object.
     * 
     * @return seedCostReduction
     */
    public int getSeedCostReduction() {
        return this.seedCostReduction;
    }

    /**
     * Gets the water bonus increase of the `Farmer` object.
     * 
     * @return waterBonusIncrease
     */
    public int getWaterBonusIncrease() {
        return this.waterBonusIncrease;
    }

    /**
     * Gets the fertilizer bonus increase of the `Farmer` object.
     * 
     * @return fertBonusIncrease
     */
    public int getFertBonusIncrease() {
        return this.fertBonusIncrease;
    }
}
