package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.time.LocalDateTime;
import java.util.List;


public class Calendar extends DesktopElement {

    private static final String CALENDAR_SELECTED_DATES = "calendarSelectedDates";
    private static final String CALENDAR_SELECT_DATE = "calendarSelectDate";
    private static final String CALENDAR_ADD_TO_SELECTION = "calendarAddToSelection";


    public Calendar(WebElement element) {
        super(element);
    }

    /**
     * Gets the selected dates in the calendar. For Win32 multiple selection calendar the returned array has two
     * dates, the first date and the last date of the selected range. For WPF calendar the returned array contains
     * all selected dates.
     * @return
     */
    public List<LocalDateTime> selectedDates(){
        Response response = callVoidCommand(CALENDAR_SELECTED_DATES);
        return createLocalDateTimeFromResponse(response);
    }

    /**
     * Deselects other selected dates and selects the specified date.
     * @param dateTime
     */
    public void  selectDate(LocalDateTime dateTime){
        Response response = callValueCommand(CALENDAR_SELECT_DATE, dateTime);
    }

    /**
     * For WPF calendar with SelectionMode="MultipleRange" this method adds the specified date to current selection.
     * For any other type of SelectionMode it deselects other selected dates and selects the specified date.
     * This method is supported only for WPF calendar.
     * @param dateTime
     */
    public void  addToSelection(LocalDateTime dateTime){
        Response response = callValueCommand(CALENDAR_ADD_TO_SELECTION, dateTime);
    }



}
