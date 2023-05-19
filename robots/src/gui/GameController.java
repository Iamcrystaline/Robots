package gui;

import log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import static gui.Constants.GameVisualizerConstants.*;
import static gui.Constants.MainApplicationFrameConstants.ROBOT_START_MOVING;
import static gui.Constants.MainApplicationFrameConstants.ROBOT_STOP_MOVING;

/**
 * Controller class of the MVC model. This class reacts to user's actions.
 */
public class GameController extends JPanel {

    private final Robot robot;
    private final Target target;

    public GameController(Robot robot, Target target) {
        this.robot = robot;
        this.target = target;
        robot.setMoving(false);
        java.util.Timer timer = new Timer(TIMER_NAME, true);
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
        double distance = MathModule.calculateDistance(target.getXCoordinate(), target.getYCoordinate(), robot.getXCoordinate(), robot.getYCoordinate());
        target.move();

        if (distance < ROBOT_STOP_DISTANCE) {
            logRobotState(true);
            return;
        }
        logRobotState(false);
        double angleToTarget = MathModule.calculateAngle(robot.getXCoordinate(), robot.getYCoordinate(), target.getXCoordinate(), target.getYCoordinate());
        if (angleToTarget == robot.getDirection()) {
            robot.setAngularVelocity(0);
        } else if (angleToTarget <= Math.PI) {
            robot.setAngularVelocity(angleToTarget > robot.getDirection() || robot.getDirection() > angleToTarget + Math.PI ? MAX_ANGULAR_VELOCITY : -MAX_ANGULAR_VELOCITY);
        } else {
            robot.setAngularVelocity(angleToTarget > robot.getDirection() && robot.getDirection() > angleToTarget - Math.PI ? MAX_ANGULAR_VELOCITY : -MAX_ANGULAR_VELOCITY);
        }
        robot.move();
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
    }
}
