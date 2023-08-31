package FlaNium.WinAPI;

import FlaNium.WinAPI.exceptions.PropertyLoadException;
import FlaNium.WinAPI.property.PropertyList;
import FlaNium.WinAPI.property.PropertyLoader;
import FlaNium.WinAPI.webdriver.DesktopOptions;
import FlaNium.WinAPI.webdriver.FlaNiumDriver;
import FlaNium.WinAPI.webdriver.FlaNiumDriverService;
import FlaNium.WinAPI.webdriver.FlaNiumOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FlaNium {


    public static FlaNiumDriver initDriver() {
        PropertyLoader.loadDriverProperties();
        PropertyLoader.loadAppProperties();
        return getDriver(getOptions());
    }

    public static FlaNiumDriver initDriver(String appPropertyName) {
        PropertyLoader.loadDriverProperties();
        PropertyLoader.loadAppProperties(appPropertyName);
        return getDriver(getOptions());
    }

    public static FlaNiumDriver initDriverWithoutStartApp() {
        PropertyLoader.loadDriverProperties();
        return getDriver(new DesktopOptions());
    }

    public static FlaNiumDriver initDriverWithoutStartApp(int responseTimeoutMs) {
        PropertyLoader.loadDriverProperties();
        return getDriver(new DesktopOptions().setResponseTimeout(responseTimeoutMs));
    }

    private static FlaNiumDriver getDriver(FlaNiumOptions options) {
        if (isRemote()) return getRemoteDriver(options);
        else return getLocalDriver(options);
    }

    private static FlaNiumDriver getLocalDriver(FlaNiumOptions options) {
        return new FlaNiumDriver(getService(), options);
    }

    private static FlaNiumDriver getRemoteDriver(FlaNiumOptions options) {
        return new FlaNiumDriver(getUrl(), options);
    }

    private static boolean isRemote() {
        return Boolean.parseBoolean(System.getProperty(PropertyList.Driver.DRIVER_REMOTE.getValue()));
    }

    private static URL getUrl() {
        String remoteUrl = getRequiredProperty(PropertyList.Driver.DRIVER_REMOTE_URL.getValue());

        try {
            return new URL(remoteUrl);
        } catch (MalformedURLException e) {
            throw new PropertyLoadException(String.format("Invalid URL format in System.property '%s': '%s'", PropertyList.Driver.DRIVER_REMOTE.getValue(), remoteUrl));
        }
    }

    private static FlaNiumDriverService getService() {

        String exe = getRequiredProperty(PropertyList.Driver.DRIVER_EXE.getValue());
        String port = System.getProperty(PropertyList.Driver.DRIVER_PORT.getValue());
        String verbose = System.getProperty(PropertyList.Driver.DRIVER_VERBOSE.getValue());
        String silent = System.getProperty(PropertyList.Driver.DRIVER_SILENT.getValue());
        String timeout = System.getProperty(PropertyList.Driver.DRIVER_TIMEOUT.getValue());
        String logFile = System.getProperty(PropertyList.Driver.DRIVER_LOG_FILE.getValue());

        FlaNiumDriverService.Builder builder = new FlaNiumDriverService.Builder();

        if (notNullAndNotEmpty(exe)) builder.usingDriverExecutable(new File(exe).getAbsoluteFile());
        if (notNullAndNotEmpty(port)) builder.usingPort(Integer.parseInt(port));
        if (notNullAndNotEmpty(verbose)) builder.withVerbose(Boolean.parseBoolean(verbose));
        if (notNullAndNotEmpty(silent)) builder.withSilent(Boolean.parseBoolean(silent));
        if (notNullAndNotEmpty(timeout)) builder.withTimeout(Duration.ofSeconds(Integer.parseInt(timeout)));
        if (notNullAndNotEmpty(logFile)) builder.withLogFile(new File(logFile).getAbsoluteFile());

        return builder.build();
    }

    private static FlaNiumOptions getOptions() {
        String app = getRequiredProperty(PropertyList.App.APP_PATH.getValue());
        String args = System.getProperty(PropertyList.App.APP_ARGS.getValue());
        String connectToRunningApp = System.getProperty(PropertyList.App.CONNECT_TO_RUNNING_APP.getValue());
        String launchDelay = System.getProperty(PropertyList.App.LAUNCH_DELAY.getValue());
        String processFindTimeOut = System.getProperty(PropertyList.App.PROCESS_FIND_TIMEOUT.getValue());
        String processName = System.getProperty(PropertyList.App.PROCESS_NAME.getValue());
        String injectionActivate = System.getProperty(PropertyList.App.INJECTION_ACTIVATE.getValue());
        String injectionDllType = System.getProperty(PropertyList.App.INJECTION_DLL_TYPE.getValue());
        String responseTimeout = System.getProperty(PropertyList.App.APP_RESPONSE_TIMEOUT.getValue());
        DesktopOptions options = new DesktopOptions();

        if (notNullAndNotEmpty(app)) {
            if (!app.contains("<")) {
                String os = System.getProperty("os.name");
                if (os != null && os.toLowerCase().contains("win")) {
                    app = new File(app).getAbsolutePath();
                }
            }
            options.setApplicationPath(app);
        }

        if (notNullAndNotEmpty(args)) options.setArguments(args);
        if (notNullAndNotEmpty(connectToRunningApp)) options.setConnectToRunningApp(Boolean.parseBoolean(connectToRunningApp));
        if (notNullAndNotEmpty(launchDelay)) options.setLaunchDelay(Integer.parseInt(launchDelay) * 1000);
        if (notNullAndNotEmpty(processFindTimeOut)) options.setProcessFindTimeOut(Integer.parseInt(processFindTimeOut) * 1000);
        if (notNullAndNotEmpty(processName)) options.setProcessName(processName);
        if (notNullAndNotEmpty(injectionActivate)) options.setInjectionActivate(Boolean.parseBoolean(injectionActivate));
        if (notNullAndNotEmpty(injectionDllType)) options.setInjectionDllType(injectionDllType);
        if (notNullAndNotEmpty(responseTimeout)) options.setResponseTimeout(Integer.parseInt(responseTimeout) * 1000);

        return options;
    }

    private static String getRequiredProperty(String propertyName) {
        String property = System.getProperty(propertyName);

        if (property == null || property.isEmpty())
            throw new PropertyLoadException("Required property missing: " + propertyName);

        return property;
    }

    private static boolean notNullAndNotEmpty(String value) {
        return !(value == null || value.isEmpty());
    }

}
