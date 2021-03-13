package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class ProgressBar extends DesktopElement {

    private static final String PROGRESS_BAR_MINIMUM = "progressBarMinimum";
    private static final String PROGRESS_BAR_MAXIMUM = "progressBarMaximum";
    private static final String PROGRESS_BAR_VALUE = "progressBarValue";


    public ProgressBar(WebElement element) {
        super(element);
    }

    /**
     * Gets the minimum value.
     * @return
     */
    public double minimum(){
        Response response = callVoidCommand(PROGRESS_BAR_MINIMUM);
        return parseDouble(response);
    }

    /**
     * Gets the maximum value.
     * @return
     */
    public double maximum(){
        Response response = callVoidCommand(PROGRESS_BAR_MAXIMUM);
        return parseDouble(response);
    }

    /**
     * Gets the current value.
     * @return
     */
    public double value(){
        Response response = callVoidCommand(PROGRESS_BAR_VALUE);
        return parseDouble(response);
    }
}
