package Model;

import java.awt.image.BufferedImage;

import java.awt.Rectangle;

/**
 * This class represents an Entity in the game. An entity is a character,
 * object, or item that exists in the game world.
 * This class provides basic properties and methods that all entities will have,
 * such as the x and y coordinates of the entity, its speed, and its direction.
 * Subclasses of this class can override and add additional behavior and
 * properties that are specific to the type of entity they represent.
 */

public abstract class Entity {
    /**
     * The x and y coordinates of the entity.
     */
    protected int x, y;

    /**
     * The speed of the entity.
     */
    protected int speed;

    /**
     * The images of the entity.
     */
    protected BufferedImage front1, front2, back1, back2, left1, left2, right1, right2;

    /**
     * The direction the entity is facing.
     */
    protected String direction;

    /**
     * The counter for walking animation.
     */
    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    /**
     * The hitbox of the entity.
     * This is used for collision detection.
     */
    protected Rectangle hitbox;
    protected boolean collision = false;

    /**
     * Returns the direction of the entity.
     * 
     * @return the direction of the entity
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Returns the sprite counter of the entity.
     * 
     * @return the sprite counter of the entity
     */
    public int getSpriteNum() {
        return spriteNum;
    }
}
