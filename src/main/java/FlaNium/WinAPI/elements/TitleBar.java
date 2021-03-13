package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class TitleBar extends DesktopElement {

    private static final String TITLE_BAR_MINIMIZE_BUTTON = "titleBarMinimizeButton";
    private static final String TITLE_BAR_MAXIMIZE_BUTTON = "titleBarMaximizeButton";
    private static final String TITLE_BAR_RESTORE_BUTTON = "titleBarRestoreButton";
    private static final String TITLE_BAR_CLOSE_BUTTON = "titleBarCloseButton";

    public TitleBar(WebElement element) {
        super(element);
    }

    /**
     * Gets the minimize button element.
     * @return
     */
    public Button minimizeButton(){
        Response response = callVoidCommand(TITLE_BAR_MINIMIZE_BUTTON);
        if (response == null) return null;
        return new Button(createRemoteWebElementFromResponse(response));
    }

    /**
     * Gets the maximize button element.
     * @return
     */
    public Button maximizeButton(){
        Response response = callVoidCommand(TITLE_BAR_MAXIMIZE_BUTTON);
        if (response == null) return null;
        return new Button(createRemoteWebElementFromResponse(response));
    }

    /**
     * Gets the restore button element.
     * @return
     */
    public Button restoreButton(){
        Response response = callVoidCommand(TITLE_BAR_RESTORE_BUTTON);
        if (response == null) return null;
        return new Button(createRemoteWebElementFromResponse(response));
    }

    /**
     * Gets the close button element.
     * @return
     */
    public Button closeButton(){
        Response response = callVoidCommand(TITLE_BAR_CLOSE_BUTTON);
        if (response == null) return null;
        return new Button(createRemoteWebElementFromResponse(response));
    }
}
