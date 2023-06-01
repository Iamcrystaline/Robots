package gui;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import static gui.Constants.SlowEffectConstants.*;

/**
 * The class responsible for the deceleration effect
 */
public class SlowEffect extends VelocityEffect {
    public SlowEffect(double XCoordinate, double YCoordinate) {
        super(XCoordinate, YCoordinate, SLOW_EFFECT_DIAMETER, SLOW_EFFECT_DURATION, SLOW_EFFECT_MULTIPLIER);
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform t = AffineTransform.getRotateInstance(SLOW_EFFECT_THETA, SLOW_EFFECT_ANCHORX, SLOW_EFFECT_ANCHORY);
        g.setTransform(t);
        g.setColor(Color.RED);
        int hastEffectCenterX = MathModule.round(getXCoordinate());
        int hasteEffectCenterY = MathModule.round(getYCoordinate());
        GameVisualizer.fillOval(g, hastEffectCenterX, hasteEffectCenterY, SLOW_EFFECT_DIAMETER, SLOW_EFFECT_DIAMETER);
        g.setColor(Color.BLACK);
        GameVisualizer.drawOval(g, hastEffectCenterX, hasteEffectCenterY, SLOW_EFFECT_DIAMETER, SLOW_EFFECT_DIAMETER);
    }

    @Override
    public VelocityEffect getNewInstance() {
        return new SlowEffect(0, 0);
    }
}
