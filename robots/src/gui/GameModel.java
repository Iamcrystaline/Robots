package gui;

import java.awt.*;

/**
 * Model class for each object in the game.
 */
public abstract class GameModel {

    private double XCoordinate;
    private double YCoordinate;

    public GameModel(double XCoordinate, double YCoordinate) {
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
    }

    public double getXCoordinate() {
        return XCoordinate;
    }

    public void setXCoordinate(double XCoordinate) {
        this.XCoordinate = XCoordinate;
    }

    public double getYCoordinate() {
        return YCoordinate;
    }

    public void setYCoordinate(double YCoordinate) {
        this.YCoordinate = YCoordinate;
    }

    /**
     * Method for painting the object on the game field
     *
     * @param g - graphics object to paint
     */

    public abstract void draw(Graphics2D g);
}
