package FlaNium.WinAPI.actions;

import FlaNium.WinAPI.enums.KeyCombination;
import FlaNium.WinAPI.enums.KeyboardLayout;
import FlaNium.WinAPI.webdriver.FlaNiumDriver;

import java.util.HashMap;

public class KeyboardActions {

    private static final String SEND_CHARS_TO_ACTIVE_ELEMENT = "sendCharsToActiveElement";
    private static final String GET_KEYBOARD_LAYOUT = "getKeyboardLayout";
    private static final String SET_KEYBOARD_LAYOUT = "setKeyboardLayout";
    private static final String GET_CLIPBOARD_TEXT = "getClipboardText";
    private static final String SET_CLIPBOARD_TEXT = "setClipboardText";
    private static final String KEY_COMBINATION = "keyCombination";

    private FlaNiumDriver flaNiumDriver;

    public KeyboardActions(FlaNiumDriver flaNiumDriver) {
        this.flaNiumDriver = flaNiumDriver;
    }

    /**
     * Simulate keystrokes. Send chars to active element.
     * @param chars String of chars
     */
    public void sendChars(String chars) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("value", chars);

        flaNiumDriver.execute(SEND_CHARS_TO_ACTIVE_ELEMENT, parameters);
    }


    /**
     * Set keyboard layout.
     * @param keyboardLayout - hex string code of keyboard layout.
     */
    public void setKeyboardLayoutCode(String keyboardLayout) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("value", keyboardLayout);

        flaNiumDriver.execute(SET_KEYBOARD_LAYOUT, parameters);
    }


    /**
     * Get keyboard layout.
     * @return - hex string code of keyboard layout.
     */
    public String getKeyboardLayoutCode() {
        return flaNiumDriver.execute(GET_KEYBOARD_LAYOUT).getValue().toString();
    }


    /**
     * Set keyboard layout.
     * @param keyboardLayout - {@link KeyboardLayout} instance of keyboard layout.
     */
    public void setKeyboardLayout(KeyboardLayout keyboardLayout) {
        setKeyboardLayoutCode(keyboardLayout.getLayoutCode());
    }


    /**
     * Get keyboard layout.
     * @return - {@link KeyboardLayout} instance of keyboard layout.
     */
    public KeyboardLayout getKeyboardLayout() {
        return KeyboardLayout.getKeyboardLayout(getKeyboardLayoutCode());
    }


    /**
     * Get clipboard text.
     * @return clipboard text string. Returned empty string if clipboard empty or contains no text.
     */
    public String getClipboardText(){
        return flaNiumDriver.execute(GET_CLIPBOARD_TEXT).getValue().toString();
    }


    /**
     * Set clipboard text.
     * @param text the text to be copied to the clipboard.
     */
    public void setClipboardText(String text){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("value", text);

        flaNiumDriver.execute(SET_CLIPBOARD_TEXT, parameters);
    }


    /**
     * Keystrokes of the selected combination.
     * @param keyCombination {@link KeyCombination} instance of key combination.
     */
    public void performKeyCombination(KeyCombination keyCombination){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("value", keyCombination.toString());

        flaNiumDriver.execute(KEY_COMBINATION, parameters);
    }
}
