package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class TabItem extends DesktopElement {

    private static final String TAB_ITEM_SELECT = "tabItemSelect";
    private static final String TAB_ITEM_ADD_TO_SELECTION = "tabItemAddToSelection";
    private static final String TAB_ITEM_REMOVE_FROM_SELECTION = "tabItemRemoveFromSelection";

    public TabItem(WebElement element) {
        super(element);
    }

    /**
     * Selects the element.
     */
    public void select(){
        Response response = callVoidCommand(TAB_ITEM_SELECT);
    }

    /**
     * Adds the element to the selection.
     */
    public void addToSelection(){
        Response response = callVoidCommand(TAB_ITEM_ADD_TO_SELECTION);
    }

    /**
     * Removes the element from the selection.
     */
    public void removeFromSelection(){
        Response response = callVoidCommand(TAB_ITEM_REMOVE_FROM_SELECTION);
    }
}
