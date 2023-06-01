package gui;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static gui.Constants.VelocityEffectConstants.EFFECT_TIMER_NAME;

/**
 * Interface for accelerating and decelerating effects
 */
public abstract class VelocityEffect extends GameModel {

    private MoveableGameModel appliedModel;
    private final Timer timer;
    private final long duration;
    private final double velocityMultiplier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VelocityEffect effect = (VelocityEffect) o;
        return Double.compare(effect.velocityMultiplier, velocityMultiplier) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(velocityMultiplier);
    }

    public VelocityEffect(double XCoordinate, double YCoordinate, double hitBoxRadius, long duration, double velocityMultiplier) {
        super(XCoordinate, YCoordinate, hitBoxRadius);
        this.timer = new Timer(EFFECT_TIMER_NAME, true);
        this.duration = duration;
        this.velocityMultiplier = velocityMultiplier;
    }

    public void apply(MoveableGameModel model) {
        this.appliedModel = model;
        VelocityEffect velocityEffect = this;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                appliedModel.removeEffect(velocityEffect);
            }
        }, duration);
    }

    public Timer getTimer() {
        return timer;
    }

    public double getVelocityMultiplier() {
        return velocityMultiplier;
    }

    public abstract VelocityEffect getNewInstance();
}
