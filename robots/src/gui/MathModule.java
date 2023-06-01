package gui;

/**
 * Class for needed math operations.
 */
public class MathModule {

    /**
     * round the number to the closest integer
     * @param number - number to round
     * @return rounded number
     */
    public static int round(double number) {
        return (int) (number + 0.5);
    }

    /**
     * Calculates the distance between 2 points
     * @param firstPointXCoordinate - X coordinate of first point
     * @param firstPointYCoordinate - Y coordinate of first point
     * @param secondPointXCoordinate - X coordinate of second point
     * @param secondPointYCoordinate - Y coordinate of second point
     * @return distance between point 1 and point 2
     */
    public static double calculateDistance(double firstPointXCoordinate, double firstPointYCoordinate, double secondPointXCoordinate, double secondPointYCoordinate) {
        double xCoordinateDifference = firstPointXCoordinate - secondPointXCoordinate;
        double yCoordinateDifference = firstPointYCoordinate - secondPointYCoordinate;
        return Math.sqrt(xCoordinateDifference * xCoordinateDifference + yCoordinateDifference * yCoordinateDifference);
    }

    /**
     * Calculates angle in radians from the beginning of the coordinates to the line, linking two points
     * @param startXCoordinate - X coordinate of the first point
     * @param startYCoordinate - Y coordinate of the fist point
     * @param endXCoordinate - X coordinate of the second point
     * @param endYCoordinate - Y coordinate of the second point
     * @return angle in radians from 0 to 2*PI
     */
    public static double calculateAngle(double startXCoordinate, double startYCoordinate, double endXCoordinate, double endYCoordinate) {
        double xCoordinateDifference = endXCoordinate - startXCoordinate;
        double yCoordinateDifference = endYCoordinate - startYCoordinate;
        return asNormalizedRadians(Math.atan2(yCoordinateDifference, xCoordinateDifference));
    }

    /**
     * Converts angle to the normalized radians
     * @param angle - angle to convert
     * @return normalized angle
     */
    public static double asNormalizedRadians(double angle) {
        while (angle < 0) {
            angle += 2 * Math.PI;
        }
        while (angle >= 2 * Math.PI) {
            angle -= 2 * Math.PI;
        }
        return angle;
    }
}
