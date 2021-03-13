package FlaNium.WinAPI.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class HorizontalScrollBar extends ScrollBarBase {

    private static final String HORIZONTAL_SCROLL_BAR_SCROLL_LEFT = "horizontalScrollBarScrollLeft";
    private static final String HORIZONTAL_SCROLL_BAR_SCROLL_RIGHT = "horizontalScrollBarScrollRight";
    private static final String HORIZONTAL_SCROLL_BAR_SCROLL_LEFT_LARGE = "horizontalScrollBarScrollLeftLarge";
    private static final String HORIZONTAL_SCROLL_BAR_SCROLL_RIGHT_LARGE = "horizontalScrollBarScrollRightLarge";

    public HorizontalScrollBar(WebElement element) {
        super(element);
    }

    /**
     * Scrolls left by a small amount.
     */
    public void scrollLeft(){
        Response response = callVoidCommand(HORIZONTAL_SCROLL_BAR_SCROLL_LEFT);
    }

    /**
     * Scrolls right by a small amount.
     */
    public void scrollRight(){
        Response response = callVoidCommand(HORIZONTAL_SCROLL_BAR_SCROLL_RIGHT);
    }

    /**
     * Scrolls left by a large amount.
     */
    public void scrollLeftLarge(){
        Response response = callVoidCommand(HORIZONTAL_SCROLL_BAR_SCROLL_LEFT_LARGE);
    }

    /**
     * Scrolls right by a large amount.
     */
    public void scrollRightLarge(){
        Response response = callVoidCommand(HORIZONTAL_SCROLL_BAR_SCROLL_RIGHT_LARGE);
    }
}
