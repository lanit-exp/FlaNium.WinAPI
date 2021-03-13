package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import FlaNium.WinAPI.elements.enums.RowOrColumnMajor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class Grid extends DesktopElement {

    private static final String GRID_ROW_COUNT = "gridRowCount";
    private static final String GRID_COLUMN_COUNT = "gridColumnCount";
    private static final String GRID_COLUMN_HEADERS = "gridColumnHeaders";
    private static final String GRID_ROW_HEADERS = "gridRowHeaders";
    private static final String GRID_ROW_OR_COLUMN_MAJOR = "gridRowOrColumnMajor";
    private static final String GRID_GET_HEADER = "gridGetHeader";
    private static final String GRID_GET_ROWS = "gridGetRows";
    private static final String GRID_SELECTED_ITEMS = "gridSelectedItems";
    private static final String GRID_SELECTED_ITEM = "gridSelectedItem";
    private static final String GRID_SELECT = "gridSelect";
    private static final String GRID_SELECT_TEXT = "gridSelectText";
    private static final String GRID_ADD_TO_SELECTION = "gridAddToSelection";
    private static final String GRID_ADD_TO_SELECTION_TEXT = "gridAddToSelectionText";
    private static final String GRID_REMOVE_FROM_SELECTION = "gridRemoveFromSelection";
    private static final String GRID_REMOVE_FROM_SELECTION_TEXT = "gridRemoveFromSelectionText";
    private static final String GRID_GET_ROW_BY_INDEX = "gridGetRowByIndex";
    private static final String GRID_GET_ROW_BY_VALUE = "gridGetRowByValue";
    private static final String GRID_GET_ROWS_BY_VALUE = "gridGetRowsByValue";


    public Grid(WebElement element) {
        super(element);
    }

    /**
     * Gets the total row count.
     */
    public int rowCount(){
        Response response = callVoidCommand(GRID_ROW_COUNT);
        return Integer.parseInt(response.getValue().toString());
    }

    /**
     * Gets the total column count.
     */
    public int columnCount(){
        Response response = callVoidCommand(GRID_COLUMN_COUNT);
        return Integer.parseInt(response.getValue().toString());
    }

    /**
     * Gets all column header elements.
     */
    public List<RemoteWebElement> columnHeaders(){
        Response response = callVoidCommand(GRID_COLUMN_HEADERS);
        return createRemoteWebElementsFromResponse(response);
    }

    /**
     * Gets all row header elements.
     */
    public List<RemoteWebElement> rowHeaders(){
        Response response = callVoidCommand(GRID_ROW_HEADERS);
        return createRemoteWebElementsFromResponse(response);
    }

    /**
     * Gets whether the data should be read primarily by row or by column.
     */
    public RowOrColumnMajor rowOrColumnMajor(){
        Response response = callVoidCommand(GRID_ROW_OR_COLUMN_MAJOR);
        return RowOrColumnMajor.getEnum(response.getValue().toString());
    }

    /**
     * Gets the header item.
     */
    public GridHeader getHeader(){
        Response response = callVoidCommand(GRID_GET_HEADER);
        if (response == null) return null;
        return new GridHeader(createRemoteWebElementFromResponse(response));
    }

    /**
     * Returns the rows which are currently visible to UIA. Might not be the full list (eg. in virtualized lists)!
     */
    public List<GridRow> getRows(){
        Response response = callVoidCommand(GRID_GET_ROWS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(GridRow::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets all selected items.
     */
    public List<GridRow> selectedItems(){
        Response response = callVoidCommand(GRID_SELECTED_ITEMS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(GridRow::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets the first selected item or null otherwise.
     */
    public GridRow selectedItem(){
        Response response = callVoidCommand(GRID_SELECTED_ITEM);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }

    /**
     * Select a row by index.
     * @param rowIndex
     * @return
     */
    public GridRow select(int rowIndex){
        Response response = callValueCommand(GRID_SELECT,rowIndex);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }

    /**
     * Select the first row by text in the given column.
     * @param columnIndex
     * @param textToFind
     * @return
     */
    public GridRow select(int columnIndex, String textToFind){
        Response response = callValueCommand(GRID_SELECT_TEXT, columnIndex, textToFind);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }

    /**
     * Add a row to the selection by index.
     * @param rowIndex
     * @return
     */
    public GridRow addToSelection(int rowIndex){
        Response response = callValueCommand(GRID_ADD_TO_SELECTION,rowIndex);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }

    /**
     * Add a row to the selection by text in the given column.
     * @param columnIndex
     * @param textToFind
     * @return
     */
    public GridRow addToSelection(int columnIndex, String textToFind){
        Response response = callValueCommand(GRID_ADD_TO_SELECTION_TEXT, columnIndex, textToFind);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }

    /**
     * Remove a row from the selection by index.
     * @param rowIndex
     * @return
     */
    public GridRow removeFromSelection(int rowIndex){
        Response response = callValueCommand(GRID_REMOVE_FROM_SELECTION,rowIndex);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }

    /**
     * Remove a row from the selection by text in the given column.
     * @param columnIndex
     * @param textToFind
     * @return
     */
    public GridRow removeFromSelection(int columnIndex, String textToFind){
        Response response = callValueCommand(GRID_REMOVE_FROM_SELECTION_TEXT, columnIndex, textToFind);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }

    /**
     * Get a row by index.
     * @param rowIndex
     * @return
     */
    public GridRow getRowByIndex(int rowIndex){
        Response response = callValueCommand(GRID_GET_ROW_BY_INDEX,rowIndex);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }

    /**
     * Get a row by text in the given column.
     * @param columnIndex
     * @param textToFind
     * @return
     */
    public GridRow getRowByValue(int columnIndex, String textToFind){
        Response response = callValueCommand(GRID_GET_ROW_BY_VALUE, columnIndex, textToFind);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }

    /**
     * Get all rows where the value of the given column matches the given value.
     * @param columnIndex The column index to check.
     * @param value The value to check.
     * @param maxItems Maximum numbers of items to return, 0 for all.
     * @return List of found rows.
     */
    public List<GridRow> getRowsByValue(int columnIndex, String value, int maxItems){
        Response response = callValueCommand(GRID_GET_ROWS_BY_VALUE, columnIndex, value, maxItems);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(GridRow::new)
                .collect(Collectors.toList());
    }
}
