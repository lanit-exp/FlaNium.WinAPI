package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.elements.enums.ToggleState;
import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class ToggleButton extends DesktopElement {

    private static final String TOGGLE_BUTTON_TOGGLE = "toggleButtonToggle";
    private static final String TOGGLE_BUTTON_GET_TOGGLE_STATE = "toggleButtonGetToggleState";
    private static final String TOGGLE_BUTTON_SET_TOGGLE_STATE = "toggleButtonSetToggleState";

    public ToggleButton(WebElement element) {
        super(element);
    }

    /**
     * Toggles the toggle button.
     * Note: In some WPF scenarios, the bounded command might not be fired. Use "AutomationElement.Click" instead in that case.
     */
    public void toggle (){
        Response response = callVoidCommand(TOGGLE_BUTTON_TOGGLE);
    }

    /**
     * Gets the current toggle state.
     * @return
     */
    public ToggleState getToggleState() {
        Response response = callVoidCommand(TOGGLE_BUTTON_GET_TOGGLE_STATE);
        return ToggleState.getEnum(response.getValue().toString());
    }

    /**
     * Sets the current toggle state.
     */
    public void setToggleState(ToggleState toggleState) {
        Response response = callValueCommand(TOGGLE_BUTTON_SET_TOGGLE_STATE, toggleState.getValue());
    }

}
