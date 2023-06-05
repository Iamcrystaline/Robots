package gui.abilities;

import gui.Robot;

import java.util.List;
import java.util.TimerTask;

import static gui.Constants.FreezeTimeAbilityConstants.FREEZE_TIME_ABILITY_COOLDOWN;
import static gui.Constants.FreezeTimeAbilityConstants.FREEZE_TIME_ABILITY_DURATION;

/**
 * Class for freeze time ability
 */
public class FreezeTimeAbility extends Ability {

    private final List<Robot> robots;

    public FreezeTimeAbility(List<Robot> robots) {
        super(FREEZE_TIME_ABILITY_COOLDOWN, FREEZE_TIME_ABILITY_DURATION);
        this.robots = robots;
    }

    /**
     * Method freeze all the robots for FREEZE_TIME_ABILITY_DURATION milliseconds
     */
    public void use() {
        if (!isOnCooldown()) {
            robots.forEach(robot -> robot.setFrozen(true));
            getTimer().schedule(new TimerTask() {
                @Override
                public void run() {
                    robots.forEach(robot -> robot.setFrozen(false));
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
