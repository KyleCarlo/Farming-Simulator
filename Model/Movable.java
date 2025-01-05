package Model;

/**
 * 
 * This is the Movable interface. It defines the behavior for objects that can
 * move in a two-dimensional space.
 * The interface defines four methods for moving the object up, down, left, and
 * right. It also includes methods for getting the object's current x and y
 * coordinates.
 */
public interface Movable {

    public void moveUp();

    public void moveDown();

    public void moveLeft();

    public void moveRight();

    public int getX();

    public int getY();

    public void render();
}
