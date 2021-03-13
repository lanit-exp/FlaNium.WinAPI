package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class MenuItem extends DesktopElement {

    private static final String MENU_ITEM_ITEMS = "menuItemItems";
    private static final String MENU_ITEM_INVOKE = "menuItemInvoke";
    private static final String MENU_ITEM_EXPAND = "menuItemExpand";
    private static final String MENU_ITEM_COLLAPSE = "menuItemCollapse";
    private static final String MENU_ITEM_IS_CHECKED = "menuItemIsChecked";

    public MenuItem(WebElement element) {
        super(element);
    }

    /**
     *  Gets all MenuItem which are inside this element.
     * @return
     */
    public List<MenuItem> items() {
        Response response = callVoidCommand(MENU_ITEM_ITEMS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(MenuItem::new)
                .collect(Collectors.toList());
    }

    /**
     * Invokes the element.
     * @return
     */
    public MenuItem invoke() {
        Response response = callVoidCommand(MENU_ITEM_INVOKE);
        if (response == null) return null;
        return new MenuItem(createRemoteWebElementFromResponse(response));
    }

    public MenuItem expand() {
        Response response = callVoidCommand(MENU_ITEM_EXPAND);
        if (response == null) return null;
        return new MenuItem(createRemoteWebElementFromResponse(response));
    }

    public MenuItem collapse() {
        Response response = callVoidCommand(MENU_ITEM_COLLAPSE);
        if (response == null) return null;
        return new MenuItem(createRemoteWebElementFromResponse(response));
    }

    public boolean isChecked() {
        Response response = callVoidCommand(MENU_ITEM_IS_CHECKED);
        return Boolean.parseBoolean(response.getValue().toString());
    }
}
