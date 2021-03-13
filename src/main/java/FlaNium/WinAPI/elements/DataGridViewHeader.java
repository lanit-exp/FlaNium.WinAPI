package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class DataGridViewHeader extends DesktopElement {

    private static final String DATA_GRID_VIEW_HEADER_GET_COLUMNS = "dataGridViewHeaderGetColumns";

    protected DataGridViewHeader(WebElement element) {
        super(element);
    }

    /**+
     * Gets the header items.
     */
    public List<DataGridViewHeaderItem> getColumns(){
        Response response = callVoidCommand(DATA_GRID_VIEW_HEADER_GET_COLUMNS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(DataGridViewHeaderItem::new)
                .collect(Collectors.toList());
    }
}
