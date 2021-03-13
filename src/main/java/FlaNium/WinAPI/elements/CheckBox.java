package FlaNium.WinAPI.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class CheckBox extends ToggleButton {

    private static final String CHECK_BOX_TOGGLE_STATE = "checkBoxToggleState";

    public CheckBox(WebElement element) {
        super(element);
    }


    /**
     * @return Toggle State value.
     */
    @Deprecated
    public String toggleState() {
        Response response = callVoidCommand(CHECK_BOX_TOGGLE_STATE);
        return response.getValue().toString();
    }
}
