package FlaNium.WinAPI.webdriver;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;


public class FlaNiumDriverService extends DriverService {

    /**
     * System property that defines the location of the log that will be written by service
     */
    public static final String FLANIUM_DRIVER_LOG_PATH_PROPERTY = "flanium.logpath";

    /**
     * Creates a default instance of the FlaNiumDriverService using a default path to the FlaNium Desktop Driver.
     * @return A {@link FlaNiumDriverService} using FlaNium Desktop and random port
     */
    public static FlaNiumDriverService createDesktopService() {
        return new Builder().usingAnyFreePort().buildDesktopService();
    }


    protected FlaNiumDriverService(File executable, int port, ImmutableList<String> args,
                                   ImmutableMap<String, String> environment) throws IOException {
        super(executable, port, args, environment);
    }

    public static class Builder extends DriverService.Builder<FlaNiumDriverService, Builder> {
        private static final String DESKTOP_DRIVER_SERVICE_FILENAME = "FlaNium.Driver.exe";

        private static final String DESKTOP_DRIVER_EXE_PROPERTY = "";

        private static final String DESKTOP_DRIVER_DOCS_URL = "https://github.com/lanit-exp/FlaNium.WinAPI";

        private static final String DESKTOP_DRIVER_DOWNLOAD_URL = "https://github.com/lanit-exp/FlaNium.Desktop.Driver/releases";

        private File exe = null;
        private boolean verbose = false;
        private boolean silent = false;
        private final int DEFAULT_PORT = 9999;

        @Override
        public int score(Capabilities capabilities) {
            return 0;
        }

        /**
         * Sets which driver executable the builder will use.
         *
         * @param file The executable to use.
         * @return A self reference.
         */
        @Override
        public Builder usingDriverExecutable(File file) {
            checkNotNull(file);
            checkExecutable(file);
            this.exe = file;
            return this;
        }

        /**
         * Configures the driver server verbosity.
         *
         * @param verbose true for verbose output, false otherwise.
         * @return A self reference.
         */
        public Builder withVerbose(boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        /**
         * Configures the driver server for silent output.
         *
         * @param silent true for silent output, false otherwise.
         * @return A self reference.
         */
        public Builder withSilent(boolean silent) {
            this.silent = silent;
            return this;
        }

        /**
         * Creates a new {@link FlaNiumDriverService} to manage the FlaNium Desktop Driver server.
         * Before creating a new service, the builder will find a port for the server to listen to.
         *
         * @return The new {@link FlaNiumDriverService} object.
         */
        public FlaNiumDriverService buildDesktopService() {
            int port = getPort();
            if (port == 0) {
                port = DEFAULT_PORT;
            }

            if (exe == null) {
                exe = findDesktopDriverExecutable();
            }

            try {
                return new FlaNiumDriverService(exe, port, createArgs(), ImmutableMap.<String, String>of());
            } catch (IOException e) {
                throw new WebDriverException(e);
            }
        }



        @Override
        protected File findDefaultExecutable() {
            return findDesktopDriverExecutable();
        }

        @Override
        protected ImmutableList<String> createArgs() {
            if (getLogFile() == null) {
                String logFilePath = System.getProperty(FLANIUM_DRIVER_LOG_PATH_PROPERTY);
                if (logFilePath != null) {
                    withLogFile(new File(logFilePath));
                }
            }

            ImmutableList.Builder<String> argsBuidler = new ImmutableList.Builder<String>();
            if (silent) {
                argsBuidler.add("--silent");
            }
            if (verbose) {
                argsBuidler.add("--verbose");
            }
            if (getLogFile() != null) {
                argsBuidler.add(String.format("--log-path=%s", getLogFile().getAbsolutePath()));
            }

            return argsBuidler.build();
        }

        @Override
        protected FlaNiumDriverService createDriverService(File exe, int port, ImmutableList<String> args,
                                                           ImmutableMap<String, String> environment) {
            try {
                return new FlaNiumDriverService(exe, port, args, environment);
            } catch (IOException e) {
                throw new WebDriverException(e);
            }
        }

        private File findDesktopDriverExecutable() {
            return findExecutable(DESKTOP_DRIVER_SERVICE_FILENAME, DESKTOP_DRIVER_EXE_PROPERTY,
                    DESKTOP_DRIVER_DOCS_URL, DESKTOP_DRIVER_DOWNLOAD_URL);
        }


    }
}
