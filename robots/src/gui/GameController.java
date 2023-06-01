package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;

import static gui.Constants.MainApplicationFrameConstants.*;
import static gui.Constants.RobotConstants.*;
import static gui.Constants.TimerConstants.*;

/**
 * Controller class of the MVC model. This class reacts to user's actions.
 */
public class GameController extends JPanel {

    private final List<Robot> robots;
    private final List<MoveableGameModel> gameModels;
    private final Map<VelocityEffect, VelocityEffect> gameEffects;
    private final Target target;
    private final GameWindow gameWindow;
    private final Random rand = new Random();

    public GameController(Robot robot, Target target, GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.robots = new ArrayList<>();
        this.robots.add(robot);
        this.target = target;
        this.gameModels = new ArrayList<>();
        this.gameModels.add(robot);
        this.gameModels.add(target);
        HasteEffect hasteEffect = new HasteEffect(rand.nextInt(INTERNAL_GAME_WINDOW_WIDTH), rand.nextInt(INTERNAL_GAME_WINDOW_HEIGHT));
        SlowEffect slowEffect = new SlowEffect(rand.nextInt(INITIAL_GAME_WINDOW_WIDTH), rand.nextInt(INITIAL_GAME_WINDOW_HEIGHT));
        this.gameEffects = new HashMap<>();
        this.gameEffects.put(hasteEffect, hasteEffect);
        this.gameEffects.put(slowEffect, slowEffect);

        robot.setMoving(false);
        Timer timer = new Timer(TIMER_NAME, true);
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
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                addNewRobot();
            }
        }, ROBOT_SPAWN_PERIOD, ROBOT_SPAWN_PERIOD);
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

    private void addNewRobot() {
        Dimension windowSize = gameWindow.getContentPane().getSize();
        int randomXCoordinate = rand.nextInt(MathModule.round(windowSize.getWidth()));
        int randomYCoordinate = rand.nextInt(MathModule.round(windowSize.getHeight()));
        while (MathModule.calculateDistance(randomXCoordinate, randomYCoordinate, target.getXCoordinate(), target.getYCoordinate()) < MIN_ROBOT_SPAWN_DISTANCE) {
            randomXCoordinate = rand.nextInt(MathModule.round(windowSize.getWidth()));
            randomYCoordinate = rand.nextInt(MathModule.round(windowSize.getHeight()));
        }
        Robot newRobot = new Robot(
                randomXCoordinate,
                randomYCoordinate,
                ROBOT_DEFAULT_VELOCITY,
                ROBOT_INITIAL_DIRECTION
        );
        this.robots.add(newRobot);
        this.gameModels.add(newRobot);
    }

    private void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    private void onModelUpdateEvent() {
        checkAndApplyEffects();
        target.move();

        for (Robot robot : robots) {
            if (MathModule.calculateDistance(robot.getXCoordinate(), robot.getYCoordinate(), target.getXCoordinate(), target.getYCoordinate()) < ROBOT_STOP_DISTANCE) {
                return;
            }
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
    }

    private void checkAndApplyEffects() {
        Dimension windowSize = gameWindow.getContentPane().getSize();
        for (MoveableGameModel model : gameModels) {
            for (VelocityEffect effect : gameEffects.values()) {
                double distance = MathModule.calculateDistance(model.getXCoordinate(), model.getYCoordinate(), effect.getXCoordinate(), effect.getYCoordinate());
                if (distance < model.getHitBoxRadius() + effect.getHitBoxRadius()) {
                    VelocityEffect previousEffect = model.getVelocityEffects().get(effect);
                    if (previousEffect != null) {
                        previousEffect.getTimer().cancel();
                        previousEffect.getTimer().purge();
                    }
                    model.getVelocityEffects().put(effect, effect);
                    effect.apply(model);
                    VelocityEffect newEffect = effect.getNewInstance();
                    newEffect.setXCoordinate(rand.nextInt(MathModule.round(windowSize.getWidth())));
                    newEffect.setYCoordinate(rand.nextInt(MathModule.round(windowSize.getHeight())));
                    gameEffects.put(newEffect, newEffect);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Robot robot : robots) {
            robot.draw(g2d);
        }
        target.draw(g2d);
        for (VelocityEffect effect : gameEffects.values()) {
            effect.draw(g2d);
        }
    }
}
