package gui.abilities;

public abstract class Ability {

    private final long cooldown;
    private final long duration;
    private boolean isOnCooldown;

    public Ability(long cooldown, long duration) {
        this.cooldown = cooldown;
        this.duration = duration;
        this.isOnCooldown = false;
    }

    public boolean isOnCooldown() {
        return isOnCooldown;
    }

    public void setOnCooldown(boolean onCooldown) {
        isOnCooldown = onCooldown;
    }

    public long getCooldown() {
        return cooldown;
    }

    public long getDuration() {
        return duration;
    }

    public abstract void use();
}
