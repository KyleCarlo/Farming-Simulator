package Control;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

/**
 * The KeyControl class is responsible for handling user input from keyboard
 * events.
 * It implements the KeyListener interface and contains booleans for each key
 * that can be pressed.
 * These booleans are updated when a key press or release event occurs, and can
 * be accessed by other classes.
 */
public class KeyControl implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean plantPressed, pressed1, pressed2, pressed3, pressed4, pressed5, pressed6, pressed7, pressed8;
    public boolean enterPressed, hPressed, uPressed;

    @Override // Override the KeyListener interface
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // movement keys
        if (key == KeyEvent.VK_W) {
            upPressed = true;
        } else if (key == KeyEvent.VK_S) {
            downPressed = true;
        } else if (key == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (key == KeyEvent.VK_D) {
            rightPressed = true;
        }

        // press 1-9 to change seed
        if (key == KeyEvent.VK_0) {
            plantPressed = true;
        } else if (key == KeyEvent.VK_1) {
            pressed1 = true;
        } else if (key == KeyEvent.VK_2) {
            pressed2 = true;
        } else if (key == KeyEvent.VK_3) {
            pressed3 = true;
        } else if (key == KeyEvent.VK_4) {
            pressed4 = true;
        } else if (key == KeyEvent.VK_5) {
            pressed5 = true;
        } else if (key == KeyEvent.VK_6) {
            pressed6 = true;
        } else if (key == KeyEvent.VK_7) {
            pressed7 = true;
        } else if (key == KeyEvent.VK_8) {
            pressed8 = true;
        }

        // press enter to plant seed
        if (key == KeyEvent.VK_H) {
            hPressed = true;
        }

        // press enter to plant seed
        if (key == KeyEvent.VK_U) {
            uPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            upPressed = false;
        } else if (key == KeyEvent.VK_S) {
            downPressed = false;
        } else if (key == KeyEvent.VK_A) {
            leftPressed = false;
        } else if (key == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (key == KeyEvent.VK_0) {
            plantPressed = false;
        } else if (key == KeyEvent.VK_1) {
            pressed1 = false;
        } else if (key == KeyEvent.VK_2) {
            pressed2 = false;
        } else if (key == KeyEvent.VK_3) {
            pressed3 = false;
        } else if (key == KeyEvent.VK_4) {
            pressed4 = false;
        } else if (key == KeyEvent.VK_5) {
            pressed5 = false;
        } else if (key == KeyEvent.VK_6) {
            pressed6 = false;
        } else if (key == KeyEvent.VK_7) {
            pressed7 = false;
        } else if (key == KeyEvent.VK_8) {
            pressed8 = false;
        }

        if (key == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (key == KeyEvent.VK_H) {
            hPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

}
