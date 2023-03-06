package FlaNium.WinAPI.actions;

import FlaNium.WinAPI.DesktopElement;
import FlaNium.WinAPI.webdriver.FlaNiumDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;

import java.util.Arrays;
import java.util.HashMap;

public class TouchActions {

    private FlaNiumDriver driver;
    private int x;
    private int y;

    public TouchActions(FlaNiumDriver driver) {
        this.driver = driver;
        this.x = 0;
        this.y = 0;
    }

    //todo Проверить
    public TouchActions(DesktopElement desktopElement) {
        this.driver = (FlaNiumDriver) desktopElement.getWrappedDriver();
        Rectangle rectangle = desktopElement.getElementRect();
        this.x = rectangle.getX();
        this.y = rectangle.getY();
    }


    /**
     * Performs a tap on the given point or points.
     *
     * @param points
     */
    public void tap(Point[] points) {
        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put("points", offsetPoints(points));

        driver.execute("touchActionsTap", parameters);
    }

    /**
     * Holds the touch on the given points for the given duration.
     *
     * @param duration The duration of the hold (in milliseconds).
     * @param points   The points that should be hold down.
     */
    public void hold(Integer duration, Point[] points) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("points", offsetPoints(points));
        parameters.put("duration", duration);

        driver.execute("touchActionsHold", parameters);
    }

    /**
     * Performs a pinch with two fingers.
     *
     * @param center      The center point of the pinch.
     * @param startRadius The starting radius.
     * @param endRadius   The end radius.
     * @param duration    The duration of the action (in milliseconds).
     * @param angle       The angle of the two points, relative to the x-axis.
     */
    public void pinch(Point center, double startRadius, double endRadius, Integer duration, double angle) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("center", offsetPoint(center));
        parameters.put("startRadius", startRadius);
        parameters.put("endRadius", endRadius);
        parameters.put("duration", duration);
        parameters.put("angle", angle);

        driver.execute("touchActionsPinch", parameters);
    }

    /**
     * Transitions all the points from the start point to the end points.
     *
     * @param duration       The duration for the action (in milliseconds).
     * @param startEndPoints The list of start/end point tuples.
     */
    public void transition(Integer duration, StartEndPoint[] startEndPoints) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("duration", duration);
        parameters.put("startEndPoints", offsetStartAndPoints(startEndPoints));

        driver.execute("touchActionsTransition", parameters);
    }

    /**
     * Performs a touch-drag from the start point to the end point.
     *
     * @param duration   The duration of the action (in milliseconds).
     * @param startEndPoints The list of start/end point tuples.
     * @param durationHold   The duration of the hold on start points (in milliseconds).
     */
    public void drag(Integer duration, StartEndPoint[] startEndPoints, Integer durationHold) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("duration", duration);
        parameters.put("startEndPoints", offsetStartAndPoints(startEndPoints));
        parameters.put("durationHold", durationHold);

        driver.execute("touchActionsDrag", parameters);
    }

    /**
     * Performs a 2-finger rotation around the given point where the first finger is at the center and
     * the second is rotated around.
     *
     * @param center     The center point of the rotation.
     * @param radius     The radius of the rotation.
     * @param startAngle The starting angle (in rad).
     * @param endAngle   The ending angle (in rad).
     * @param duration   The total duration for the transition (in milliseconds).
     */
    public void rotate(Point center, double radius, double startAngle, double endAngle, Integer duration) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("center", offsetPoint(center));
        parameters.put("radius", radius);
        parameters.put("startAngle", startAngle);
        parameters.put("endAngle", endAngle);
        parameters.put("duration", duration);

        driver.execute("touchActionsRotate", parameters);
    }


    public static class StartEndPoint {
        private Integer x1;
        private Integer y1;
        private Integer x2;
        private Integer y2;

        private Point startPoint;
        private Point endPoint;

        public StartEndPoint(Point startPoint, Point endPoint) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;

            this.x1 = startPoint.getX();
            this.y1 = startPoint.getY();
            this.x2 = endPoint.getX();
            this.y2 = endPoint.getY();
        }

        public Integer getX1() {
            return x1;
        }

        public Integer getY1() {
            return y1;
        }

        public Integer getX2() {
            return x2;
        }

        public Integer getY2() {
            return y2;
        }

        public Point getStartPoint() {
            return startPoint;
        }

        public Point getEndPoint() {
            return endPoint;
        }
    }


    private Point offsetPoint(Point point){
        return new Point(point.getX() + x, point.getY() + y);
    }

    private Point[] offsetPoints(Point[] points){
       return Arrays.stream(points).map(this::offsetPoint).toArray(Point[]::new);
    }

    private StartEndPoint[] offsetStartAndPoints(StartEndPoint[] points){
        return Arrays.stream(points)
                .map(startEndPoint -> new StartEndPoint(offsetPoint(startEndPoint.getStartPoint()), offsetPoint(startEndPoint.getEndPoint())))
                        .toArray(StartEndPoint[]::new);
    }

}
