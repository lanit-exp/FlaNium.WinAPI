package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.elements.enums.ExpandCollapseState;
import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class TreeItem extends DesktopElement {

    public static final String TREE_ITEM_ITEMS = "treeItemItems";
    public static final String TREE_ITEM_GET_TEXT = "treeItemGetText";
    public static final String TREE_ITEM_EXPAND_COLLAPSE_STATE = "treeItemExpandCollapseState";
    public static final String TREE_ITEM_EXPAND = "treeItemExpand";
    public static final String TREE_ITEM_COLLAPSE = "treeItemCollapse";
    public static final String TREE_ITEM_SELECT = "treeItemSelect";
    public static final String TREE_ITEM_ADD_TO_SELECTION = "treeItemAddToSelection";
    public static final String TREE_ITEM_REMOVE_FROM_SELECTION = "treeItemRemoveFromSelection";
    public static final String TREE_ITEM_IS_CHECKED = "treeItemIsChecked";
    public static final String TREE_ITEM_SET_CHECKED = "treeItemSetChecked";

    public TreeItem(WebElement element) {
        super(element);
    }

    /**
     * All child "TreeItem" objects from this"TreeItem".
     *
     * @return
     */
    public List<TreeItem> items() {
        Response response = callVoidCommand(TREE_ITEM_ITEMS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(TreeItem::new)
                .collect(Collectors.toList());
    }

    /**
     * The text of the "TreeItem".
     *
     * @return
     */
    public String getText() {
        Response response = callVoidCommand(TREE_ITEM_GET_TEXT);
        return response.getValue().toString();
    }

    /**
     * Gets the current expand / collapse state.
     *
     * @return
     */
    public ExpandCollapseState expandCollapseState() {
        Response response = callVoidCommand(TREE_ITEM_EXPAND_COLLAPSE_STATE);
        return ExpandCollapseState.getEnum(response.getValue().toString());
    }

    /**
     * Expands the element.
     */
    public void expand() {
        callVoidCommand(TREE_ITEM_EXPAND);
    }


    /**
     * Collapses the element.
     */
    public void collapse() {
        callVoidCommand(TREE_ITEM_COLLAPSE);
    }

    /**
     * Selects the element.
     */
    public void select() {
        callVoidCommand(TREE_ITEM_SELECT);
    }

    /**
     * Add the element to the selection.
     * @return
     */
    public TreeItem addToSelection(){
        Response response = callVoidCommand(TREE_ITEM_ADD_TO_SELECTION);
        if (response == null) return null;
        return new TreeItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Remove the element from the selection.
     * @return
     */
    public TreeItem removeFromSelection(){
        Response response = callVoidCommand(TREE_ITEM_REMOVE_FROM_SELECTION);
        if (response == null) return null;
        return new TreeItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Gets if the tree item is checked, if checking is supported.
     * @return
     */
    public boolean isChecked() {
        Response response = callVoidCommand(TREE_ITEM_IS_CHECKED);
        return Boolean.parseBoolean(response.getValue().toString());
    }

    /**
     * Sets if the tree item is checked, if checking is supported.
     * @param checked
     */
    public void setChecked(boolean checked) {
        Response response = callValueCommand(TREE_ITEM_SET_CHECKED,String.valueOf(checked));
    }
}
