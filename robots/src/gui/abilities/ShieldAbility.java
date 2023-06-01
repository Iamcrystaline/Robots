package gui.abilities;

import gui.Target;

import java.util.TimerTask;

import static gui.Constants.ShieldAbilityConstants.SHIELD_ABILITY_COOLDOWN;
import static gui.Constants.ShieldAbilityConstants.SHIELD_ABILITY_DURATION;

public class ShieldAbility extends Ability {

    private final Target target;

    public ShieldAbility(Target target) {
        super(SHIELD_ABILITY_COOLDOWN, SHIELD_ABILITY_DURATION);
        this.target = target;
    }

    @Override
    public void use() {
        if (!isOnCooldown()) {
            target.setShielded(true);
            getTimer().schedule(new TimerTask() {
                @Override
                public void run() {
                    target.setShielded(false);
                }
            }, getDuration());
            setOnCooldown(true);
            Ability ability = this;
            getTimer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ability.setOnCooldown(false);
                }
            }, getCooldown());
        }
    }
}
