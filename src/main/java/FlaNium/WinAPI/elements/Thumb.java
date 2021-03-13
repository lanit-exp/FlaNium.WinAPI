package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class Thumb extends DesktopElement {

    private static final String THUMB_SLIDE_HORIZONTALLY = "thumbSlideHorizontally";
    private static final String THUMB_SLIDE_VERTICALLY = "thumbSlideVertically";

    public Thumb(WebElement element) {
        super(element);
    }

    /**
     * Moves the slider horizontally.
     * @param distance The distance to move the slider, + for right, - for left.
     */
    public void slideHorizontally (int distance){
        Response response = callValueCommand(THUMB_SLIDE_HORIZONTALLY, distance);
    }

    /**
     * Moves the slider vertically.
     * @param distance The distance to move the slider, + for down, - for up.
     */
    public void slideVertically (int distance){
        Response response = callValueCommand(THUMB_SLIDE_VERTICALLY, distance);
    }
}
