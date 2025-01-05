package Model;

import java.awt.image.BufferedImage;

/**
 * 
 * The {@code Renderable} interface is implemented by classes that are able to
 * be rendered to the screen in some way.
 * 
 * @author Assistant
 */
public interface Renderable {

    public BufferedImage getDefaultImage(char condition);

    public BufferedImage getFrontImage(int num);

    public BufferedImage getBackImage(int num);

    public BufferedImage getLeftImage(int num);

    public BufferedImage getRightImage(int num);

    public String getDirection();
}