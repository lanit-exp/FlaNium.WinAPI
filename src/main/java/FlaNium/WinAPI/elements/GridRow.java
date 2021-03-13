package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class GridRow extends DesktopElement {

    private static final String GRID_ROW_CELLS = "gridRowCells";
    private static final String GRID_ROW_HEADER = "gridRowHeader";
    private static final String GRID_ROW_FIND_CELL_BY_TEXT = "gridRowFindCellByText";
    private static final String GRID_ROW_SCROLL_INTO_VIEW = "gridRowScrollIntoView";

    public GridRow(WebElement element) {
        super(element);
    }

    /**
     * Gets all the cells from the row.
     * @return
     */
    public List<GridCell> cells(){
        Response response = callVoidCommand(GRID_ROW_CELLS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(GridCell::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets the header item of the row.
     * @return
     */
    public GridHeaderItem header(){
        Response response = callVoidCommand(GRID_ROW_HEADER);
        if (response == null) return null;
        return new GridHeaderItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * Find a cell by a given text.
     * @param textToFind
     * @return
     */
    public GridCell findCellByText(String textToFind){
        Response response = callValueCommand(GRID_ROW_FIND_CELL_BY_TEXT, textToFind);
        if (response == null) return null;
        return new GridCell(createRemoteWebElementFromResponse(response));
    }

    /**
     * Scrolls the row into view.
     * @return
     */
    public GridRow scrollIntoView(){
        Response response = callVoidCommand(GRID_ROW_SCROLL_INTO_VIEW);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }
}
