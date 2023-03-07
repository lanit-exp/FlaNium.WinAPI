package FlaNium.WinAPI.webdriver;

import FlaNium.WinAPI.actions.KeyboardActions;
import FlaNium.WinAPI.actions.ScreenshotActions;
import FlaNium.WinAPI.actions.TouchActions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FlaNiumDriver extends RemoteWebDriver {


    private static final String DRAG_AND_DROP = "dragAndDrop";
    private static final String GET_ACTIVE_WINDOW = "getActiveWindow";


    /**
     * Initializes a new instance of the {@link FlaNiumDriver} class using the specified {@link FlaNiumDriverService}
     * and options.
     *
     * @param service The {@link FlaNiumDriverService} to use.
     * @param options The {@link FlaNiumOptions} used to initialize the driver.
     */
    public FlaNiumDriver(FlaNiumDriverService service, FlaNiumOptions options) {
        super(new FlaNiumDriverCommandExecutor(service), options.toCapabilities());
    }

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} class using the specified {@link FlaNiumDriverService}
     * and options.
     *
     * @param service The {@link FlaNiumDriverService} to use.
     * @param dc      The {@link DesiredCapabilities} used to initialize the driver.
     */
    public FlaNiumDriver(FlaNiumDriverService service, DesiredCapabilities dc) {
        super(new FlaNiumDriverCommandExecutor(service), dc);
    }

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} lass using the specified remote address and options.
     *
     * @param remoteAddress URL containing the address of the FlaNiumDriver remote server.
     * @param options       The {@link FlaNiumOptions} object to be used with the FlaNium driver.
     */
    public FlaNiumDriver(URL remoteAddress, FlaNiumOptions options) {
        super(new FlaNiumDriverCommandExecutor(remoteAddress), options.toCapabilities());
    }

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} lass using the specified remote address and options.
     *
     * @param remoteAddress URL containing the address of the FlaNiumDriver remote server.
     * @param dc            The {@link DesiredCapabilities} object to be used with the FlaNium driver.
     */
    public FlaNiumDriver(URL remoteAddress, DesiredCapabilities dc) {
        super(new FlaNiumDriverCommandExecutor(remoteAddress), dc);
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

        this.execute(DRAG_AND_DROP, parameters);
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

        this.execute(DRAG_AND_DROP, parameters);
    }

    /**
     * Get the active window.
     *
     * @return The active window.
     */
    public RemoteWebElement getActiveWindow() {
        try {
            Response response = this.execute(GET_ACTIVE_WINDOW);

            Object value = response.getValue();

            if (value instanceof RemoteWebElement) {
                return (RemoteWebElement) value;
            }

            if (!(value instanceof Map<?, ?>)) {
                return null;
            }

            Map<?, ?> elementDictionary = (Map<?, ?>) value;
            RemoteWebElement result = new RemoteWebElement();
            result.setParent(this);
            result.setId((String) elementDictionary.get("ELEMENT"));
            return result;

        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Response execute(String driverCommand, Map<String, ?> parameters) {
        return super.execute(driverCommand, parameters);
    }

    @Override
    public Response execute(String command) {
        return super.execute(command);
    }

    /**
     * Get Touch Actions instance.
     *
     * @return Touch Actions instance.
     */
    public TouchActions touchActions() {
        return new TouchActions(this);
    }

    /**
     * Get Keyboard Actions instance.
     *
     * @return Keyboard Actions instance.
     */
    public KeyboardActions keyboardActions() {
        return new KeyboardActions(this);
    }


    /**
     * Get Screenshot Actions of current item.
     *
     * @return ScreenshotActions instance.
     */
    public ScreenshotActions screenshotActions() {
        return new ScreenshotActions(this);
    }
}
