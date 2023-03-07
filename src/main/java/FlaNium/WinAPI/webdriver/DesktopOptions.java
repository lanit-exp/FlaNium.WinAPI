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
    private static final String DEBUG_CONNECT_TO_RUNNING_APP_OPTION = "debugConnectToRunningApp";
    private static final String LAUNCH_DELAY_OPTION = "launchDelay";
    private static final String PROCESS_NAME_OPTION = "processName";
    private static final String INJECTION_ACTIVATE = "injectionActivate";
    private static final String APP_TYPE = "appType";
    private static final String RESPONSE_TIMEOUT = "responseTimeout";

    private String applicationPath;
    private String arguments;
    private Boolean debugConnectToRunningApp;
    private Integer launchDelay;
    private String processName;
    private Boolean injectionActivate;
    private String appType;
    private Integer responseTimeout;


    /**
     * Sets the absolute local path to an .exe file to be started.
     * This capability is not required if debugConnectToRunningApp is specified.
     * @param applicationPath Absolute local path to an .exe file to be started.
     */
    public DesktopOptions setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
        return this;
    }

    /**
     * Sets startup argunments of the application under test.
     * @param arguments Startup argunments of the application under test.
     */
    public DesktopOptions setArguments(String arguments) {
        this.arguments = arguments;
        return this;
    }

    /**
     * Sets a value indicating whether debug connect to running app.
     * If true, then application starting step are skipped.
     * @param debugConnectToRunningApp Value indicating whether debug connect to running app.
     */
    public DesktopOptions setDebugConnectToRunningApp(Boolean debugConnectToRunningApp) {
        this.debugConnectToRunningApp = debugConnectToRunningApp;
        return this;
    }

    /**
     * Sets the launch delay in milliseconds, to be waited to let visuals to initialize after application started.
     * @param launchDelay Launch delay in milliseconds
     */
    public DesktopOptions setLaunchDelay(Integer launchDelay) {
        this.launchDelay = launchDelay;
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
     * Set type of application. Use with InjectionActivate.
     * Need EXTENDED version of FlaNium Driver.
     * @param appType Type of application.
     */
    public DesktopOptions setAppType(AppType appType){
        this.appType = appType.toString();
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

        if (debugConnectToRunningApp != null) {
            capabilityDictionary.put(DEBUG_CONNECT_TO_RUNNING_APP_OPTION, debugConnectToRunningApp);
        }

        if (launchDelay != null) {
            capabilityDictionary.put(LAUNCH_DELAY_OPTION, launchDelay);
        }

        if (processName != null) {
            capabilityDictionary.put(PROCESS_NAME_OPTION, processName);
        }

        if (injectionActivate != null) {
            capabilityDictionary.put(INJECTION_ACTIVATE, injectionActivate);
        }

        if (appType != null) {
            capabilityDictionary.put(APP_TYPE, appType);
        }

        if (responseTimeout != null) {
            capabilityDictionary.put(RESPONSE_TIMEOUT, responseTimeout);
        }

        return new DesiredCapabilities(capabilityDictionary);
    }

    public enum AppType{
        DELPHI;
    }
}
