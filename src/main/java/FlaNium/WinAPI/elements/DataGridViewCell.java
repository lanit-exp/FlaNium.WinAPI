package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class DataGridViewCell extends DesktopElement {

    private static final String DATA_GRID_VIEW_CELL_GET_VALUE = "dataGridViewCellGetValue";
    private static final String DATA_GRID_VIEW_CELL_SET_VALUE = "dataGridViewCellSetValue";


    protected DataGridViewCell(WebElement element) {
        super(element);
    }

    /**
     * Gets the value in the cell.
     */
    public String getValue(){
        Response response = callVoidCommand(DATA_GRID_VIEW_CELL_GET_VALUE);
        return response.getValue().toString();
    }

    /**
     * Sets the value in the cell.
     */
    public void setValue(String value){
        Response response = callValueCommand(DATA_GRID_VIEW_CELL_SET_VALUE, value);
    }
}
