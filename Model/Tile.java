package Model;

import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.util.HashMap;

import java.util.Map;

/**
 * The Tile class represents a tile on a farm. It contains information about the
 * tile's location, whether it is plowed, watered, a rock, a block, or contains
 * a crop. The Tile class also has a render() method which returns the image of
 * the tile depending on its state.
 * 
 * The Tile class implements the Renderable interface, which requires a render()
 * method to be implemented. This is because the Tile class represents a
 * renderable object in the game.
 * 
 * Tile objects are used to create the farm grid in the game, which the player
 * can interact with and perform actions such as planting seeds and using tools.
 */
public class Tile implements Renderable {
    private Map<Character, BufferedImage> defaultTiles;
    private boolean isPath = true;
    private int x, y;
    private boolean isPlowed, isWatered, isRock, isBlock;
    private Crop crop;

    /**
     * Constructs a Tile object with default values.
     */
    public Tile() {
        this.setDefaultTiles();
        this.isPlowed = false;
        this.isWatered = false;
        this.isRock = false;
        this.isBlock = false;
        this.crop = null;
    }

    /**
     * Constructs a Tile object with the specified x and y coordinates.
     * 
     * @param isPlowed
     */
    public void setPlowed(boolean isPlowed) {
        this.isPlowed = isPlowed;
    }

    /**
     * Sets the Tile's watered state to the specified boolean value.
     * 
     * @param isWatered
     */
    public void setWatered(boolean isWatered) {
        this.isWatered = isWatered;
    }

    /**
     * Sets the Tile's rock state to the specified boolean value.
     */
    public void setRock(boolean isRock) {
        this.isRock = isRock;
        if (isRock)
            this.isPath = false;
        else
            this.isPath = true;
    }

    /**
     * Sets the Tile's path state to the specified boolean value.
     * 
     * @param isPath
     */
    public void setPath(boolean isPath) {
        this.isPath = isPath;
    }

    /**
     * Sets the Tile's block state to the specified boolean value.
     * 
     * @param isBlock
     */
    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    /**
     * Sets the Tile's x and y coordinates to the specified values.
     * 
     * @param x
     * @param y
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the Tile's crop to the specified Crop object.
     * 
     * @param crop
     */
    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    /**
     * Returns the Tile's crop.
     * 
     * @return crop
     */
    public Crop getCrop() {
        return this.crop;
    }

    /**
     * Returns the Tile's plowed state.
     * 
     * @return isPlowed
     */
    public boolean getIsPlowed() {
        return this.isPlowed;
    }

    /**
     * Returns the Tile's watered state.
     * 
     * @return isWatered
     */
    public boolean getIsWatered() {
        return this.isWatered;
    }

    /**
     * Returns the Tile's rock state.
     * 
     * @return isRock
     */
    public boolean getIsRock() {
        return this.isRock;
    }

    /**
     * Returns the Tile's path state.
     * 
     * @return isPath
     */
    public boolean getIsPath() {
        return this.isPath;
    }

    /**
     * Returns the Tile's block state.
     * 
     * @return isBlock
     */
    public boolean getIsBlock() {
        return this.isBlock;
    }

    /**
     * Returns the Tile's x coordinate.
     * 
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the Tile's y coordinate.
     * 
     * @return y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the image of the Tile depending on its state.
     */
    public void setDefaultTiles() {
        this.defaultTiles = new HashMap<Character, BufferedImage>();
        try {
            this.defaultTiles.put('0', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/farmtile.png")));

            this.defaultTiles.put('1', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/rock.png")));

            this.defaultTiles.put('2', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/plowed.png")));

            this.defaultTiles.put('3', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/watered.png")));

            this.defaultTiles.put('L', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/left_edge.png")));

            this.defaultTiles.put('l',
                    ImageIO.read(getClass().getResourceAsStream("/resources/tiles/left_edge_in.png")));

            this.defaultTiles.put('R', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/right_edge.png")));

            this.defaultTiles.put('r',
                    ImageIO.read(getClass().getResourceAsStream("/resources/tiles/right_edge_in.png")));

            this.defaultTiles.put('d', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/down.png")));

            this.defaultTiles.put('u', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/up.png")));

            this.defaultTiles.put('c', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/LL.png")));

            this.defaultTiles.put('v', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/LR.png")));

            this.defaultTiles.put('z', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/UL.png")));

            this.defaultTiles.put('x', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/UR.png")));

            this.defaultTiles.put('b', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/LL_in.png")));

            this.defaultTiles.put('e', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/LR_in.png")));

            this.defaultTiles.put('m', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/UL_in.png")));

            this.defaultTiles.put('n', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/UR_in.png")));

            this.defaultTiles.put('p', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/down_in.png")));

            this.defaultTiles.put('q', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/up_in.png")));

            this.defaultTiles.put('s', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/left_in.png")));

            this.defaultTiles.put('t', ImageIO.read(getClass().getResourceAsStream("/resources/tiles/right_in.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // Override Renderable interface

    public BufferedImage getDefaultImage(char condition) {
        return this.defaultTiles.get(condition);
    }

    public BufferedImage getFrontImage(int num) {
        return null;
    }

    public BufferedImage getBackImage(int num) {
        return null;
    }

    public BufferedImage getLeftImage(int num) {
        return null;
    }

    public BufferedImage getRightImage(int num) {
        return null;
    }

    public String getDirection() {
        return null;
    }
}
