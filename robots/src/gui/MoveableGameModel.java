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
    // Used map instead of Set here cause Set doesn't have get() and add with replacing methods
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
     * Apply effects to the current velocity
     */
    public void applyEffects() {
        setCurrentVelocity(velocityConstant);
        for (VelocityEffect velocityEffect : velocityEffects.values()) {
            double multiplier = velocityEffect.getVelocityMultiplier();
            setCurrentVelocity(getCurrentVelocity() * multiplier);
        }
    }
}
