package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class Window extends DesktopElement {

    private static final String WINDOW_TITLE = "windowTitle";
    private static final String WINDOW_IS_MODAL = "windowIsModal";
    private static final String WINDOW_TITLE_BAR = "windowTitleBar";
    private static final String WINDOW_MODAL_WINDOWS = "windowModalWindows";
    private static final String WINDOW_POPUP = "windowPopup";
    private static final String WINDOW_CONTEXT_MENU = "windowContextMenu";
    private static final String WINDOW_CLOSE = "windowClose";
    private static final String WINDOW_MOVE = "windowMove";
    private static final String WINDOW_SET_TRANSPARENCY = "windowSetTransparency";

    public Window(WebElement element) {
        super(element);
    }

    /**
     * Gets the title of the window.
     *
     * @return
     */
    public String title() {
        Response response = callVoidCommand(WINDOW_TITLE);
        return response.getValue().toString();
    }

    /**
     * Gets if the window is modal.
     *
     * @return
     */
    public boolean isModal() {
        Response response = callVoidCommand(WINDOW_IS_MODAL);
        return Boolean.parseBoolean(response.getValue().toString());
    }

    /**
     * Gets the "TitleBar" of the window.
     *
     * @return
     */
    public TitleBar titleBar() {
        Response response = callVoidCommand(WINDOW_TITLE_BAR);
        if (response == null) return null;
        return new TitleBar(createRemoteWebElementFromResponse(response));
    }

    /**
     * Gets a list of all modal child windows.
     *
     * @return
     */
    public List<Window> modalWindows() {
        Response response = callVoidCommand(WINDOW_MODAL_WINDOWS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(Window::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets the current WPF popup window.
     *
     * @return
     */
    public TitleBar popup() {
        Response response = callVoidCommand(WINDOW_POPUP);
        if (response == null) return null;
        return new TitleBar(createRemoteWebElementFromResponse(response));
    }

    /**
     * Gets the context menu for the window.
     * Note: It uses the FrameworkType of the window as lookup logic. Use "GetContextMenuByFrameworkType" if you want to control this.
     *
     * @return
     */
    public Menu contextMenu() {
        Response response = callVoidCommand(WINDOW_CONTEXT_MENU);
        if (response == null) return null;
        return new Menu(createRemoteWebElementFromResponse(response));
    }

    /**
     * Closes the window.
     */
    public void close() {
        Response response = callVoidCommand(WINDOW_CLOSE);
    }

    /**
     * Moves the window to the given coordinates.
     * @param x
     * @param y
     */
    public void move(int x, int y) {
        Response response = callValueCommand(WINDOW_MOVE, x, y);
    }

    /**
     * Brings the element to the foreground.
     * @param alpha
     */
    public void setTransparency(byte alpha) {
        Response response = callValueCommand(WINDOW_SET_TRANSPARENCY, alpha);
    }
}
