package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class Slider extends DesktopElement {

    private static final String SLIDER_MINIMUM = "sliderMinimum";
    private static final String SLIDER_MAXIMUM = "sliderMaximum";
    private static final String SLIDER_SMALL_CHANGE = "sliderSmallChange";
    private static final String SLIDER_LARGE_CHANGE = "sliderLargeChange";
    private static final String SLIDER_GET_LARGE_INCREASE_BUTTON = "sliderGetLargeIncreaseButton";
    private static final String SLIDER_GET_LARGE_DECREASE_BUTTON = "sliderGetLargeDecreaseButton";
    private static final String SLIDER_GET_THUMB = "sliderGetThumb";
    private static final String SLIDER_IS_ONLY_VALUE = "sliderIsOnlyValue";
    private static final String SLIDER_GET_VALUE = "sliderGetValue";
    private static final String SLIDER_SET_VALUE = "sliderSetValue";
    private static final String SLIDER_SMALL_INCREMENT = "sliderSmallIncrement";
    private static final String SLIDER_SMALL_DECREMENT = "sliderSmallDecrement";
    private static final String SLIDER_LARGE_INCREMENT = "sliderLargeIncrement";
    private static final String SLIDER_LARGE_DECREMENT = "sliderLargeDecrement";


    public Slider(WebElement element) {
        super(element);
    }

    /**
     * @return The minimum value.
     */
    public double minimum(){
        Response response = callVoidCommand(SLIDER_MINIMUM);
        return Double.parseDouble(response.getValue().toString());
    }

    /**
     * @return The maximum value.
     */
    public double maximum(){
        Response response = callVoidCommand(SLIDER_MAXIMUM);
        return Double.parseDouble(response.getValue().toString());
    }

    /**
     * @return The value of a small change.
     */
    public double smallChange(){
        Response response = callVoidCommand(SLIDER_SMALL_CHANGE);
        return Double.parseDouble(response.getValue().toString());
    }

    /**
     * @return The value of a large change.
     */
    public double largeChange(){
        Response response = callVoidCommand(SLIDER_LARGE_CHANGE);
        return Double.parseDouble(response.getValue().toString());
    }

    /**
     *
     * @return The button element used to perform a large increment.
     */
    public Button getLargeIncreaseButton(){
        Response response = callVoidCommand(SLIDER_GET_LARGE_INCREASE_BUTTON);
        if (response == null) return null;
        return new Button(createRemoteWebElementFromResponse(response));
    }

    /**
     *
     * @return The button element used to perform a large decrement.
     */
    public Button getLargeDecreaseButton(){
        Response response = callVoidCommand(SLIDER_GET_LARGE_DECREASE_BUTTON);
        if (response == null) return null;
        return new Button(createRemoteWebElementFromResponse(response));
    }

    /**
     * @return The element used to drag.
     */
    public Thumb getThumb(){
        Response response = callVoidCommand(SLIDER_GET_THUMB);
        if (response == null) return null;
        return new Thumb(createRemoteWebElementFromResponse(response));
    }

    /**
     * Flag which indicates if the "Slider" supports range values (min-max) or only values (0-100).
     * Only values are for example used when combining UIA3 and WinForms applications.
     * @return
     */
    public boolean isOnlyValue(){
        Response response = callVoidCommand(SLIDER_IS_ONLY_VALUE);
        return Boolean.parseBoolean(response.getValue().toString());
    }

    /**
     * Gets the current value.
     * @return
     */
    public double getValue(){
        Response response = callVoidCommand(SLIDER_GET_VALUE);
        return parseDouble(response);
    }

    /**
     * Sets the current value.
     * @param value
     */
    public void setValue(double value){
        Response response = callValueCommand(SLIDER_SET_VALUE, String.valueOf(value));
    }

    /**
     * Performs a small increment.
     */
    public void smallIncrement(){
        Response response = callVoidCommand(SLIDER_SMALL_INCREMENT);
    }

    /**
     * Performs a small decrement.
     */
    public void smallDecrement(){
        Response response = callVoidCommand(SLIDER_SMALL_DECREMENT);
    }

    /**
     * Performs a large increment.
     */
    public void largeIncrement(){
        Response response = callVoidCommand(SLIDER_LARGE_INCREMENT);
    }

    /**
     * Performs a large decrement.
     */
    public void largeDecrement(){
        Response response = callVoidCommand(SLIDER_LARGE_DECREMENT);
    }
}
