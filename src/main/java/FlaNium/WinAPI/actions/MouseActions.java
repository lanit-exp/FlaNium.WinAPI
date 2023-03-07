package FlaNium.WinAPI.actions;

import FlaNium.WinAPI.DesktopElement;
import FlaNium.WinAPI.enums.BasePoint;

import java.util.HashMap;

public class MouseActions {

    private static final String ELEMENT_DRAG_AND_DROP = "elementDragAndDrop";
    private static final String ELEMENT_MOUSE_ACTION = "elementMouseAction";

    private DesktopElement desktopElement;

    public MouseActions(DesktopElement desktopElement) {
        this.desktopElement = desktopElement;
    }

    /**
     * Drags and drops the mouse from the starting point (Base point of element bounding rectangle + x, y coordinates)
     * with the given distance.
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x         X Coordinate relative to base point of element bounding rectangle.
     * @param y         Y Coordinate relative to base point of element bounding rectangle.
     * @param dx        The x distance to drag and drop, + for right, - for left.
     * @param dy        The y distance to drag and drop, + for down, - for up.
     */
    public void dragAndDrop(BasePoint basePoint, int x, int y, int dx, int dy) {
        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put("id", desktopElement.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("dx", dx);
        parameters.put("dy", dy);
        parameters.put("basePoint", basePoint.toString());

        desktopElement.execute(ELEMENT_DRAG_AND_DROP, parameters);
    }

    /**
     * Drags and drops the mouse from the starting point (Base point of element bounding rectangle + x, y coordinates)
     * with the given distance and within the specified time.
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x         X Coordinate relative to base point of element bounding rectangle.
     * @param y         Y Coordinate relative to base point of element bounding rectangle.
     * @param dx        The x distance to drag and drop, + for right, - for left.
     * @param dy        The y distance to drag and drop, + for down, - for up.
     * @param duration  Execution time in milliseconds.
     */
    public void smoothDragAndDrop(BasePoint basePoint, int x, int y, int dx, int dy, int duration) {
        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put("id", desktopElement.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("dx", dx);
        parameters.put("dy", dy);
        parameters.put("basePoint", basePoint.toString());
        parameters.put("duration", duration);

        desktopElement.execute(ELEMENT_DRAG_AND_DROP, parameters);
    }

    /**
     * Move the mouse to the point (Base point of element bounding rectangle + x, y coordinates).
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x         X Coordinate relative to base point of element bounding rectangle.
     * @param y         Y Coordinate relative to base point of element bounding rectangle.
     */
    public void mouseMove(BasePoint basePoint, int x, int y) {
        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put("id", desktopElement.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("basePoint", basePoint.toString());
        parameters.put("action", "move");

        desktopElement.execute(ELEMENT_MOUSE_ACTION, parameters);
    }

    /**
     * Click the mouse on the point (Base point of element bounding rectangle + x, y coordinates).
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x         X Coordinate relative to base point of element bounding rectangle.
     * @param y         Y Coordinate relative to base point of element bounding rectangle.
     */
    public void mouseClick(BasePoint basePoint, int x, int y) {
        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put("id", desktopElement.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("basePoint", basePoint.toString());
        parameters.put("action", "click");

        desktopElement.execute(ELEMENT_MOUSE_ACTION, parameters);
    }


    /**
     * Right click the mouse on the point (Base point of element bounding rectangle + x, y coordinates).
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x         X Coordinate relative to base point of element bounding rectangle.
     * @param y         Y Coordinate relative to base point of element bounding rectangle.
     */
    public void mouseRightClick(BasePoint basePoint, int x, int y) {
        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put("id", desktopElement.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("basePoint", basePoint.toString());
        parameters.put("action", "rightClick");

        desktopElement.execute(ELEMENT_MOUSE_ACTION, parameters);
    }


    /**
     * Double click the mouse on the point (Base point of element bounding rectangle + x, y coordinates).
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x         X Coordinate relative to base point of element bounding rectangle.
     * @param y         Y Coordinate relative to base point of element bounding rectangle.
     */
    public void mouseDoubleClick(BasePoint basePoint, int x, int y) {
        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put("id", desktopElement.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("basePoint", basePoint.toString());
        parameters.put("action", "doubleClick");

        desktopElement.execute(ELEMENT_MOUSE_ACTION, parameters);
    }

}
