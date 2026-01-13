package FlaNium.WinAPI.webdriver;

import FlaNium.WinAPI.actions.KeyboardActions;
import FlaNium.WinAPI.actions.MouseActions;
import FlaNium.WinAPI.actions.ScreenshotActions;
import FlaNium.WinAPI.actions.TouchActions;
import FlaNium.WinAPI.exceptions.FlaNiumDriverException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlaNiumDriver extends RemoteWebDriver {

    private static final String GET_ACTIVE_WINDOW = "getActiveWindow";
    private static final String SET_ROOT_ELEMENT = "setRootElement";
    private static final String CHANGE_PROCESS = "changeProcess";
    private static final String CHANGE_PROCESS_BY_ID = "changeProcessById";
    private static final String GET_CURRENT_PROCESS_ID = "getCurrentProcessId";
    private static final String GET_PROCESS_ID_BY_NAME = "getProcessIdByName";
    private static final String KILL_PROCESSES = "killProcesses";
    private static final String KILL_PROCESS_BY_ID = "killProcessById";
    private static final String FILE_OR_DIRECTORY_EXISTS = "fileOrDirectoryExists";
    private static final String DELETE_FILE_OR_DIRECTORY = "deleteFileOrDirectory";
    private static final String FILE_DOWNLOAD = "fileDownload";
    private static final String FILE_UPLOAD = "fileUpload";
    private static final String START_APP = "startApp";


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

    //---------------------- Root Element ------------------------------------------------------------------------------

    /**
     * Sets the desktop as the root element for item searches and other actions.
     * By default, the root element is the application's main window.
     */
    public void setDesktopAsRootElement() {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("type", "desktop");
        this.execute(SET_ROOT_ELEMENT, parameters);
    }

    /**
     * Sets the main window of the connected process as the root element.
     */
    public void resetRootElement() {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("type", "process");
        this.execute(SET_ROOT_ELEMENT, parameters);
    }

    /**
     * Sets the given web element as the root element.
     *
     * @param webElement Any web element.
     */
    public void setRootElement(RemoteWebElement webElement) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("type", "element");
        parameters.put("id", webElement.getId());
        this.execute(SET_ROOT_ELEMENT, parameters);
    }


    //-------------------------- Process -------------------------------------------------------------------------------

    /**
     * Attaches to the first process found by name.
     * Changes the root element to the process's main window.
     * Also terminates the given process at the end of the session.
     *
     * @param processName Process name.
     * @param timeOut     process search timeout in ms.
     */
    public void changeProcess(String processName, int timeOut) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", processName);
        parameters.put("timeout", timeOut);
        this.execute(CHANGE_PROCESS, parameters);
    }

    /**
     * Attaches to the process found by id.
     * @param processId Process Id.
     */
    public void changeProcessById(int processId) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("id", processId);
        this.execute(CHANGE_PROCESS_BY_ID, parameters);
    }

    /**
     * Returns the id of all processes with the name.
     * @param processName name of process.
     * @return Processes id.
     */
    public List<Integer> getProcessIdByName(String processName) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", processName);
        Response response = this.execute(GET_PROCESS_ID_BY_NAME, parameters);
        return (List<Integer>) response.getValue();
    }

    /**
     * Returns the current process id.
     * @return Process id. If the process is not attached, it returns -1.
     */
    public Integer getCurrentProcessId() {
        Response response = this.execute(GET_CURRENT_PROCESS_ID);
        return Integer.parseInt(response.getValue().toString());
    }

    /**
     * Terminates all processes found by name.
     *
     * @param processName Process name.
     * @return the current number of killed processes with the name.
     */
    public int killAllProcessesByName(String processName) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", processName);
        Response response = this.execute(KILL_PROCESSES, parameters);
        return Integer.parseInt(response.getValue().toString());
    }

    /**
     * Terminates processes with id.
     *
     * @param id Process Id.
     * @return true - if process successful kill or false - if process not found.
     */
    public boolean killProcessesById(Integer id) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        Response response = this.execute(KILL_PROCESS_BY_ID, parameters);
        return Boolean.parseBoolean(response.getValue().toString());
    }

    //----------------------------- Files ------------------------------------------------------------------------------

    /**
     * Checks if a file or folder exists at the specified path.
     *
     * @param path Path to folder or file. System variables are supported, for example: <br>
     *             "&lt;LOCALAPPDATA&gt;/folder/file.exe"
     */
    public boolean fileOrDirectoryExists(String path) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("path", path);
        Response response = this.execute(FILE_OR_DIRECTORY_EXISTS, parameters);
        return Boolean.parseBoolean(response.getValue().toString());
    }

    /**
     * Deletes a file or folder at the specified path. <br>
     * If an error occurs during deletion or the file or folder is missing, an exception is thrown.
     *
     * @param path Path to folder or file. System variables are supported, for example: <br>
     *             "&lt;LOCALAPPDATA&gt;/folder/file.exe"
     */
    public void deleteFileOrDirectory(String path) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("path", path);
        this.execute(DELETE_FILE_OR_DIRECTORY, parameters);
    }

    /**
     * Download a file at the specified path. <br>
     * If an error occurs during read the file or file is missing, an exception is thrown.
     *
     * @param filePath Path to file. System variables are supported, for example: <br>
     *                 "&lt;LOCALAPPDATA&gt;/folder/file.exe"
     */
    public byte[] downloadFileAndGetBytes(String filePath) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("path", filePath);

        Response response = this.execute(FILE_DOWNLOAD, parameters);

        Object result = response.getValue();

        if (result instanceof String) {
            return Base64.getDecoder().decode((String) result);
        } else throw new FlaNiumDriverException(String.format("Unexpected result for %s command: %s",
                "downloadFile", result == null ? "null" : result.getClass().getName() + " instance"));
    }


    /**
     * Downloads the file from the specified path and saves it locally.
     *
     * @param filePath     path to the file on the host with the FlaNium driver
     * @param filePathSave local path to save the downloaded file
     * @return saved file
     * @throws IOException
     */
    public File downloadFileAndSaveToFile(String filePath, String filePathSave) throws IOException {
        byte[] bytes = downloadFileAndGetBytes(filePath);
        File result = new File(filePathSave);
        FileUtils.writeByteArrayToFile(result, bytes);
        return result;
    }

    /**
     * Uploads an array of bytes and saves it as a file on the host with the FlaNium driver.
     *
     * @param bytes          data to save to file.
     * @param targetFilePath File path to save.
     */
    public void uploadFile(byte[] bytes, String targetFilePath) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("path", targetFilePath);
        parameters.put("bytes", Base64.getEncoder().encodeToString(bytes));

        this.execute(FILE_UPLOAD, parameters);
    }

    /**
     * Uploads data from a file to a host running the FlaNium driver and saves it as a file.
     *
     * @param file           file to upload and save to file.
     * @param targetFilePath file path to save.
     * @throws IOException
     */
    public void uploadFile(File file, String targetFilePath) throws IOException {
        uploadFile(FileUtils.readFileToByteArray(file), targetFilePath);
    }


    //----------------------------- App --------------------------------------------------------------------------------

    /**
     * Launches the application at the given path.
     *
     * @param appPath       The absolute path to an .exe file to be started.
     *                      System variables are supported, for example: <br>
     *                      "&lt;LOCALAPPDATA&gt;/folder/file.exe"
     * @param appArguments  Startup arguments of the application. May be null.
     * @param launchDelayMs Static timeout to start in ms.
     * @param startSecondInstance If the application is already running, it launches a second instance rather than attaching to the current one.
     *
     * @return Process Id of launched app.
     */
    public Integer startApp(String appPath, String appArguments, Integer launchDelayMs, boolean startSecondInstance) {
        HashMap<String, Object> parameters = new HashMap<>();

        parameters.put("appPath", appPath);
        if (appArguments != null) parameters.put("appArguments", appArguments);
        parameters.put("launchDelay", launchDelayMs);
        parameters.put("startSecondInstance", startSecondInstance);

        Response response = this.execute(START_APP, parameters);

        return Integer.parseInt(response.getValue().toString());
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
