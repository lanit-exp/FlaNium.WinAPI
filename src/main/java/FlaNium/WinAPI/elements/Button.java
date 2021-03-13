package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class Button extends DesktopElement {

    private static final String BUTTON_INVOKE = "buttonInvoke";

    public Button(WebElement element) {
        super(element);
    }

    /**
     * Invokes the element.
     */
    public void invoke(){
        Response response = callVoidCommand(BUTTON_INVOKE);
    }
}
