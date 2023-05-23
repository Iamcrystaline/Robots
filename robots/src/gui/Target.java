package gui;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static gui.Constants.GameVisualizerConstants.*;
import static gui.Constants.TargetConstants.*;

public class Target extends MoveableGameModel {

    private int horizontalMoveMode;
    private int verticalMoveMode;

    public void setHorizontalMoveMode(int horizontalMoveMode) {
        this.horizontalMoveMode = horizontalMoveMode;
    }

    public void setVerticalMoveMode(int verticalMoveMode) {
        this.verticalMoveMode = verticalMoveMode;
    }

    public Target(double xCoordinate, double yCoordinate, double currentVelocity) {
        super(xCoordinate, yCoordinate, currentVelocity, TARGET_DEFAULT_VELOCITY);
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform t = AffineTransform.getRotateInstance(TARGET_THETA, TARGET_ANCHORX, TARGET_ANCHORY);
        g.setTransform(t);
        g.setColor(Color.YELLOW);
        int targetCenterX = MathModule.round(getXCoordinate());
        int targetCenterY = MathModule.round(getYCoordinate());
        GameVisualizer.fillOval(g, targetCenterX, targetCenterY, TARGET_DIAMETER, TARGET_DIAMETER);
        g.setColor(Color.BLACK);
        GameVisualizer.drawOval(g, targetCenterX, targetCenterY, TARGET_DIAMETER, TARGET_DIAMETER);
    }

    @Override
    public void move() {
        applyEffects();
        double newTargetXCoordinate = getXCoordinate() + getCurrentVelocity() * TIMER_UPDATE_PERIOD * horizontalMoveMode;
        setXCoordinate(newTargetXCoordinate);
        double newTargetYCoordinate = getYCoordinate() + getCurrentVelocity() * TIMER_UPDATE_PERIOD * verticalMoveMode;
        setYCoordinate(newTargetYCoordinate);
    }
}
