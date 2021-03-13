package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class DataGridViewRow extends DesktopElement {

    private static final String DATA_GRID_VIEW_ROW_GET_CELLS = "dataGridViewRowGetCells";


    protected DataGridViewRow(WebElement element) {
        super(element);
    }

    /**+
     * Gets all cells.
     */
    public List<DataGridViewCell> getCells(){
        Response response = callVoidCommand(DATA_GRID_VIEW_ROW_GET_CELLS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(DataGridViewCell::new)
                .collect(Collectors.toList());
    }
}
