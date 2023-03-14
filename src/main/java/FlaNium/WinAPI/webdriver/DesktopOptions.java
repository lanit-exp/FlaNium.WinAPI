package FlaNium.WinAPI.webdriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to manage options specific to {@link FlaNiumDriver}
 */
public class DesktopOptions implements FlaNiumOptions {
    private static final String APPLICATION_PATH_OPTION = "app";
    private static final String ARGUMENTS_OPTION = "args";
    private static final String CONNECT_TO_RUNNING_APP_OPTION = "connectToRunningApp";
    private static final String LAUNCH_DELAY_OPTION = "launchDelay";
    private static final String PROCESS_FIND_TIMEOUT = "processFindTimeOut";
    private static final String PROCESS_NAME_OPTION = "processName";
    private static final String INJECTION_ACTIVATE = "injectionActivate";
    private static final String INJECTION_DLL_TYPE = "injectionDllType";
    private static final String RESPONSE_TIMEOUT = "responseTimeout";

    private String applicationPath;
    private String arguments;
    private Boolean connectToRunningApp;
    private Integer launchDelay;
    private Integer processFindTimeOut;
    private String processName;
    private Boolean injectionActivate;
    private String injectionDllType;
    private Integer responseTimeout;


    /**
     * Sets the absolute path to an .exe file to be started.
     * @param applicationPath Absolute path to an .exe file to be started.
     */
    public DesktopOptions setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
        return this;
    }

    /**
     * Sets startup arguments of the application under test.
     * @param arguments Startup arguments of the application under test.
     */
    public DesktopOptions setArguments(String arguments) {
        this.arguments = arguments;
        return this;
    }

    /**
     * If false (default) - always starts a new application process (with closing the current one, if any).
     * <p>
     * If true and the application is not running, then starts the application.
     * <p>
     * If true and the application is running, then it simply uses the current state of the application.
     * <p>
     * Also, if true - then the application does not close when the session ends.
     *
     * @param connectToRunningApp An option that allows you to connect to a previously launched application.
     */
    public DesktopOptions setConnectToRunningApp(Boolean connectToRunningApp) {
        this.connectToRunningApp = connectToRunningApp;
        return this;
    }

    /**
     * Sets the launch delay in milliseconds, to be waited to let visuals to initialize after application started.
     * @param launchDelay Launch delay in milliseconds.
     */
    public DesktopOptions setLaunchDelay(Integer launchDelay) {
        this.launchDelay = launchDelay;
        return this;
    }

    /**
     * Sets the search time for the application process specified in the processName parameter.
     * @param processFindTimeOut Process lookup timeout in milliseconds.
     */
    public DesktopOptions setProcessFindTimeOut(Integer processFindTimeOut) {
        this.processFindTimeOut = processFindTimeOut;
        return this;
    }

    /**
     * Setting the name of the application process. It is used in cases when the process id changes after starting the application.
     * @param processName process name of the main window of the application.
     */
    public DesktopOptions setProcessName(String processName) {
        this.processName = processName;
        return this;
    }

    /**
     * Using injection technology to access application data.
     * Need EXTENDED version of FlaNium Driver.
     * @param injectionActivate Activate injection technology
     */
    public DesktopOptions setInjectionActivate(Boolean injectionActivate){
        this.injectionActivate = injectionActivate;
        return this;
    }

    /**
     * Set type of DLL for inject. Use with InjectionActivate.
     * @param injectionDllType Type of Dll.
     */
    public DesktopOptions setInjectionDllType(String injectionDllType){
        this.injectionDllType = injectionDllType;
        return this;
    }

    /**
     * Setting the response timeout.
     * If no response is received from the Win API within this time, an exception will be thrown.
     * @param responseTimeout response timeout in milliseconds.
     */
    public DesktopOptions setResponseTimeout(Integer responseTimeout){
        this.responseTimeout = responseTimeout;
        return this;
    }


    /**
     * Convert options to DesiredCapabilities for FlaNium Desktop Driver
     * @return The DesiredCapabilities for FlaNium Desktop Driver with these options.
     */
    @Override
    public Capabilities toCapabilities() {
        Map<String, Object> capabilityDictionary = new HashMap<>();
        capabilityDictionary.put(APPLICATION_PATH_OPTION, applicationPath);

        if ((arguments != null) && (arguments.length() > 0)) {
            capabilityDictionary.put(ARGUMENTS_OPTION, arguments);
        }

        if (connectToRunningApp != null) {
            capabilityDictionary.put(CONNECT_TO_RUNNING_APP_OPTION, connectToRunningApp);
        }

        if (launchDelay != null) {
            capabilityDictionary.put(LAUNCH_DELAY_OPTION, launchDelay);
        }

        if (processFindTimeOut != null) {
            capabilityDictionary.put(PROCESS_FIND_TIMEOUT, processFindTimeOut);
        }

        if (processName != null) {
            capabilityDictionary.put(PROCESS_NAME_OPTION, processName);
        }

        if (injectionActivate != null) {
            capabilityDictionary.put(INJECTION_ACTIVATE, injectionActivate);
        }

        if (injectionDllType != null) {
            capabilityDictionary.put(INJECTION_DLL_TYPE, injectionDllType);
        }

        if (responseTimeout != null) {
            capabilityDictionary.put(RESPONSE_TIMEOUT, responseTimeout);
        }

        return new DesiredCapabilities(capabilityDictionary);
    }

}
