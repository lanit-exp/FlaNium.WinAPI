package FlaNium.WinAPI.actions;

import FlaNium.WinAPI.webdriver.FlaNiumDriver;

import java.util.HashMap;

public class MouseActions {

    private static final String DRAG_AND_DROP = "dragAndDrop";

    private FlaNiumDriver flaNiumDriver;

    public MouseActions(FlaNiumDriver flaNiumDriver) {
        this.flaNiumDriver = flaNiumDriver;
    }

    /**
     * Drags and drops the mouse from the starting point with the given distance.
     *
     * @param x  X coordinate of the start point.
     * @param y  Y coordinate of the start point.
     * @param dx The x distance to drag and drop, + for right, - for left.
     * @param dy The y distance to drag and drop, + for down, - for up.
     */
    public void dragAndDrop(int x, int y, int dx, int dy) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("dx", dx);
        parameters.put("dy", dy);

        flaNiumDriver.execute(DRAG_AND_DROP, parameters);
    }

    /**
     * Drags and drops the mouse from the starting point with the given distance within the specified time.
     *
     * @param x        X coordinate of the start point.
     * @param y        Y coordinate of the start point.
     * @param dx       The x distance to drag and drop, + for right, - for left.
     * @param dy       The y distance to drag and drop, + for down, - for up.
     * @param duration Execution time in milliseconds.
     */
    public void smoothDragAndDrop(int x, int y, int dx, int dy, int duration) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("dx", dx);
        parameters.put("dy", dy);
        parameters.put("duration", duration);

        flaNiumDriver.execute(DRAG_AND_DROP, parameters);
    }
}
