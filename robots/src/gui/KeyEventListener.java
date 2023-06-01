package gui;

import gui.abilities.FreezeTimeAbility;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Listener class for keyboard typing/pressing/releasing.
 */
public class KeyEventListener extends KeyAdapter {

    private final Target target;
    private final FreezeTimeAbility freezeTimeAbility;

    public KeyEventListener(Target target, FreezeTimeAbility freezeTimeAbility) {
        this.target = target;
        this.freezeTimeAbility = freezeTimeAbility;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyPressed(e);
    }

    /**
     * Depends on the user's action do something.
     *
     * @param e - pressed key
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_1: {
                freezeTimeAbility.use();
                break;
            }
            case KeyEvent.VK_DOWN: {
                target.setVerticalMoveMode(VerticalMoveModes.DOWN);
                break;
            }
            case KeyEvent.VK_UP: {
                target.setVerticalMoveMode(VerticalMoveModes.UP);
                break;
            }
            case KeyEvent.VK_RIGHT: {
                target.setHorizontalMoveMode(HorizontalMoveModes.RIGHT);
                break;
            }
            case KeyEvent.VK_LEFT: {
                target.setHorizontalMoveMode(HorizontalMoveModes.LEFT);
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
            target.setVerticalMoveMode(VerticalMoveModes.STOP);
        }
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            target.setHorizontalMoveMode(HorizontalMoveModes.STOP);
        }
    }
}
