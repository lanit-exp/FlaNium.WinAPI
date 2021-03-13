package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class GridCell extends DesktopElement {

    private static final String GRID_CELL_CONTAINING_GRID = "gridCellContainingGrid";
    private static final String GRID_CELL_CONTAINING_ROW = "gridCellContainingRow";


    public GridCell(WebElement element) {
        super(element);
    }

    /**
     * Gets the grid that contains this cell.
     * @return
     */
    public Grid containingGrid(){
        Response response = callVoidCommand(GRID_CELL_CONTAINING_GRID);
        if (response == null) return null;
        return new Grid(createRemoteWebElementFromResponse(response));
    }

    /**
     * Gets the row that contains this cell.
     * @return
     */
    public GridRow containingRow(){
        Response response = callVoidCommand(GRID_CELL_CONTAINING_ROW);
        if (response == null) return null;
        return new GridRow(createRemoteWebElementFromResponse(response));
    }
}
