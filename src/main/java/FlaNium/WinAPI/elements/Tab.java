package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class Tab extends DesktopElement {

    private  static final String TAB_SELECTED_TAB_ITEM = "tabSelectedTabItem";
    private  static final String TAB_SELECTED_TAB_ITEM_INDEX = "tabSelectedTabItemIndex";
    private  static final String TAB_TAB_ITEMS = "tabTabItems";
    private  static final String TAB_SELECT_TAB_ITEM_INDEX = "tabSelectTabItemIndex";
    private  static final String TAB_SELECT_TAB_ITEM_TEXT = "tabSelectTabItemText";

    public Tab(WebElement element) {
        super(element);
    }

    /**
     * The currently selected TabItem.
     * @return
     */
    public TabItem selectedTabItem(){
        Response response = callVoidCommand(TAB_SELECTED_TAB_ITEM);
        if (response == null) return null;
        return new TabItem(createRemoteWebElementFromResponse(response));
    }

    /**
     *  The index of the currently selected TabItem.
     * @return
     */
    public int selectedTabItemIndex(){
        Response response = callVoidCommand(TAB_SELECTED_TAB_ITEM_INDEX);
        return Integer.parseInt(response.getValue().toString());
    }

    /**
     * All TabItem objects from this Tab.
     * @return
     */
    public List<TabItem> tabItems() {
        Response response = callVoidCommand(TAB_TAB_ITEMS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(TabItem::new)
                .collect(Collectors.toList());
    }

    /**
     * Selects a TabItem by index.
     * @param index
     * @return
     */
    public TabItem selectTabItem(int index){
        Response response = callValueCommand(TAB_SELECT_TAB_ITEM_INDEX,index);
        if (response == null) return null;
        return new TabItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Selects a TabItem by a give text (name property).
     * @param text
     * @return
     */
    public TabItem selectTabItem(String text){
        Response response = callValueCommand(TAB_SELECT_TAB_ITEM_TEXT,text);
        if (response == null) return null;
        return new TabItem(createRemoteWebElementFromResponse(response));
    }

}
