package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class GridHeader extends DesktopElement {

    private static final String GRID_HEADER_COLUMNS = "gridHeaderColumns";

    public GridHeader(WebElement element) {
        super(element);
    }

    /**
     * Gets all header items from the grid header.
     * @return
     */
    public List<GridHeaderItem> columns(){
        Response response = callVoidCommand(GRID_HEADER_COLUMNS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(GridHeaderItem::new)
                .collect(Collectors.toList());
    }
}
