package FlaNium.WinAPI.webdriver;
import FlaNium.WinAPI.exceptions.FlaNiumDriverException;
import com.google.common.base.Throwables;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.remote.http.HttpMethod;
import org.openqa.selenium.remote.service.DriverCommandExecutor;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * {@link DriverCommandExecutor} that understands FlaNium Driver specific commands.
 */
public class FlaNiumDriverCommandExecutor extends HttpCommandExecutor {
    private static final Map<String, CommandInfo> FLANIUM_COMMAND_NAME_TO_URL;

    private final FlaNiumDriverService service;

    static {
        FLANIUM_COMMAND_NAME_TO_URL = new HashMap<String, CommandInfo>();

        FLANIUM_COMMAND_NAME_TO_URL.put("executeInApp",
                new CommandInfo("/session/:sessionId/executeInApp", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("elementAttribute",
                new CommandInfo("/session/:sessionId/element/:id/attribute/:value", HttpMethod.GET));

        //region ComboBox
        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxCollapse",
                new CommandInfo("/session/:sessionId/element/:id/combobox/collapse", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxExpand",
                new CommandInfo("/session/:sessionId/element/:id/combobox/expand", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxSelect",
                new CommandInfo("/session/:sessionId/element/:id/combobox/select/:value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxSelectIndex",
                new CommandInfo("/session/:sessionId/element/:id/combobox/selectIndex/:value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxSetEditableText",
                new CommandInfo("/session/:sessionId/element/:id/combobox/setEditableText/:value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxIsEditable",
                new CommandInfo("/session/:sessionId/element/:id/combobox/isEditable", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxIsReadOnly",
                new CommandInfo("/session/:sessionId/element/:id/combobox/isReadonly", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxValue",
                new CommandInfo("/session/:sessionId/element/:id/combobox/value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxSelectedItems",
                new CommandInfo("/session/:sessionId/element/:id/combobox/selectedItems", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxSelectedItem",
                new CommandInfo("/session/:sessionId/element/:id/combobox/selectedItem", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxItems",
                new CommandInfo("/session/:sessionId/element/:id/combobox/items", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxExpandCollapseState",
                new CommandInfo("/session/:sessionId/element/:id/combobox/expandCollapseState", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("comboBoxEditableText",
                new CommandInfo("/session/:sessionId/element/:id/combobox/editableText", HttpMethod.POST));
        //endregion

        //region CheckBox
        FLANIUM_COMMAND_NAME_TO_URL.put("checkBoxToggleState",
                new CommandInfo("/session/:sessionId/element/:id/checkbox/toggleState", HttpMethod.POST));
        //endregion

        //region Slider
        FLANIUM_COMMAND_NAME_TO_URL.put("sliderMinimum",
                new CommandInfo("/session/:sessionId/element/:id/slider/minimum", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderMaximum",
                new CommandInfo("/session/:sessionId/element/:id/slider/maximum", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderSmallChange",
                new CommandInfo("/session/:sessionId/element/:id/slider/smallChange", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderLargeChange",
                new CommandInfo("/session/:sessionId/element/:id/slider/largeChange", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderGetLargeIncreaseButton",
                new CommandInfo("/session/:sessionId/element/:id/slider/getLargeIncreaseButton", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderGetLargeDecreaseButton",
                new CommandInfo("/session/:sessionId/element/:id/slider/getLargeDecreaseButton", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderGetThumb",
                new CommandInfo("/session/:sessionId/element/:id/slider/getThumb", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderIsOnlyValue",
                new CommandInfo("/session/:sessionId/element/:id/slider/isOnlyValue", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderGetValue",
                new CommandInfo("/session/:sessionId/element/:id/slider/getValue", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderSetValue",
                new CommandInfo("/session/:sessionId/element/:id/slider/setValue/:value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderSmallIncrement",
                new CommandInfo("/session/:sessionId/element/:id/slider/smallIncrement", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderSmallDecrement",
                new CommandInfo("/session/:sessionId/element/:id/slider/smallDecrement", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderLargeIncrement",
                new CommandInfo("/session/:sessionId/element/:id/slider/largeIncrement", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sliderLargeDecrement",
                new CommandInfo("/session/:sessionId/element/:id/slider/largeDecrement", HttpMethod.POST));
        //endregion

        //region DataGridView
        FLANIUM_COMMAND_NAME_TO_URL.put("dataGridViewHasAddRow",
                new CommandInfo("/session/:sessionId/element/:id/dataGridView/hasAddRow", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("dataGridViewGetHeader",
                new CommandInfo("/session/:sessionId/element/:id/dataGridView/getHeader", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("dataGridViewGetRows",
                new CommandInfo("/session/:sessionId/element/:id/dataGridView/getRows", HttpMethod.POST));
        //endregion

        //region DataGridViewHeader
        FLANIUM_COMMAND_NAME_TO_URL.put("dataGridViewHeaderGetColumns",
                new CommandInfo("/session/:sessionId/element/:id/dataGridViewHeader/getColumns", HttpMethod.POST));
        //endregion

        //region DataGridViewRow
        FLANIUM_COMMAND_NAME_TO_URL.put("dataGridViewRowGetCells",
                new CommandInfo("/session/:sessionId/element/:id/dataGridViewRow/getCells", HttpMethod.POST));
        //endregion

        //region DataGridViewCell
        FLANIUM_COMMAND_NAME_TO_URL.put("dataGridViewCellGetValue",
                new CommandInfo("/session/:sessionId/element/:id/dataGridViewCell/getValue", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("dataGridViewCellSetValue",
                new CommandInfo("/session/:sessionId/element/:id/dataGridViewCell/setValue/:value", HttpMethod.POST));
        //endregion

        //region Grid
        FLANIUM_COMMAND_NAME_TO_URL.put("gridRowCount",
                new CommandInfo("/session/:sessionId/element/:id/grid/rowCount", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridColumnCount",
                new CommandInfo("/session/:sessionId/element/:id/grid/columnCount", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridColumnHeaders",
                new CommandInfo("/session/:sessionId/element/:id/grid/columnHeaders", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridRowHeaders",
                new CommandInfo("/session/:sessionId/element/:id/grid/rowHeaders", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridRowOrColumnMajor",
                new CommandInfo("/session/:sessionId/element/:id/grid/rowOrColumnMajor", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridGetHeader",
                new CommandInfo("/session/:sessionId/element/:id/grid/getHeader", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridGetRows",
                new CommandInfo("/session/:sessionId/element/:id/grid/getRows", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridSelectedItems",
                new CommandInfo("/session/:sessionId/element/:id/grid/selectedItems", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridSelectedItem",
                new CommandInfo("/session/:sessionId/element/:id/grid/selectedItem", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridSelect",
                new CommandInfo("/session/:sessionId/element/:id/grid/select/:index", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridSelectText",
                new CommandInfo("/session/:sessionId/element/:id/grid/selectText/:index/:text", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridAddToSelection",
                new CommandInfo("/session/:sessionId/element/:id/grid/addToSelection/:index", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridAddToSelectionText",
                new CommandInfo("/session/:sessionId/element/:id/grid/addToSelectionText/:index/:text", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridRemoveFromSelection",
                new CommandInfo("/session/:sessionId/element/:id/grid/removeFromSelection/:index", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridRemoveFromSelectionText",
                new CommandInfo("/session/:sessionId/element/:id/grid/removeFromSelectionText/:index/:text", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridGetRowByIndex",
                new CommandInfo("/session/:sessionId/element/:id/grid/getRowByIndex/:index", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridGetRowByValue",
                new CommandInfo("/session/:sessionId/element/:id/grid/getRowByValue/:index/:text", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridGetRowsByValue",
                new CommandInfo("/session/:sessionId/element/:id/grid/getRowsByValue/:index/:text/:count", HttpMethod.POST));
        //endregion

        //region GridCell
        FLANIUM_COMMAND_NAME_TO_URL.put("gridCellContainingGrid",
                new CommandInfo("/session/:sessionId/element/:id/gridCell/containingGrid", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridCellContainingRow",
                new CommandInfo("/session/:sessionId/element/:id/gridCell/containingRow", HttpMethod.POST));
        //endregion

        //region GridHeader
        FLANIUM_COMMAND_NAME_TO_URL.put("gridHeaderColumns",
                new CommandInfo("/session/:sessionId/element/:id/gridHeader/columns", HttpMethod.POST));
        //endregion

        //region GridRow
        FLANIUM_COMMAND_NAME_TO_URL.put("gridRowCells",
                new CommandInfo("/session/:sessionId/element/:id/gridRow/cells", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridRowHeader",
                new CommandInfo("/session/:sessionId/element/:id/gridRow/header", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridRowFindCellByText",
                new CommandInfo("/session/:sessionId/element/:id/gridRow/findCellByText/:value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("gridRowScrollIntoView",
                new CommandInfo("/session/:sessionId/element/:id/gridRow/scrollIntoView", HttpMethod.POST));
        //endregion

        //region ScrollBarBase
        FLANIUM_COMMAND_NAME_TO_URL.put("scrollBarBaseValue",
                new CommandInfo("/session/:sessionId/element/:id/scrollBarBase/value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("scrollBarBaseMinimumValue",
                new CommandInfo("/session/:sessionId/element/:id/scrollBarBase/minimumValue", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("scrollBarBaseMaximumValue",
                new CommandInfo("/session/:sessionId/element/:id/scrollBarBase/maximumValue", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("scrollBarBaseSmallChange",
                new CommandInfo("/session/:sessionId/element/:id/scrollBarBase/smallChange", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("scrollBarBaseLargeChange",
                new CommandInfo("/session/:sessionId/element/:id/scrollBarBase/largeChange", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("scrollBarBaseIsReadOnly",
                new CommandInfo("/session/:sessionId/element/:id/scrollBarBase/isReadOnly", HttpMethod.POST));
        //endregion

        //region HorizontalScrollBar
        FLANIUM_COMMAND_NAME_TO_URL.put("horizontalScrollBarScrollLeft",
                new CommandInfo("/session/:sessionId/element/:id/horizontalScrollBar/scrollLeft", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("horizontalScrollBarScrollRight",
                new CommandInfo("/session/:sessionId/element/:id/horizontalScrollBar/scrollRight", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("horizontalScrollBarScrollLeftLarge",
                new CommandInfo("/session/:sessionId/element/:id/horizontalScrollBar/scrollLeftLarge", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("horizontalScrollBarScrollRightLarge",
                new CommandInfo("/session/:sessionId/element/:id/horizontalScrollBar/scrollRightLarge", HttpMethod.POST));
        //endregion

        //region VerticalScrollBar
        FLANIUM_COMMAND_NAME_TO_URL.put("verticalScrollBarScrollUp",
                new CommandInfo("/session/:sessionId/element/:id/verticalScrollBar/scrollUp", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("verticalScrollBarScrollDown",
                new CommandInfo("/session/:sessionId/element/:id/verticalScrollBar/scrollDown", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("verticalScrollBarScrollUpLarge",
                new CommandInfo("/session/:sessionId/element/:id/verticalScrollBar/scrollUpLarge", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("verticalScrollBarScrollDownLarge",
                new CommandInfo("/session/:sessionId/element/:id/verticalScrollBar/scrollDownLarge", HttpMethod.POST));
        //endregion

        //region ProgressBar
        FLANIUM_COMMAND_NAME_TO_URL.put("progressBarMinimum",
                new CommandInfo("/session/:sessionId/element/:id/progressBar/minimum", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("progressBarMaximum",
                new CommandInfo("/session/:sessionId/element/:id/progressBar/maximum", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("progressBarValue",
                new CommandInfo("/session/:sessionId/element/:id/progressBar/value", HttpMethod.POST));
        //endregion

        //region ListBox
        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxItems",
                new CommandInfo("/session/:sessionId/element/:id/listBox/items", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxSelectedItems",
                new CommandInfo("/session/:sessionId/element/:id/listBox/selectedItems", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxSelectedItem",
                new CommandInfo("/session/:sessionId/element/:id/listBox/selectedItem", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxSelectIndex",
                new CommandInfo("/session/:sessionId/element/:id/listBox/selectIndex/:index", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxSelectText",
                new CommandInfo("/session/:sessionId/element/:id/listBox/selectText/:value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxAddToSelectionIndex",
                new CommandInfo("/session/:sessionId/element/:id/listBox/addToSelectionIndex/:index", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxAddToSelectionText",
                new CommandInfo("/session/:sessionId/element/:id/listBox/addToSelectionText/:value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxRemoveFromSelectionIndex",
                new CommandInfo("/session/:sessionId/element/:id/listBox/removeFromSelectionIndex/:index", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxRemoveFromSelectionText",
                new CommandInfo("/session/:sessionId/element/:id/listBox/removeFromSelectionText/:value", HttpMethod.POST));
        //endregion

        //region ListBoxItem
        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxItemScrollIntoView",
                new CommandInfo("/session/:sessionId/element/:id/listBoxItem/scrollIntoView", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxItemIsChecked",
                new CommandInfo("/session/:sessionId/element/:id/listBoxItem/isChecked", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("listBoxItemSetChecked",
                new CommandInfo("/session/:sessionId/element/:id/listBoxItem/setChecked/:value", HttpMethod.POST));
        //endregion

        //region Menu
        FLANIUM_COMMAND_NAME_TO_URL.put("menuItems",
                new CommandInfo("/session/:sessionId/element/:id/menu/items", HttpMethod.POST));
        //endregion

        //region MenuItem
        FLANIUM_COMMAND_NAME_TO_URL.put("menuItemItems",
                new CommandInfo("/session/:sessionId/element/:id/menuItem/items", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("menuItemInvoke",
                new CommandInfo("/session/:sessionId/element/:id/menuItem/invoke", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("menuItemExpand",
                new CommandInfo("/session/:sessionId/element/:id/menuItem/expand", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("menuItemCollapse",
                new CommandInfo("/session/:sessionId/element/:id/menuItem/collapse", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("menuItemIsChecked",
                new CommandInfo("/session/:sessionId/element/:id/menuItem/isChecked", HttpMethod.POST));
        //endregion

        //region Button
        FLANIUM_COMMAND_NAME_TO_URL.put("buttonInvoke",
                new CommandInfo("/session/:sessionId/element/:id/button/invoke", HttpMethod.POST));
        //endregion

        //region Spinner
        FLANIUM_COMMAND_NAME_TO_URL.put("spinnerMinimum",
                new CommandInfo("/session/:sessionId/element/:id/spinner/minimum", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("spinnerMaximum",
                new CommandInfo("/session/:sessionId/element/:id/spinner/maximum", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("spinnerSmallChange",
                new CommandInfo("/session/:sessionId/element/:id/spinner/smallChange", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("spinnerIsOnlyValue",
                new CommandInfo("/session/:sessionId/element/:id/spinner/isOnlyValue", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("spinnerGetValue",
                new CommandInfo("/session/:sessionId/element/:id/spinner/getValue", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("spinnerSetValue",
                new CommandInfo("/session/:sessionId/element/:id/spinner/setValue/:value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("spinnerIncrement",
                new CommandInfo("/session/:sessionId/element/:id/spinner/increment", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("spinnerDecrement",
                new CommandInfo("/session/:sessionId/element/:id/spinner/decrement", HttpMethod.POST));
        //endregion

        //region Tab
        FLANIUM_COMMAND_NAME_TO_URL.put("tabSelectedTabItem",
                new CommandInfo("/session/:sessionId/element/:id/tab/selectedTabItem", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("tabSelectedTabItemIndex",
                new CommandInfo("/session/:sessionId/element/:id/tab/selectedTabItemIndex", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("tabTabItems",
                new CommandInfo("/session/:sessionId/element/:id/tab/tabItems", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("tabSelectTabItemIndex",
                new CommandInfo("/session/:sessionId/element/:id/tab/selectTabItemIndex/:index", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("tabSelectTabItemText",
                new CommandInfo("/session/:sessionId/element/:id/tab/selectTabItemText/:value", HttpMethod.POST));
        //endregion

        //region TabItem
        FLANIUM_COMMAND_NAME_TO_URL.put("tabItemSelect",
                new CommandInfo("/session/:sessionId/element/:id/tabItem/select", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("tabItemAddToSelection",
                new CommandInfo("/session/:sessionId/element/:id/tabItem/addToSelection", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("tabItemRemoveFromSelection",
                new CommandInfo("/session/:sessionId/element/:id/tabItem/removeFromSelection", HttpMethod.POST));
        //endregion

        //region TextBox
        FLANIUM_COMMAND_NAME_TO_URL.put("textBoxGetText",
                new CommandInfo("/session/:sessionId/element/:id/textBox/getText", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("textBoxSetText",
                new CommandInfo("/session/:sessionId/element/:id/textBox/setText/:value", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("textBoxIsReadOnly",
                new CommandInfo("/session/:sessionId/element/:id/textBox/isReadOnly", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("textBoxEnter",
                new CommandInfo("/session/:sessionId/element/:id/textBox/enter/:value", HttpMethod.POST));
        //endregion

        //region Thumb
        FLANIUM_COMMAND_NAME_TO_URL.put("thumbSlideHorizontally",
                new CommandInfo("/session/:sessionId/element/:id/thumb/slideHorizontally/:index", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("thumbSlideVertically",
                new CommandInfo("/session/:sessionId/element/:id/thumb/slideVertically/:index", HttpMethod.POST));
        //endregion

        //region TitleBar
        FLANIUM_COMMAND_NAME_TO_URL.put("titleBarMinimizeButton",
                new CommandInfo("/session/:sessionId/element/:id/titleBar/minimizeButton", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("titleBarMaximizeButton",
                new CommandInfo("/session/:sessionId/element/:id/titleBar/maximizeButton", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("titleBarRestoreButton",
                new CommandInfo("/session/:sessionId/element/:id/titleBar/restoreButton", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("titleBarCloseButton",
                new CommandInfo("/session/:sessionId/element/:id/titleBar/closeButton", HttpMethod.POST));
        //endregion

        //region ToggleButton
        FLANIUM_COMMAND_NAME_TO_URL.put("toggleButtonToggle",
                new CommandInfo("/session/:sessionId/element/:id/toggleButton/toggle", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("toggleButtonGetToggleState",
                new CommandInfo("/session/:sessionId/element/:id/toggleButton/getToggleState", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("toggleButtonSetToggleState",
                new CommandInfo("/session/:sessionId/element/:id/toggleButton/setToggleState/:value", HttpMethod.POST));
        //endregion

        //region Tree
        FLANIUM_COMMAND_NAME_TO_URL.put("treeSelectedTreeItem",
                new CommandInfo("/session/:sessionId/element/:id/tree/selectedTreeItem", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("treeItems",
                new CommandInfo("/session/:sessionId/element/:id/tree/items", HttpMethod.POST));
        //endregion

        //region TreeItem
        FLANIUM_COMMAND_NAME_TO_URL.put("treeItemItems",
                new CommandInfo("/session/:sessionId/element/:id/treeItem/items", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("treeItemGetText",
                new CommandInfo("/session/:sessionId/element/:id/treeItem/getText", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("treeItemExpandCollapseState",
                new CommandInfo("/session/:sessionId/element/:id/treeItem/expandCollapseState", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("treeItemExpand",
                new CommandInfo("/session/:sessionId/element/:id/treeItem/expand", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("treeItemCollapse",
                new CommandInfo("/session/:sessionId/element/:id/treeItem/collapse", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("treeItemSelect",
                new CommandInfo("/session/:sessionId/element/:id/treeItem/select", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("treeItemAddToSelection",
                new CommandInfo("/session/:sessionId/element/:id/treeItem/addToSelection", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("treeItemRemoveFromSelection",
                new CommandInfo("/session/:sessionId/element/:id/treeItem/removeFromSelection", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("treeItemIsChecked",
                new CommandInfo("/session/:sessionId/element/:id/treeItem/isChecked", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("treeItemSetChecked",
                new CommandInfo("/session/:sessionId/element/:id/treeItem/setChecked/:value", HttpMethod.POST));
        //endregion

        //region Window
        FLANIUM_COMMAND_NAME_TO_URL.put("windowTitle",
                new CommandInfo("/session/:sessionId/element/:id/window/title", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("windowIsModal",
                new CommandInfo("/session/:sessionId/element/:id/window/isModal", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("windowTitleBar",
                new CommandInfo("/session/:sessionId/element/:id/window/titleBar", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("windowModalWindows",
                new CommandInfo("/session/:sessionId/element/:id/window/modalWindows", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("windowPopup",
                new CommandInfo("/session/:sessionId/element/:id/window/popup", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("windowContextMenu",
                new CommandInfo("/session/:sessionId/element/:id/window/contextMenu", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("windowClose",
                new CommandInfo("/session/:sessionId/element/:id/window/close", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("windowMove",
                new CommandInfo("/session/:sessionId/element/:id/window/move/:x/:y", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("windowSetTransparency",
                new CommandInfo("/session/:sessionId/element/:id/window/setTransparency/:index", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("windowGetActiveWindow",
                new CommandInfo("/session/:sessionId/element/:id/window/getActiveWindow", HttpMethod.POST));
        //endregion

        //region Calendar
        FLANIUM_COMMAND_NAME_TO_URL.put("calendarSelectedDates",
                new CommandInfo("/session/:sessionId/element/:id/calendar/selectedDates", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("calendarSelectDate",
                new CommandInfo("/session/:sessionId/element/:id/calendar/selectDate/:dateTime", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("calendarAddToSelection",
                new CommandInfo("/session/:sessionId/element/:id/calendar/addToSelection/:dateTime", HttpMethod.POST));
        //endregion

        //region DateTimePicker
        FLANIUM_COMMAND_NAME_TO_URL.put("dateTimePickerGetDate",
                new CommandInfo("/session/:sessionId/element/:id/dateTimePicker/getDate", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("dateTimePickerSetDate",
                new CommandInfo("/session/:sessionId/element/:id/dateTimePicker/setDate/:dateTime", HttpMethod.POST));
        //endregion

        //region Other
        FLANIUM_COMMAND_NAME_TO_URL.put("customScreenshot",
                new CommandInfo("/session/:sessionId/customScreenshot/:format", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("elementScreenshot",
                new CommandInfo("/session/:sessionId/element/:id/elementScreenshot", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("dragAndDrop",
                new CommandInfo("/session/:sessionId/dragAndDrop", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("getActiveWindow",
                new CommandInfo("/session/:sessionId/getActiveWindow", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("elementDragAndDrop",
                new CommandInfo("/session/:sessionId/element/:id/elementDragAndDrop", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("sendCharsToActiveElement",
                new CommandInfo("/session/:sessionId/sendCharsToActiveElement", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("getKeyboardLayout",
                new CommandInfo("/session/:sessionId/getKeyboardLayout", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("setKeyboardLayout",
                new CommandInfo("/session/:sessionId/setKeyboardLayout", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("elementMouseAction",
                new CommandInfo("/session/:sessionId/element/:id/elementMouseAction", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("getClipboardText",
                new CommandInfo("/session/:sessionId/getClipboardText", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("setClipboardText",
                new CommandInfo("/session/:sessionId/setClipboardText", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("keyCombination",
                new CommandInfo("/session/:sessionId/keyCombination", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("touchActionsTap",
                new CommandInfo("/session/:sessionId/touchActionsTap", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("touchActionsHold",
                new CommandInfo("/session/:sessionId/touchActionsHold", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("touchActionsPinch",
                new CommandInfo("/session/:sessionId/touchActionsPinch", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("touchActionsTransition",
                new CommandInfo("/session/:sessionId/touchActionsTransition", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("touchActionsDrag",
                new CommandInfo("/session/:sessionId/touchActionsDrag", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("touchActionsRotate",
                new CommandInfo("/session/:sessionId/touchActionsRotate", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("actions",
                new CommandInfo("/session/:sessionId/actions", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("setRootElement",
                new CommandInfo("/session/:sessionId/setRootElement", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("changeProcess",
                new CommandInfo("/session/:sessionId/changeProcess", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("killProcesses",
                new CommandInfo("/session/:sessionId/killProcesses", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("fileOrDirectoryExists",
                new CommandInfo("/session/:sessionId/fileOrDirectoryExists", HttpMethod.POST));

        FLANIUM_COMMAND_NAME_TO_URL.put("deleteFileOrDirectory",
                new CommandInfo("/session/:sessionId/deleteFileOrDirectory", HttpMethod.POST));
        //endregion

    }

    public FlaNiumDriverCommandExecutor(FlaNiumDriverService driverService) {
        super(FLANIUM_COMMAND_NAME_TO_URL, driverService.getUrl());
        service = driverService;
    }

    public FlaNiumDriverCommandExecutor(URL remoteUrl) {
        super(FLANIUM_COMMAND_NAME_TO_URL, remoteUrl);
        service = null;
    }

    @Override
    public Response execute(Command command) throws IOException {
        if (service != null) {
            if (DriverCommand.NEW_SESSION.equals(command.getName())) {
                service.start();
            }
        }


        try {
            return super.execute(command);
        } catch (Throwable t) {
            Throwable rootCause = Throwables.getRootCause(t);
            if (rootCause instanceof ConnectException && "Connection refused".equals(rootCause.getMessage()) &&
                    ((service == null) || (!service.isRunning()))) {
                throw new FlaNiumDriverException("The driver server has unexpectedly died!", t);
            }
            Throwables.throwIfUnchecked(t);
            throw new FlaNiumDriverException(t);
        } finally {
            if ((service != null) && (DriverCommand.QUIT.equals(command.getName()))) {
                service.stop();
            }
        }
    }
}
