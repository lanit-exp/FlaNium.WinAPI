package FlaNium.WinAPI.webdriver;

import FlaNium.WinAPI.actions.KeyboardActions;
import FlaNium.WinAPI.actions.MouseActions;
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

    private static final String GET_ACTIVE_WINDOW = "getActiveWindow";
    private static final String SET_ROOT_ELEMENT = "setRootElement";
    private static final String CHANGE_PROCESS = "changeProcess";
    private static final String KILL_PROCESSES = "killProcesses";


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

    // ----------------------- Override --------------------------------------------------------------------------------

    @Override
    public Response execute(String driverCommand, Map<String, ?> parameters) {
        return super.execute(driverCommand, parameters);
    }

    @Override
    public Response execute(String command) {
        return super.execute(command);
    }

    // ------------------------ Methods --------------------------------------------------------------------------------

    /**
     * Get the active window or current root element.
     *
     * @return The active window or current root element.
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

    /**
     * Sets the desktop as the root element for item searches and other actions.
     * By default, the root element is the application's main window.
     */
    public void setDesktopAsRootElement(){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("type", "desktop");
        this.execute(SET_ROOT_ELEMENT, parameters);
    }

    /**
     * Sets the main window of the connected process as the root element.
     */
    public void resetRootElement(){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("type", "process");
        this.execute(SET_ROOT_ELEMENT, parameters);
    }

    /**
     * Sets the given web element as the root element.
     * @param webElement Any web element.
     */
    public void setRootElement(RemoteWebElement webElement){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("type", "element");
        parameters.put("id", webElement.getId());
        this.execute(SET_ROOT_ELEMENT, parameters);
    }

    /**
     * Attaches to the first process found by name.
     * Changes the root element to the process's main window.
     * Also terminates the given process at the end of the session.
     * @param processName Process name.
     * @param timeOut process search timeout in ms.
     */
    public void changeProcess(String processName, int timeOut){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", processName);
        parameters.put("timeout", timeOut);
        this.execute(CHANGE_PROCESS, parameters);
    }

    /**
     * Terminates all processes found by name.
     * @param processName Process name.
     */
    public void killAllProcessesByName(String processName){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", processName);
        this.execute(KILL_PROCESSES, parameters);
    }

    // --------------------------- Actions -----------------------------------------------------------------------------
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
     * Get Mouse Actions instance.
     *
     * @return Mouse Actions instance.
     */
    public MouseActions mouseActions() {
        return new MouseActions(this);
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
