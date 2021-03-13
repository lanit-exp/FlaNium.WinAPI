package FlaNium.WinAPI.elements.enums;

/**
 * Contains values that specify the toggle state of a Microsoft UI Automation element that implements the ITogglePattern.
 */
public enum ToggleState {
    /**
     * The UI Automation element is not selected, checked, marked or otherwise activated.
     */
    OFF("Off"),

    /**
     * The UI Automation element is selected, checked, marked or otherwise activated.
     */
    ON("On"),

    /**
     * The UI Automation element is in an indeterminate state.
     */
    INDETERMINATE("Indeterminate");

    ToggleState(String value) {
        this.value = value;
    }

    private String value;

    public String getValue(){
        return value;
    }

    public static ToggleState getEnum(String value) {
        switch (value) {
            case "Off":
                return OFF;
            case "On":
                return ON;
            case "Indeterminate":
                return INDETERMINATE;
            default:
                return null;
        }
    }
}
