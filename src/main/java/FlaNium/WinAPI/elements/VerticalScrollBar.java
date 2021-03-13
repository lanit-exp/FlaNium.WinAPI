package FlaNium.WinAPI.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class VerticalScrollBar extends ScrollBarBase {

    private static final String VERTICAL_SCROLL_BAR_SCROLL_UP = "verticalScrollBarScrollUp";
    private static final String VERTICAL_SCROLL_BAR_SCROLL_DOWN = "verticalScrollBarScrollDown";
    private static final String VERTICAL_SCROLL_BAR_SCROLL_UP_LARGE = "verticalScrollBarScrollUpLarge";
    private static final String VERTICAL_SCROLL_BAR_SCROLL_DOWN_LARGE = "verticalScrollBarScrollDownLarge";

    public VerticalScrollBar(WebElement element) {
        super(element);
    }

    /**
     * Scrolls up by a small amount.
     */
    public void scrollUp(){
        Response response = callVoidCommand(VERTICAL_SCROLL_BAR_SCROLL_UP);
    }

    /**
     * Scrolls down by a small amount.
     */
    public void scrollDown(){
        Response response = callVoidCommand(VERTICAL_SCROLL_BAR_SCROLL_DOWN);
    }

    /**
     * Scrolls up by a large amount.
     */
    public void scrollUpLarge(){
        Response response = callVoidCommand(VERTICAL_SCROLL_BAR_SCROLL_UP_LARGE);
    }

    /**
     * Scrolls down by a large amount.
     */
    public void scrollDownLarge(){
        Response response = callVoidCommand(VERTICAL_SCROLL_BAR_SCROLL_DOWN_LARGE);
    }
}
