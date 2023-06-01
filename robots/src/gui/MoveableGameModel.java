package gui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Interface for moveable game objects
 */
public abstract class MoveableGameModel extends GameModel {

    private volatile double currentVelocity;
    private final double velocityConstant;
    private final Map<VelocityEffect, VelocityEffect> velocityEffects = new HashMap<>();

    public double getCurrentVelocity() {
        return currentVelocity;
    }

    public void setCurrentVelocity(double currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    public Map<VelocityEffect, VelocityEffect> getVelocityEffects() {
        return velocityEffects;
    }

    public double getVelocityConstant() {
        return velocityConstant;
    }

    public void applyEffect(VelocityEffect velocityEffect) {
        velocityEffects.put(velocityEffect, velocityEffect);
    }

    public void removeEffect(VelocityEffect velocityEffect) {
        velocityEffects.remove(velocityEffect);
    }

    public MoveableGameModel(double XCoordinate, double YCoordinate, double currentVelocity, double velocityConstant, double hitBoxRadius) {
        super(XCoordinate, YCoordinate, hitBoxRadius);
        this.currentVelocity = currentVelocity;
        this.velocityConstant = velocityConstant;
    }

    /**
     * Describes how to calculate coordinates for object
     */
    public abstract void move();

    /**
     * Method that adds a list of effects to the object
     */
    public void applyEffects() {
        setCurrentVelocity(velocityConstant);
        for (VelocityEffect velocityEffect : velocityEffects.values()) {
            double multiplier = velocityEffect.getVelocityMultiplier();
            setCurrentVelocity(getCurrentVelocity() * multiplier);
        }
    }
}
