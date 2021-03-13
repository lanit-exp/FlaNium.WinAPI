package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class ListBoxItem extends DesktopElement {

    private static final String LIST_BOX_ITEM_SCROLL_INTO_VIEW = "listBoxItemScrollIntoView";
    private static final String LIST_BOX_ITEM_IS_CHECKED = "listBoxItemIsChecked";
    private static final String LIST_BOX_ITEM_SET_CHECKED = "listBoxItemSetChecked";


    public ListBoxItem(WebElement element) {
        super(element);
    }

    /**
     * Scrolls the element into view.
     * @return
     */
    public ListBoxItem scrollIntoView(){
        Response response = callVoidCommand(LIST_BOX_ITEM_SCROLL_INTO_VIEW);
        if (response == null) return null;
        return new ListBoxItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Gets if the listbox item is checked, if checking is supported
     * @return
     */
    public boolean isChecked(){
        Response response = callVoidCommand(LIST_BOX_ITEM_IS_CHECKED);
        return Boolean.getBoolean(response.getValue().toString());
    }

    /**
     * Sets if the listbox item is checked, if checking is supported
     * @param state
     */
    public void setChecked(boolean state){
        Response response = callValueCommand(LIST_BOX_ITEM_SET_CHECKED, String.valueOf(state));
    }


}
