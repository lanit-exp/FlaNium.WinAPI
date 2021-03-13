package FlaNium.WinAPI.webdriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class FlaNiumDriver extends RemoteWebDriver {

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} class using the specified options
     * @param options Thre {@link FlaNiumOptions} to be used with the FlaNium driver.
     */
    public FlaNiumDriver(FlaNiumOptions options) {
        this(createDefaultService(options.getClass()), options);
    }

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
     * @param service The {@link FlaNiumDriverService} to use.
     * @param dc The {@link DesiredCapabilities} used to initialize the driver.
     */
    public FlaNiumDriver(FlaNiumDriverService service, DesiredCapabilities dc) {
        super(new FlaNiumDriverCommandExecutor(service), dc);
    }

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} lass using the specified remote address and options.
     * @param remoteAddress URL containing the address of the FlaNiumDriver remote server.
     * @param options The {@link FlaNiumOptions} object to be used with the FlaNium driver.
     */
    public FlaNiumDriver(URL remoteAddress, FlaNiumOptions options) {
        super(new FlaNiumDriverCommandExecutor(remoteAddress), options.toCapabilities());
    }

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} lass using the specified remote address and options.
     * @param remoteAddress URL containing the address of the FlaNiumDriver remote server.
     * @param dc The {@link DesiredCapabilities} object to be used with the FlaNium driver.
     */
    public FlaNiumDriver(URL remoteAddress, DesiredCapabilities dc) {
        super(new FlaNiumDriverCommandExecutor(remoteAddress), dc);
    }


    private static FlaNiumDriverService createDefaultService(Class<? extends FlaNiumOptions> optionsType) {
        if (optionsType == DesktopOptions.class)
            return FlaNiumDriverService.createDesktopService();

        throw new IllegalArgumentException(
                "Option type must be type of DesktopOptions");
    }
}
