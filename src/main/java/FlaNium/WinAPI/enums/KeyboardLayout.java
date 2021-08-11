package FlaNium.WinAPI.enums;

public enum KeyboardLayout {

    RUS("00000419"),
    ENG("00000409"),
    UNKNOWN();

    private String layoutCode;

    public String getLayoutCode() {
        return layoutCode;
    }

    private KeyboardLayout setLayoutCode(String layoutCode) {
        this.layoutCode = layoutCode;
        return this;
    }

    KeyboardLayout() {
    }

    KeyboardLayout(String layoutCode) {
        this.layoutCode = layoutCode;
    }

    public static KeyboardLayout getKeyboardLayout(String layoutCode) {
        switch (layoutCode) {
            case "00000419":
                return KeyboardLayout.RUS;
            case "00000409":
                return KeyboardLayout.ENG;
            default:
                return KeyboardLayout.UNKNOWN.setLayoutCode(layoutCode);
        }
    }

}
