package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static gui.Constants.KeyEventListenerConstants.TARGET_VELOCITY;

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
                target.setVerticalVelocity(TARGET_VELOCITY);
                break;
            }
            case KeyEvent.VK_UP: {
                target.setVerticalVelocity(-TARGET_VELOCITY);
                break;
            }
            case KeyEvent.VK_RIGHT: {
                target.setHorizontalVelocity(TARGET_VELOCITY);
                break;
            }
            case KeyEvent.VK_LEFT: {
                target.setHorizontalVelocity(-TARGET_VELOCITY);
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
        target.setHorizontalVelocity(0);
        target.setVerticalVelocity(0);
    }
}
