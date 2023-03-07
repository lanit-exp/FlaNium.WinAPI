package FlaNium.WinAPI;

import FlaNium.WinAPI.actions.ScreenshotActions;
import FlaNium.WinAPI.actions.TouchActions;
import FlaNium.WinAPI.elements.Window;
import FlaNium.WinAPI.enums.BasePoint;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DesktopElement extends RemoteWebElement {

    private static final String WINDOW_GET_ACTIVE_WINDOW = "windowGetActiveWindow";

    private static final String ELEMENT_DRAG_AND_DROP = "elementDragAndDrop";
    private static final String ELEMENT_MOUSE_ACTION = "elementMouseAction";


    public DesktopElement(WebElement element) {
        this.setParent(getRemoteWebDriver(element));
        this.setId(getId(element));
    }

    private static RemoteWebDriver getRemoteWebDriver(WebElement element) {
        if (!(element instanceof RemoteWebElement))
            throw new ClassCastException("Specified cast is not valid. Please use RemoteWebElement as parameter.");
        RemoteWebElement remoteWebElement = (RemoteWebElement) element;
        return (RemoteWebDriver) remoteWebElement.getWrappedDriver();
    }

    private static String getId(WebElement element) {

        try {
            Method methodInfo = element.getClass().getMethod("getId");
            return methodInfo.invoke(element).toString();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return null;
        }
    }


    //region Create from Response
    protected RemoteWebElement createRemoteWebElementFromResponse(Response response) {
        Object value = response.getValue();
        if (value instanceof RemoteWebElement) {
            return (RemoteWebElement) value;
        }

        if (!(value instanceof Map<?, ?>)) {
            return null;
        }
        Map<?, ?> elementDictionary = (Map<?, ?>) value;
        RemoteWebElement result = new RemoteWebElement();
        result.setParent((RemoteWebDriver) this.getWrappedDriver());
        result.setId((String) elementDictionary.get("ELEMENT"));
        return result;
    }

    protected List<RemoteWebElement> createRemoteWebElementsFromResponse(Response response) {

        Object responseValue = response.getValue();

        List allElements;
        try {
            allElements = (List) responseValue;
        } catch (ClassCastException var8) {
            throw new WebDriverException("Returned value cannot be converted to List<WebElement>: " + responseValue, var8);
        }

        Iterator var6 = allElements.iterator();

        while (var6.hasNext()) {
            RemoteWebElement element = (RemoteWebElement) var6.next();
            element.setParent((RemoteWebDriver) this.getWrappedDriver());
        }

        return allElements;
    }

    protected List<LocalDateTime> createLocalDateTimeFromResponse(Response response) {

        Object responseValue = response.getValue();

        List allElements;
        try {
            allElements = (List) responseValue;
        } catch (ClassCastException var8) {
            throw new WebDriverException("Returned value cannot be converted to List<LocalDateTime>: " + responseValue, var8);
        }

        Iterator var6 = allElements.iterator();
        List<LocalDateTime> list = new ArrayList<>();

        while (var6.hasNext()) {
            Object object = var6.next();
            String value = object.toString();
            LocalDateTime localDateTime = parseDateTime(value);
            list.add(localDateTime);
        }

        return list;
    }
    //endregion

    protected Response exe(String command, HashMap<String, Object> parameters) {
        Response response;
        try {
            response = this.execute(command, parameters);
        } catch (NoSuchElementException e) {
            response = null;
        }
        return response;
    }

    //region Call Command
    protected Response callVoidCommand(String command) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, String value) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("value", value);
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, int index) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("index", index);
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, int index, String text) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("index", index);
        parameters.put("text", text);
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, int index, String text, int count) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("index", index);
        parameters.put("text", text);
        parameters.put("count", count);
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, int x, int y) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, LocalDateTime dateTime) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("dateTime", dateTime.toString());
        return exe(command, parameters);
    }
    //endregion

    //region public Method

    /**
     * Getting the "Name" attribute of an element.
     * @return The "Name" attribute of the current element.
     */
    public String getName() {
        return this.getAttribute("Name");
    }

    /**
     * Get the active window.
     * @return The active window.
     */
    public Window getActiveWindow() {
        Response response = callVoidCommand(WINDOW_GET_ACTIVE_WINDOW);
        return new Window(createRemoteWebElementFromResponse(response));
    }
    //endregion

    //region Parse

    protected double parseDouble(Response response) {
        String value = response.getValue().toString();
        if (value.contains(","))
            value = value.replace(",", ".");
        return Double.parseDouble(value);
    }

    protected LocalDateTime parseDateTime(String dateTime) {
        String pattern = null;

        if (dateTime.matches("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}:\\d{2}")) pattern = "dd.MM.uuuu HH:mm:ss";
        else if (dateTime.matches("\\d{2}\\.\\d{2}\\.\\d{4} \\d{1}:\\d{2}:\\d{2}")) pattern = "dd.MM.uuuu H:mm:ss";

        if (pattern != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        }
        return LocalDateTime.parse(dateTime);
    }
    //endregion




    /**
     * Drags and drops the mouse from the starting point (Base point of element bounding rectangle + x, y coordinates)
     * with the given distance.
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x X Coordinate relative to base point of element bounding rectangle.
     * @param y Y Coordinate relative to base point of element bounding rectangle.
     * @param dx The x distance to drag and drop, + for right, - for left.
     * @param dy The y distance to drag and drop, + for down, - for up.
     */
    public void dragAndDrop(BasePoint basePoint, int x, int y, int dx, int dy) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("id", this.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("dx", dx);
        parameters.put("dy", dy);
        parameters.put("basePoint", basePoint.toString());

        this.execute(ELEMENT_DRAG_AND_DROP, parameters);
    }

    /**
     * Drags and drops the mouse from the starting point (Base point of element bounding rectangle + x, y coordinates)
     * with the given distance and within the specified time.
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x X Coordinate relative to base point of element bounding rectangle.
     * @param y Y Coordinate relative to base point of element bounding rectangle.
     * @param dx The x distance to drag and drop, + for right, - for left.
     * @param dy The y distance to drag and drop, + for down, - for up.
     * @param duration Execution time in milliseconds.
     */
    public void smoothDragAndDrop(BasePoint basePoint, int x, int y, int dx, int dy, int duration) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("id", this.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("dx", dx);
        parameters.put("dy", dy);
        parameters.put("basePoint", basePoint.toString());
        parameters.put("duration", duration);

        this.execute(ELEMENT_DRAG_AND_DROP, parameters);
    }

    /**
     * Move the mouse to the point (Base point of element bounding rectangle + x, y coordinates).
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x X Coordinate relative to base point of element bounding rectangle.
     * @param y Y Coordinate relative to base point of element bounding rectangle.
     */
    public void mouseMove(BasePoint basePoint, int x, int y) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("id", this.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("basePoint", basePoint.toString());
        parameters.put("action", "move");

        this.execute(ELEMENT_MOUSE_ACTION, parameters);
    }

    /**
     * Click the mouse on the point (Base point of element bounding rectangle + x, y coordinates).
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x X Coordinate relative to base point of element bounding rectangle.
     * @param y Y Coordinate relative to base point of element bounding rectangle.
     */
    public void mouseClick(BasePoint basePoint, int x, int y) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("id", this.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("basePoint", basePoint.toString());
        parameters.put("action", "click");

        this.execute(ELEMENT_MOUSE_ACTION, parameters);
    }


    /**
     * Right click the mouse on the point (Base point of element bounding rectangle + x, y coordinates).
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x X Coordinate relative to base point of element bounding rectangle.
     * @param y Y Coordinate relative to base point of element bounding rectangle.
     */
    public void mouseRightClick(BasePoint basePoint, int x, int y) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("id", this.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("basePoint", basePoint.toString());
        parameters.put("action", "rightClick");

        this.execute(ELEMENT_MOUSE_ACTION, parameters);
    }


    /**
     * Double click the mouse on the point (Base point of element bounding rectangle + x, y coordinates).
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x X Coordinate relative to base point of element bounding rectangle.
     * @param y Y Coordinate relative to base point of element bounding rectangle.
     */
    public void mouseDoubleClick(BasePoint basePoint, int x, int y) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("id", this.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("basePoint", basePoint.toString());
        parameters.put("action", "doubleClick");

        this.execute(ELEMENT_MOUSE_ACTION, parameters);
    }

    /**
     * Get Bounding Rectangle of element.
     * @return Rectangle instance.
     */
    //todo Проверить
    public Rectangle getElementRect() {
        String rectString = this.getAttribute("BoundingRectangle");
        String[] rect = rectString.split(",");
        return new Rectangle(Integer.parseInt(rect[0].trim()), Integer.parseInt(rect[1].trim())
                , Integer.parseInt(rect[3].trim()), Integer.parseInt(rect[2].trim()));
    }


    /**
     * Cast DesktopElement to a Typed Element.
     * @return WebElementExtensions instance.
     */
    public WebElementCast to(){
        return new WebElementCast(this);
    }

    /**
     * Cast DesktopElement to a Coordinate Element.
     * @return WebElementExtensions instance.
     */
    //todo Проверить
    public CoordinateElement toCoordinateElement(){
        Rectangle rectangle = getElementRect();
        return new CoordinateElement(this, BasePoint.TOP_LEFT, 0, 0, rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * Get Touch Actions instance.
     * @return Touch Actions instance.
     */
    //todo Проверить
    public TouchActions touchActions(){
        return new TouchActions(this);
    }

    /**
     * Get Screenshot Actions of current item.
     * @return ScreenshotActions instance.
     */
    public ScreenshotActions screenshotActions(){
        return new ScreenshotActions(this);
    }

}
