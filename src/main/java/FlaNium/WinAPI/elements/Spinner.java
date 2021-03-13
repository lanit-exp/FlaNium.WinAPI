package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class Spinner extends DesktopElement {

    private static final String SPINNER_MINIMUM = "spinnerMinimum";
    private static final String SPINNER_MAXIMUM = "spinnerMaximum";
    private static final String SPINNER_SMALL_CHANGE = "spinnerSmallChange";
    private static final String SPINNER_IS_ONLY_VALUE = "spinnerIsOnlyValue";
    private static final String SPINNER_GET_VALUE = "spinnerGetValue";
    private static final String SPINNER_SET_VALUE = "spinnerSetValue";
    private static final String SPINNER_INCREMENT = "spinnerIncrement";
    private static final String SPINNER_DECREMENT = "spinnerDecrement";

    public Spinner(WebElement element) {
        super(element);
    }

    /**
     * The minimum value.
     * @return
     */
    public double minimum(){
        Response response = callVoidCommand(SPINNER_MINIMUM);
        return Double.parseDouble(response.getValue().toString());
    }

    /**
     * The maximum value.
     * @return
     */
    public double maximum(){
        Response response = callVoidCommand(SPINNER_MAXIMUM);
        return Double.parseDouble(response.getValue().toString());
    }

    /**
     * The value of a small change.
     * @return
     */
    public double smallChange(){
        Response response = callVoidCommand(SPINNER_SMALL_CHANGE);
        return Double.parseDouble(response.getValue().toString());
    }

    /**
     * Flag which indicates if the "Spinner" supports range values (min-max) or only values (0-100).
     * Only values are for example used when combining UIA3 and WinForms applications.
     * @return
     */
    public boolean isOnlyValue(){
        Response response = callVoidCommand(SPINNER_IS_ONLY_VALUE);
        return Boolean.parseBoolean(response.getValue().toString());
    }

    /**
     * Gets the current value.
     * @return
     */
    public double getValue(){
        Response response = callVoidCommand(SPINNER_GET_VALUE);
        return Double.parseDouble(response.getValue().toString());
    }

    /**
     * Sets the current value.
     * @param value
     */
    public void setValue(double value){
        Response response = callValueCommand(SPINNER_SET_VALUE, String.valueOf(value));
    }

    /**
     * Performs increment.
     */
    public void increment(){
        Response response = callVoidCommand(SPINNER_INCREMENT);
    }

    /**
     * Performs decrement.
     */
    public void decrement(){
        Response response = callVoidCommand(SPINNER_DECREMENT);
    }
}
