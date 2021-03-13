package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.time.LocalDateTime;

public class DateTimePicker extends DesktopElement {

    private static final String DATE_TIME_PICKER_GET_DATE = "dateTimePickerGetDate";
    private static final String DATE_TIME_PICKER_SET_DATE = "dateTimePickerSetDate";

    public DateTimePicker(WebElement element) {
        super(element);
    }

    /**
     * Gets the selected date in the DateTimePicker.
     * For Win32, setting SelectedDate to null will uncheck the DateTimePicker control and disable it. Also for Win32, if the control is unchecked then SelectedDate will return null.
     * @return
     */
    public LocalDateTime getDate(){
        Response response = callVoidCommand(DATE_TIME_PICKER_GET_DATE);
        return parseDateTime(response.getValue().toString());
    }


    /**
     * Sets the selected date in the DateTimePicker.
     * For Win32, setting SelectedDate to null will uncheck the DateTimePicker control and disable it. Also for Win32, if the control is unchecked then SelectedDate will return null.
     * @param dateTime
     */
    public void  setDate(LocalDateTime dateTime){
        Response response = callValueCommand(DATE_TIME_PICKER_SET_DATE, dateTime);
    }

}
