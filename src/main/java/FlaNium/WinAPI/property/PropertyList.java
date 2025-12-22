package FlaNium.WinAPI.property;

import java.util.Arrays;

public class PropertyList {
    
    public enum Driver {

        DRIVER_REMOTE ( "flanium.driver.remote"),

        DRIVER_REMOTE_URL ( "flanium.driver.remoteUrl"),

        DRIVER_EXE ( "flanium.driver.exe"),
        DRIVER_PORT ( "flanium.driver.port"),
        DRIVER_VERBOSE ( "flanium.driver.verbose"),
        DRIVER_SILENT ( "flanium.driver.silent"),
        DRIVER_TIMEOUT ( "flanium.driver.timeout"),
        DRIVER_LOG_FILE ( "flanium.driver.logFile"),
        DRIVER_CACHED_STRATEGY ( "flanium.driver.cachedStrategy");

        private String value;

        public String getValue() {
            return value;
        }

        Driver(String value) {
            this.value = value;
        }

        public static boolean containsValue(String value){
           return Arrays.stream(Driver.values()).anyMatch(driver -> driver.value.equals(value));
        }
    }

    public enum App {

        APP_PATH ( "flanium.app.path"),
        APP_ARGS ( "flanium.app.args"),
        CONNECT_TO_RUNNING_APP ( "flanium.app.connectToRunningApp"),
        LAUNCH_DELAY ( "flanium.app.launchDelay"),
        
        PROCESS_FIND_TIMEOUT ( "flanium.app.processFindTimeOut"),
        PROCESS_NAME ( "flanium.app.processName"),
        
        INJECTION_ACTIVATE ( "flanium.app.injectionActivate"),
        INJECTION_DLL_TYPE ( "flanium.app.injectionDllType"),
        
        APP_RESPONSE_TIMEOUT ( "flanium.app.responseTimeout");

        private String value;

        public String getValue() {
            return value;
        }

        App(String value) {
            this.value = value;
        }

        public static boolean containsValue(String value){
            return Arrays.stream(App.values()).anyMatch(app -> app.value.equals(value));
        }

    }    
    

}
