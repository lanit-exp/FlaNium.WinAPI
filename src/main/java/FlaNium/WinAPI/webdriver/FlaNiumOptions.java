package FlaNium.WinAPI.webdriver;
import org.openqa.selenium.Capabilities;

/**
 * Defines the interface to manage options specific to {@link FlaNiumDriver}
 */
public interface FlaNiumOptions {
    /**
     * Convert options to DesiredCapabilities for one of FlaNium Drivers
     * @return The DesiredCapabilities for FlaNium Driver with these options.
     */
    Capabilities toCapabilities();
}
