package FlaNium.WinAPI;

import FlaNium.WinAPI.elements.Window;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DesktopElement extends RemoteWebElement {

    private static final String WINDOW_GET_ACTIVE_WINDOW = "windowGetActiveWindow";

    protected DesktopElement(WebElement element) {
        this.setParent(getRemoteWebDriver(element));
        this.setId(getId(element));
    }

    private static RemoteWebDriver getRemoteWebDriver(WebElement element) {
        if (!(element instanceof RemoteWebElement))
            throw new ClassCastException("Specified cast is not valid. Please use RemoteWebElement as parameter.");
        RemoteWebElement remoteWebElement = (RemoteWebElement) element;
        return (RemoteWebDriver) remoteWebElement.getWrappedDriver();
    }

    private static String getId(WebElement element) {

        try {
            Method methodInfo = element.getClass().getMethod("getId");
            return methodInfo.invoke(element).toString();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return null;
        }
    }


    //region Create from Response
    protected RemoteWebElement createRemoteWebElementFromResponse(Response response) {
        Object value = response.getValue();
        if (value instanceof RemoteWebElement) {
            return (RemoteWebElement) value;
        }

        if (!(value instanceof Map<?, ?>)) {
            return null;
        }
        Map<?, ?> elementDictionary = (Map<?, ?>) value;
        RemoteWebElement result = new RemoteWebElement();
        result.setParent((RemoteWebDriver) this.getWrappedDriver());
        result.setId((String) elementDictionary.get("ELEMENT"));
        return result;
    }

    protected List<RemoteWebElement> createRemoteWebElementsFromResponse(Response response) {

        Object responseValue = response.getValue();

        List allElements;
        try {
            allElements = (List) responseValue;
        } catch (ClassCastException var8) {
            throw new WebDriverException("Returned value cannot be converted to List<WebElement>: " + responseValue, var8);
        }

        Iterator var6 = allElements.iterator();

        while (var6.hasNext()) {
            RemoteWebElement element = (RemoteWebElement) var6.next();
            element.setParent((RemoteWebDriver) this.getWrappedDriver());
        }

        return allElements;
    }

    protected List<LocalDateTime> createLocalDateTimeFromResponse(Response response) {

        Object responseValue = response.getValue();

        List allElements;
        try {
            allElements = (List) responseValue;
        } catch (ClassCastException var8) {
            throw new WebDriverException("Returned value cannot be converted to List<LocalDateTime>: " + responseValue, var8);
        }

        Iterator var6 = allElements.iterator();
        List<LocalDateTime> list = new ArrayList<>();

        while (var6.hasNext()) {
            Object object = var6.next();
            String value = object.toString();
            LocalDateTime localDateTime = parseDateTime(value);
            list.add(localDateTime);
        }

        return list;
    }
    //endregion

    protected Response exe(String command, HashMap<String, Object> parameters) {
        Response response;
        try {
            response = this.execute(command, parameters);
        } catch (NoSuchElementException e) {
            response = null;
        }
        return response;
    }

    //region Call Command
    protected Response callVoidCommand(String command) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, String value) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("value", value);
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, int index) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("index", index);
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, int index, String text) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("index", index);
        parameters.put("text", text);
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, int index, String text, int count) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("index", index);
        parameters.put("text", text);
        parameters.put("count", count);
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, int x, int y) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        return exe(command, parameters);
    }

    protected Response callValueCommand(String command, LocalDateTime dateTime) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("dateTime", dateTime.toString());
        return exe(command, parameters);
    }
    //endregion

    //region public Method
    public String getName() {
        return this.getAttribute("Name");
    }

    public Window getActiveWindow() {
        Response response = callVoidCommand(WINDOW_GET_ACTIVE_WINDOW);
        return new Window(createRemoteWebElementFromResponse(response));
    }
    //endregion

    //region Parse

    protected double parseDouble(Response response) {
        String value = response.getValue().toString();
        if (value.contains(","))
            value = value.replace(",", ".");
        return Double.parseDouble(value);
    }

    protected LocalDateTime parseDateTime(String dateTime) {
        String pattern = null;

        if (dateTime.matches("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}:\\d{2}")) pattern = "dd.MM.uuuu HH:mm:ss";
        else if (dateTime.matches("\\d{2}\\.\\d{2}\\.\\d{4} \\d{1}:\\d{2}:\\d{2}")) pattern = "dd.MM.uuuu H:mm:ss";

        if (pattern != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        }
        return LocalDateTime.parse(dateTime);
    }
    //endregion

}
