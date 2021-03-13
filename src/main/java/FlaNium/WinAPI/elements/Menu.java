package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class Menu extends DesktopElement {

    private static final String MENU_ITEMS = "menuItems";

    public Menu(WebElement element) {
        super(element);
    }

    /**
     * Gets all MenuItem which are inside this element.
     * @return
     */
    public List<MenuItem> items() {
        Response response = callVoidCommand(MENU_ITEMS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(MenuItem::new)
                .collect(Collectors.toList());
    }
}
