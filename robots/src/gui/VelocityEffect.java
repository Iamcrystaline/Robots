package gui;

public abstract class VelocityEffect extends GameModel {


    public VelocityEffect(double XCoordinate, double YCoordinate) {
        super(XCoordinate, YCoordinate);
    }

    public abstract double getVelocityMultiplier();
}
