package gui;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static gui.Constants.HasteEffectConstants.*;

/**
 * The class responsible for the accelerated effect
 */

public class HasteEffect extends VelocityEffect {

    public HasteEffect(double XCoordinate, double YCoordinate) {
        super(XCoordinate, YCoordinate);
    }

    @Override
    public double getVelocityMultiplier() {
        return HASTE_EFFECT_MULTIPLIER;
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform t = AffineTransform.getRotateInstance(HASTE_EFFECT_THETA, HASTE_EFFECT_ANCHORX, HASTE_EFFECT_ANCHORY);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        int hastEffectCenterX = MathModule.round(getXCoordinate());
        int hasteEffectCenterY = MathModule.round(getYCoordinate());
        GameVisualizer.fillOval(g, hastEffectCenterX, hasteEffectCenterY, HASTE_EFFECT_DIAMETER, HASTE_EFFECT_DIAMETER);
        g.setColor(Color.BLACK);
        GameVisualizer.drawOval(g, hastEffectCenterX, hasteEffectCenterY, HASTE_EFFECT_DIAMETER, HASTE_EFFECT_DIAMETER);
    }
}
