package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class ListBox extends DesktopElement {

    private static final String LIST_BOX_ITEMS = "listBoxItems";
    private static final String LIST_BOX_SELECTED_ITEMS = "listBoxSelectedItems";
    private static final String LIST_BOX_SELECTED_ITEM = "listBoxSelectedItem";
    private static final String LIST_BOX_SELECT_INDEX = "listBoxSelectIndex";
    private static final String LIST_BOX_SELECT_TEXT = "listBoxSelectText";
    private static final String LIST_BOX_ADD_TO_SELECTION_INDEX = "listBoxAddToSelectionIndex";
    private static final String LIST_BOX_ADD_TO_SELECTION_TEXT = "listBoxAddToSelectionText";
    private static final String LIST_BOX_REMOVE_FROM_SELECTION_INDEX = "listBoxRemoveFromSelectionIndex";
    private static final String LIST_BOX_REMOVE_FROM_SELECTION_TEXT = "listBoxRemoveFromSelectionText";


    public ListBox(WebElement element) {
        super(element);
    }

    /**
     * Returns all the list box items
     * @return
     */
    public List<ListBoxItem> items() {
        Response response = callVoidCommand(LIST_BOX_ITEMS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(ListBoxItem::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets all selected items.
     * @return
     */
    public List<ListBoxItem> selectedItems() {
        Response response = callVoidCommand(LIST_BOX_SELECTED_ITEMS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(ListBoxItem::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets the first selected item or null otherwise.
     * @return
     */
    public ListBoxItem selectedItem() {
        Response response = callVoidCommand(LIST_BOX_SELECTED_ITEM);
        if (response == null) return null;
        return new ListBoxItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Selects an item by index.
     * @param index
     * @return
     */
    public ListBoxItem select(int index) {
        Response response = callValueCommand(LIST_BOX_SELECT_INDEX,index);
        if (response == null) return null;
        return new ListBoxItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Selects an item by text.
     * @param text
     * @return
     */
    public ListBoxItem select(String text) {
        Response response = callValueCommand(LIST_BOX_SELECT_TEXT,text);
        if (response == null) return null;
        return new ListBoxItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Add a row to the selection by index.
     * @param index
     * @return
     */
    public ListBoxItem addToSelection(int index) {
        Response response = callValueCommand(LIST_BOX_ADD_TO_SELECTION_INDEX,index);
        if (response == null) return null;
        return new ListBoxItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Add a row to the selection by text.
     * @param text
     * @return
     */
    public ListBoxItem addToSelection(String text) {
        Response response = callValueCommand(LIST_BOX_ADD_TO_SELECTION_TEXT,text);
        if (response == null) return null;
        return new ListBoxItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Remove a row from the selection by index.
     * @param index
     * @return
     */
    public ListBoxItem removeFromSelection(int index) {
        Response response = callValueCommand(LIST_BOX_REMOVE_FROM_SELECTION_INDEX,index);
        if (response == null) return null;
        return new ListBoxItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Remove a row from the selection by text.
     * @param text
     * @return
     */
    public ListBoxItem removeFromSelection(String text) {
        Response response = callValueCommand(LIST_BOX_REMOVE_FROM_SELECTION_TEXT,text);
        if (response == null) return null;
        return new ListBoxItem(createRemoteWebElementFromResponse(response));
    }
}
