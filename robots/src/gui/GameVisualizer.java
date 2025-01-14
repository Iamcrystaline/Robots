package gui;

import java.awt.Graphics;

/**
 * View class in MVC model. This class paints the components of the game.
 */
public class GameVisualizer {

    public static void fillOval(Graphics g, int centerXCoordinate, int centerYCoordinate, int firstDiameter, int secondDiameter) {
        g.fillOval(centerXCoordinate - firstDiameter / 2, centerYCoordinate - secondDiameter / 2, firstDiameter, secondDiameter);
    }

    public static void drawOval(Graphics g, int centerXCoordinate, int centerYCoordinate, int firstDiameter, int secondDiameter) {
        g.drawOval(centerXCoordinate - firstDiameter / 2, centerYCoordinate - secondDiameter / 2, firstDiameter, secondDiameter);
    }
}
