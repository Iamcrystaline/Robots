package gui.abilities;

import gui.Robot;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static gui.Constants.FreezeTimeAbilityConstants.FREEZE_TIME_ABILITY_COOLDOWN;
import static gui.Constants.FreezeTimeAbilityConstants.FREEZE_TIME_ABILITY_DURATION;
import static gui.Constants.TimerConstants.FREEZE_TIME_TIMER_NAME;

public class FreezeTimeAbility extends Ability {

    private final List<Robot> robots;
    private final Timer timer;

    public FreezeTimeAbility(List<Robot> robots) {
        super(FREEZE_TIME_ABILITY_COOLDOWN, FREEZE_TIME_ABILITY_DURATION);
        this.robots = robots;
        this.timer = new Timer(FREEZE_TIME_TIMER_NAME);
    }

    public void use() {
        if (!isOnCooldown()) {
            robots.forEach(robot -> robot.setFrozen(true));
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    robots.forEach(robot -> robot.setFrozen(false));
                }
            }, getDuration());
            setOnCooldown(true);
            FreezeTimeAbility ability = this;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ability.setOnCooldown(false);
                }
            }, getCooldown());
        }
    }
}
