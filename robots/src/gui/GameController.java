package gui;

import log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import static gui.Constants.HasteEffectConstants.HASTE_EFFECT_DIAMETER;
import static gui.Constants.MainApplicationFrameConstants.*;
import static gui.Constants.RobotConstants.*;
import static gui.Constants.SlowEffectConstants.SLOW_EFFECT_DIAMETER;
import static gui.Constants.TargetConstants.TARGET_DIAMETER;
import static gui.Constants.TimerConstants.*;

/**
 * Controller class of the MVC model. This class reacts to user's actions.
 */
public class GameController extends JPanel {

    private final Robot robot;
    private final Target target;
    private final HasteEffect hasteEffect;
    private final SlowEffect slowEffect;
    private final GameWindow gameWindow;
    private final Random rand = new Random();
    private final Timer timer;

    public GameController(Robot robot, Target target, GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.robot = robot;
        this.target = target;
        this.hasteEffect = new HasteEffect(rand.nextInt(INITIAL_GAME_WINDOW_WIDTH), rand.nextInt(INITIAL_GAME_WINDOW_HEIGHT));
        this.slowEffect = new SlowEffect(rand.nextInt(INITIAL_GAME_WINDOW_WIDTH), rand.nextInt(INITIAL_GAME_WINDOW_HEIGHT));

        robot.setMoving(false);
        timer = new Timer(TIMER_NAME, true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, TIMER_DELAY, TIMER_REDRAW_PERIOD);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onModelUpdateEvent();
            }
        }, TIMER_DELAY, TIMER_UPDATE_PERIOD);
        this.setFocusable(true);
        this.requestFocus();
        addKeyListener(new KeyEventListener(target));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                target.setXCoordinate(event.getPoint().x);
                target.setYCoordinate(event.getPoint().y);
                repaint();
            }
        });
        setDoubleBuffered(true);
    }

    private void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    private void onModelUpdateEvent() {
        double robotToTargetDistance = MathModule.calculateDistance(target.getXCoordinate(), target.getYCoordinate(), robot.getXCoordinate(), robot.getYCoordinate());
        double targetToHastEffectDistance = MathModule.calculateDistance(target.getXCoordinate(), target.getYCoordinate(), hasteEffect.getXCoordinate(), hasteEffect.getYCoordinate());
        double targetToSlowEffectDistance = MathModule.calculateDistance(target.getXCoordinate(), target.getYCoordinate(), slowEffect.getXCoordinate(), slowEffect.getYCoordinate());
        double robotToHastEffectDistance = MathModule.calculateDistance(robot.getXCoordinate(), robot.getYCoordinate(), hasteEffect.getXCoordinate(), hasteEffect.getYCoordinate());
        double robotToSlowEffectDistance = MathModule.calculateDistance(robot.getXCoordinate(), robot.getYCoordinate(), slowEffect.getXCoordinate(), slowEffect.getYCoordinate());
        target.move();

        checkAndApplyEffect(target, TARGET_DIAMETER, hasteEffect, HASTE_EFFECT_DIAMETER, targetToHastEffectDistance);
        checkAndApplyEffect(target, TARGET_DIAMETER, slowEffect, SLOW_EFFECT_DIAMETER, targetToSlowEffectDistance);
        checkAndApplyEffect(robot, ROBOT_BODY_SECOND_DIAMETER, hasteEffect, HASTE_EFFECT_DIAMETER, robotToHastEffectDistance);
        checkAndApplyEffect(robot, ROBOT_BODY_SECOND_DIAMETER, slowEffect, SLOW_EFFECT_DIAMETER, robotToSlowEffectDistance);

        if (robotToTargetDistance < ROBOT_STOP_DISTANCE) {
            logRobotState(true);
            return;
        }
        logRobotState(false);
        double angleToTarget = MathModule.calculateAngle(robot.getXCoordinate(), robot.getYCoordinate(), target.getXCoordinate(), target.getYCoordinate());
        if (angleToTarget == robot.getDirection()) {
            robot.setAngularVelocity(0);
        } else if (angleToTarget <= Math.PI) {
            robot.setAngularVelocity(angleToTarget > robot.getDirection() || robot.getDirection() > angleToTarget + Math.PI ? ROBOT_ANGULAR_VELOCITY : -ROBOT_ANGULAR_VELOCITY);
        } else {
            robot.setAngularVelocity(angleToTarget > robot.getDirection() && robot.getDirection() > angleToTarget - Math.PI ? ROBOT_ANGULAR_VELOCITY : -ROBOT_ANGULAR_VELOCITY);
        }
        robot.move();
    }

    private void checkAndApplyEffect(MoveableGameModel modelToCheck, double modelToCheckDiameter, VelocityEffect effectToCheck, double effectToCheckDiameter, double distance) {
        if (distance < modelToCheckDiameter + effectToCheckDiameter) {
            modelToCheck.applyEffect(effectToCheck);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    modelToCheck.removeEffect(effectToCheck);
                }
            }, 30000L);
            Dimension windowSize = gameWindow.getContentPane().getSize();
            effectToCheck.setXCoordinate(rand.nextInt(MathModule.round(windowSize.getWidth())));
            effectToCheck.setYCoordinate(rand.nextInt(MathModule.round(windowSize.getHeight())));
        }
    }

    /**
     * Method to log changes in robot's movement
     *
     * @param isStopped - true - if robot was stopped
     *                  false - if robot start's moving
     */
    private void logRobotState(boolean isStopped) {
        if (robot.isMoving() == isStopped) {
            robot.setMoving(!robot.isMoving());
            Logger.debug(robot.isMoving() ? ROBOT_START_MOVING : ROBOT_STOP_MOVING);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        robot.draw(g2d);
        target.draw(g2d);
        hasteEffect.draw(g2d);
        slowEffect.draw(g2d);
    }
}
