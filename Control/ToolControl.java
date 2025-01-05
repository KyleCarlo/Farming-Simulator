package Control;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

/**
 * 
 * This class handles the key inputs for the tools in the game. It implements
 * the KeyListener interface and overrides the keyPressed and keyReleased
 * methods.
 * 
 * The keys J, K, L, N, and M are used to determine which tool is currently
 * being used.
 * 
 * The plowPressed, waterPressed, fertPressed, pickaxePressed, and shovelPressed
 * boolean variables are used to store the state of each tool key.
 */
public class ToolControl implements KeyListener {
    public boolean plowPressed, waterPressed, fertPressed, pickaxePressed, shovelPressed;

    @Override // Override the KeyListener interface
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_J) {
            plowPressed = true;
        } else if (key == KeyEvent.VK_K) {
            waterPressed = true;
        } else if (key == KeyEvent.VK_L) {
            fertPressed = true;
        } else if (key == KeyEvent.VK_N) {
            pickaxePressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_J) {
            plowPressed = false;
        } else if (key == KeyEvent.VK_K) {
            waterPressed = false;
        } else if (key == KeyEvent.VK_L) {
            fertPressed = false;
        } else if (key == KeyEvent.VK_N) {
            pickaxePressed = false;
        }

        if (key == KeyEvent.VK_M) {
            shovelPressed = true;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

}
