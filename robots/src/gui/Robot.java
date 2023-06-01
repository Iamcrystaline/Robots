package gui;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static gui.Constants.RobotConstants.*;
import static gui.Constants.TimerConstants.TIMER_UPDATE_PERIOD;


public class Robot extends MoveableGameModel {
    private volatile double direction;
    private double angularVelocityConstant;
    private double angularVelocity;
    private boolean isFrozen;

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public double getAngularVelocityConstant() {
        return angularVelocityConstant;
    }

    public void setAngularVelocityConstant(double angularVelocityConstant) {
        this.angularVelocityConstant = angularVelocityConstant;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getDirection() {
        return direction;
    }

    public Robot(double xCoordinate, double yCoordinate, double currentVelocity, double direction, double angularVelocityConstant) {
        super(xCoordinate, yCoordinate, currentVelocity, ROBOT_DEFAULT_VELOCITY, ROBOT_BODY_SECOND_DIAMETER);
        this.angularVelocityConstant = angularVelocityConstant;
        this.direction = direction;
        this.isFrozen = false;
    }

    @Override
    public void draw(Graphics2D g) {
        int robotCenterX = MathModule.round(getXCoordinate());
        int robotCenterY = MathModule.round(getYCoordinate());
        AffineTransform t = AffineTransform.getRotateInstance(getDirection(), robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        GameVisualizer.fillOval(g, robotCenterX, robotCenterY, ROBOT_BODY_FIRST_DIAMETER, ROBOT_BODY_SECOND_DIAMETER);
        g.setColor(Color.BLACK);
        GameVisualizer.drawOval(g, robotCenterX, robotCenterY, ROBOT_BODY_FIRST_DIAMETER, ROBOT_BODY_SECOND_DIAMETER);
        g.setColor(Color.WHITE);
        GameVisualizer.fillOval(g, robotCenterX + ROBOT_HEAD_X_OFFSET, robotCenterY, ROBOT_HEAD_DIAMETER, ROBOT_HEAD_DIAMETER);
        g.setColor(Color.BLACK);
        GameVisualizer.drawOval(g, robotCenterX + ROBOT_HEAD_X_OFFSET, robotCenterY, ROBOT_HEAD_DIAMETER, ROBOT_HEAD_DIAMETER);
    }

    @Override
    public void move() {
        if (!isFrozen) {
            applyEffects();
            double newRobotDirection = getDirection() + getAngularVelocity() * TIMER_UPDATE_PERIOD;
            double newRobotXCoordinate = getXCoordinate() + getCurrentVelocity() * TIMER_UPDATE_PERIOD * Math.cos(newRobotDirection);
            double newRobotYCoordinate = getYCoordinate() + getCurrentVelocity() * TIMER_UPDATE_PERIOD * Math.sin(newRobotDirection);
            setXCoordinate(newRobotXCoordinate);
            setYCoordinate(newRobotYCoordinate);
            setDirection(MathModule.asNormalizedRadians(newRobotDirection));
        }
    }

}
