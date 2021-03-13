package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

public class TextBox extends DesktopElement {

    private static final String TEXT_BOX_GET_TEXT = "textBoxGetText";
    private static final String TEXT_BOX_SET_TEXT = "textBoxSetText";
    private static final String TEXT_BOX_IS_READ_ONLY = "textBoxIsReadOnly";
    private static final String TEXT_BOX_ENTER = "textBoxEnter";

    public TextBox(WebElement element) {
        super(element);
    }

    /**
     * Gets the text of the element.
     * @return
     */
    public String getText(){
        Response response = callVoidCommand(TEXT_BOX_GET_TEXT);
        return response.getValue().toString();
    }

    /**
     * Sets the text of the element.
     * @param text
     */
    public void setText(String text){
        Response response = callValueCommand(TEXT_BOX_SET_TEXT, text);
    }

    /**
     * Gets if the element is read only or not.
     * @return
     */
    public boolean isReadOnly(){
        Response response = callVoidCommand(TEXT_BOX_IS_READ_ONLY);
        return Boolean.parseBoolean(response.getValue().toString());
    }

    /**
     * Simulate typing in text. This is slower than setting Text but raises more events.
     * @param text
     */
    public void enter(String text){
        Response response = callValueCommand(TEXT_BOX_ENTER, text);
    }
}
