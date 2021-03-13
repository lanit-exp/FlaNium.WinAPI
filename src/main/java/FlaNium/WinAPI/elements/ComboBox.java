package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import FlaNium.WinAPI.elements.enums.ExpandCollapseState;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class ComboBox extends DesktopElement {

    private static final String COMBO_BOX_COLLAPSE = "comboBoxCollapse";
    private static final String COMBO_BOX_EXPAND = "comboBoxExpand";
    private static final String COMBO_BOX_SELECT = "comboBoxSelect";
    private static final String COMBO_BOX_SELECT_INDEX = "comboBoxSelectIndex";
    private static final String COMBO_BOX_SET_EDITABLE_TEXT = "comboBoxSetEditableText";

    private static final String COMBO_BOX_IS_EDITABLE = "comboBoxIsEditable";
    private static final String COMBO_BOX_IS_READ_ONLY = "comboBoxIsReadOnly";
    private static final String COMBO_BOX_VALUE = "comboBoxValue";
    private static final String COMBO_BOX_SELECTED_ITEMS = "comboBoxSelectedItems";
    private static final String COMBO_BOX_SELECTED_ITEM = "comboBoxSelectedItem";
    private static final String COMBO_BOX_ITEMS = "comboBoxItems";
    private static final String COMBO_BOX_EXPAND_COLLAPSE_STATE = "comboBoxExpandCollapseState";
    private static final String COMBO_BOX_EDITABLE_TEXT = "comboBoxEditableText";


    public ComboBox(WebElement element) {
        super(element);
    }

    /**
     * Collapses the element.
     */
    public void collapse() {
        callVoidCommand(COMBO_BOX_COLLAPSE);
    }

    /**
     * Expands the element.
     */
    public void expand() {
        callVoidCommand(COMBO_BOX_EXPAND);
    }

    /**
     * Select the first item which matches the given text.
     *
     * @param value The text to search for.
     * @return The first found item or null if no item matches.
     */
    public ComboBoxItem select(String value) {
        Response response = callValueCommand(COMBO_BOX_SELECT, value);
        return new ComboBoxItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Select an item by index.
     *
     * @param index The index to search for.
     * @return The first found item or null if no item matches.
     */
    public ComboBoxItem selectIndex(int index) {
        Response response = callValueCommand(COMBO_BOX_SELECT_INDEX, String.valueOf(index));
        return new ComboBoxItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Set the text of the editable element inside the combobox.
     * Only works if the combobox is editable.
     */
    public void setEditableText(String value) {
        Response response = callValueCommand(COMBO_BOX_SET_EDITABLE_TEXT, value);
    }

    /**
     * Flag which indicates, if the combobox is editable or not.
     */
    public boolean isEditable() {
        Response response = callVoidCommand(COMBO_BOX_IS_EDITABLE);
        return Boolean.parseBoolean(response.getValue().toString());
    }

    /**
     * Flag which indicates, if the combobox is read-only or not.
     */
    public boolean isReadOnly() {
        Response response = callVoidCommand(COMBO_BOX_IS_READ_ONLY);
        return Boolean.parseBoolean(response.getValue().toString());
    }

    /**
     * @return Selected value.
     */
    public String value() {
        Response response = callVoidCommand(COMBO_BOX_VALUE);
        return response.getValue().toString();
    }

    /**
     * Gets the first selected item or null otherwise.
     */
    public ComboBoxItem selectedItem() {
        Response response = callVoidCommand(COMBO_BOX_SELECTED_ITEM);
        if (response == null) return null;
        return new ComboBoxItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Gets all selected items.
     */
    public List<ComboBoxItem> selectedItems() {
        Response response = callVoidCommand(COMBO_BOX_SELECTED_ITEMS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(ComboBoxItem::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets all items.
     */
    public List<ComboBoxItem> items() {
        Response response = callVoidCommand(COMBO_BOX_ITEMS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(ComboBoxItem::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets the ExpandCollapseState of the element.
     */
    public ExpandCollapseState expandCollapseState() {
        Response response = callVoidCommand(COMBO_BOX_EXPAND_COLLAPSE_STATE);
        return ExpandCollapseState.getEnum(response.getValue().toString());
    }

    /**
     * The text of the editable element inside the combobox.
     * Only works if the combobox is editable.
     */
    public String editableText() {
        Response response = callVoidCommand(COMBO_BOX_EDITABLE_TEXT);
        return response.getValue().toString();
    }


}