package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Listener class for keyboard typing/pressing/releasing.
 */
public class KeyEventListener extends KeyAdapter {

    private final Target target;

    public KeyEventListener(Target target) {
        this.target = target;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyPressed(e);
    }

    /**
     * Depends on the user's action, changes the target's velocity.
     *
     * @param e - pressed key
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_DOWN: {
                target.setVerticalMoveMode(1);
                break;
            }
            case KeyEvent.VK_UP: {
                target.setVerticalMoveMode(-1);
                break;
            }
            case KeyEvent.VK_RIGHT: {
                target.setHorizontalMoveMode(1);
                break;
            }
            case KeyEvent.VK_LEFT: {
                target.setHorizontalMoveMode(-1);
                break;
            }
        }
    }

    /**
     * Resets the target's velocity
     *
     * @param e - released key
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_UP) {
            target.setVerticalMoveMode(0);
        }
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            target.setHorizontalMoveMode(0);
        }
    }
}
