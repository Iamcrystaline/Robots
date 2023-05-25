package gui;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static gui.Constants.SlowEffectConstants.*;

public class SlowEffect extends VelocityEffect {
    public SlowEffect(double XCoordinate, double YCoordinate) {
        super(XCoordinate, YCoordinate);
    }

    @Override
    public double getVelocityMultiplier() {
        return SLOW_EFFECT_MULTIPLIER;
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
}
