package gui;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for moveable game objects
 */
public abstract class MoveableGameModel extends GameModel {

    private volatile double currentVelocity;
    private final double velocityConstant;

    public double getCurrentVelocity() {
        return currentVelocity;
    }

    public void setCurrentVelocity(double currentVelocity) {
        if (currentVelocity <= velocityConstant * 2 && currentVelocity >= velocityConstant / 2) {
            this.currentVelocity = currentVelocity;
        }
    }

    private final List<VelocityEffect> velocityEffects = new ArrayList<>();

    public double getVelocityConstant() {
        return velocityConstant;
    }

    public void applyEffect(VelocityEffect velocityEffect) {
        if (!velocityEffects.contains(velocityEffect)) {
            velocityEffects.add(velocityEffect);
        }
    }

    public void removeEffect(VelocityEffect velocityEffect) {
        velocityEffects.remove(velocityEffect);
    }

    public MoveableGameModel(double XCoordinate, double YCoordinate, double currentVelocity, double velocityConstant) {
        super(XCoordinate, YCoordinate);
        this.currentVelocity = currentVelocity;
        this.velocityConstant = velocityConstant;
    }

    /**
     * Describes how to calculate coordinates for object
     */
    public abstract void move();

    public void applyEffects() {
        setCurrentVelocity(velocityConstant);
        for (VelocityEffect velocityEffect : velocityEffects) {
            double multiplier = velocityEffect.getVelocityMultiplier();
            setCurrentVelocity(getCurrentVelocity() * multiplier);
        }
    }
}
