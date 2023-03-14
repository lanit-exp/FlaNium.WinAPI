package FlaNium.WinAPI.webdriver;

import FlaNium.WinAPI.exceptions.FlaNiumDriverException;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.internal.Require;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;


public class FlaNiumDriverService extends DriverService {


    protected FlaNiumDriverService(File executable, int port, Duration timeout, List<String> args,
                                   Map<String, String> environment) throws IOException {
        super(executable, port, timeout, args, environment);
    }


    public static class Builder extends DriverService.Builder<FlaNiumDriverService, Builder> {

        private File exe = null;
        private int port = 9999;
        private boolean verbose = false;
        private boolean silent = false;
        private Duration timeout = Duration.ofSeconds(20);
        private File logFile = null;


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
         * Sets which port the driver server should be started on. A value of 0 indicates that any free port may be used.
         *
         * @param port The port to use; must be non-negative.
         * @return A self reference.
         */
        @Override
        public Builder usingPort(int port) {
            this.port = Require.nonNegative("Port number", port);
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
         * Configures the timeout waiting for driver server to start.
         *
         * @param timeout Timeout waiting for driver server to start.
         * @return A self reference.
         */
        public Builder withTimeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        /**
         * Configures the driver server to write log to the given file.
         *
         * @param logFile A file to write log to.
         * @return A self reference.
         */

        @Override
        public Builder withLogFile(File logFile) {
            this.logFile = logFile;
            return this;
        }

        /**
         * Creates a new {@link FlaNiumDriverService} to manage the FlaNium Desktop Driver server.
         * Before creating a new service, the builder will find a port for the server to listen to.
         *
         * @return The new {@link FlaNiumDriverService} object.
         */
        @Override
        public FlaNiumDriverService build() {

            if (exe == null)
                throw new FlaNiumDriverException("Not set driver executable. Use method usingDriverExecutable() in Builder.");

            if (port == 0) port = PortProber.findFreePort();

            return createDriverService(exe, port, timeout, createArgs(), ImmutableMap.of());
        }


        @Override
        protected File findDefaultExecutable() {
            return null;
        }

        @Override
        protected List<String> createArgs() {

            List<String> args = new ArrayList<>();

            if (silent) args.add("--silent");

            if (verbose) args.add("--verbose");

            if (logFile != null) args.add(String.format("--log-path=%s", logFile.getAbsolutePath()));

            args.add(String.format("--port=%d", port));

            return args;
        }


        @Override
        protected FlaNiumDriverService createDriverService(File exe, int port, Duration timeout, List<String> args,
                                                           Map<String, String> environment) {
            try {
                return new FlaNiumDriverService(exe, port, timeout, args, environment);
            } catch (IOException e) {
                throw new FlaNiumDriverException(e);
            }
        }


    }
}
