package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class DataGridView extends DesktopElement {

    private static final String DATA_GRID_VIEW_HAS_ADD_ROW = "dataGridViewHasAddRow";
    private static final String DATA_GRID_VIEW_GET_HEADER = "dataGridViewGetHeader";
    private static final String DATA_GRID_VIEW_GET_ROWS = "dataGridViewGetRows";

    public DataGridView(WebElement element) {
        super(element);
    }

    /**
     * Flag to indicate if the grid has the "Add New Item" row or not.
     */
    public boolean hasAddRow(){
        Response response = callVoidCommand(DATA_GRID_VIEW_HAS_ADD_ROW);
        return Boolean.parseBoolean(response.getValue().toString());
    }

    /**
     * Gets the header element or null if the header is disabled.
     */
    public DataGridViewHeader getHeader(){
        Response response = callVoidCommand(DATA_GRID_VIEW_GET_HEADER);
        if (response == null) return null;
        return new DataGridViewHeader(createRemoteWebElementFromResponse(response));
    }

    /**
     * Gets all the data rows.
     */
    public List<DataGridViewRow> getRows(){
        Response response = callVoidCommand(DATA_GRID_VIEW_GET_ROWS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(DataGridViewRow::new)
                .collect(Collectors.toList());
    }

}
