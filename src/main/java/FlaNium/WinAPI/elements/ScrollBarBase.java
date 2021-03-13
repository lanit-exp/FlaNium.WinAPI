package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class ScrollBarBase extends DesktopElement {

    private static final String SCROLL_BAR_BASE_VALUE = "scrollBarBaseValue";
    private static final String SCROLL_BAR_BASE_MINIMUM_VALUE = "scrollBarBaseMinimumValue";
    private static final String SCROLL_BAR_BASE_MAXIMUM_VALUE = "scrollBarBaseMaximumValue";
    private static final String SCROLL_BAR_BASE_SMALL_CHANGE = "scrollBarBaseSmallChange";
    private static final String SCROLL_BAR_BASE_LARGE_CHANGE = "scrollBarBaseLargeChange";
    private static final String SCROLL_BAR_BASE_IS_READ_ONLY = "scrollBarBaseIsReadOnly";


    protected ScrollBarBase(WebElement element) {
        super(element);
    }

    /**
     * The current value of the scroll.
     * @return
     */
    public double value(){
        Response response = callVoidCommand(SCROLL_BAR_BASE_VALUE);
        return parseDouble(response);
    }

    /**
     * The minimum value of the scroll.
     * @return
     */
    public double minimumValue(){
        Response response = callVoidCommand(SCROLL_BAR_BASE_MINIMUM_VALUE);
        return parseDouble(response);
    }

    /**
     * The maximum value of the scroll.
     * @return
     */
    public double maximumValue(){
        Response response = callVoidCommand(SCROLL_BAR_BASE_MAXIMUM_VALUE);
        return parseDouble(response);
    }

    /**
     * The small change value of the scroll.
     * @return
     */
    public double smallChange(){
        Response response = callVoidCommand(SCROLL_BAR_BASE_SMALL_CHANGE);
        return parseDouble(response);
    }

    /**
     * The large change value of the scroll.
     * @return
     */
    public double largeChange(){
        Response response = callVoidCommand(SCROLL_BAR_BASE_LARGE_CHANGE);
        return parseDouble(response);
    }

    /**
     * Value which indicates if the scroll is read only.
     * @return
     */
    public boolean isReadOnly(){
        Response response = callVoidCommand(SCROLL_BAR_BASE_IS_READ_ONLY);
        return Boolean.parseBoolean(response.getValue().toString());
    }



}
