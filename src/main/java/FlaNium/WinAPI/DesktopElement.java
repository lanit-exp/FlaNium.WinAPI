package FlaNium.WinAPI;

import FlaNium.WinAPI.elements.Window;
import FlaNium.WinAPI.enums.BasePoint;
import FlaNium.WinAPI.enums.ImageFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DesktopElement extends RemoteWebElement {

    private static final String WINDOW_GET_ACTIVE_WINDOW = "windowGetActiveWindow";
    private static final String ELEMENT_SCREENSHOT = "elementScreenshot";
    private static final String ELEMENT_DRAG_AND_DROP = "elementDragAndDrop";

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

    /**
     * Getting the "Name" attribute of an element.
     * @return The "Name" attribute of the current element.
     */
    public String getName() {
        return this.getAttribute("Name");
    }

    /**
     * Get the active window.
     * @return The active window.
     */
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

    /**
     * Taking a screenshot of the current item.
     * @param outputType Return type BASE64, BYTES or FILE.
     * @param imageFormat  Image format: BMP, EMF, WMF, GIF, JPEG, PNG, TIFF, EXIF, ICON.
     * @return Screenshot of the current item.
     * @throws WebDriverException
     */
    public <X> X getScreenshot(OutputType<X> outputType, ImageFormat imageFormat) throws WebDriverException {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("format", imageFormat.toString());

        Response response = this.execute(ELEMENT_SCREENSHOT,parameters);

        Object result = response.getValue();
        String base64EncodedPng;
        if (result instanceof String) {
            base64EncodedPng = (String)result;
            return outputType.convertFromBase64Png(base64EncodedPng);
        } else if (result instanceof byte[]) {
            base64EncodedPng = new String((byte[])((byte[])result));
            return outputType.convertFromBase64Png(base64EncodedPng);
        } else {
            throw new RuntimeException(String.format("Unexpected result for %s command: %s", "screenshot", result == null ? "null" : result.getClass().getName() + " instance"));
        }
    }

    /**
     * Taking a screenshot of the current item.
     * @param imageFormat Image format: BMP, EMF, WMF, GIF, JPEG, PNG, TIFF, EXIF, ICON.
     * @return Screenshot file of the current item.
     */
    public File getScreenshotFile(ImageFormat imageFormat){
        return getScreenshot(OutputType.FILE,imageFormat);
    }

    /**
     * Taking a screenshot of the current item. Image format: PNG.
     * @return Screenshot file of the current item.
     */
    public File getPngScreenshotFile(){
        return getScreenshotFile(ImageFormat.PNG);
    }

    /**
     * Taking a screenshot of the current item. Image format: JPEG.
     * @return Screenshot file of the current item.
     */
    public File getJpegScreenshotFile(){
        return getScreenshotFile(ImageFormat.JPEG);
    }

    /**
     * Taking a screenshot of the current item and save to file. Image format: PNG.
     * @param file File path.
     * @throws IOException
     */
    public void savePngScreenshotFile(String file) throws IOException {
        FileUtils.copyFile(getPngScreenshotFile(), new File(file));
    }

    /**
     * Taking a screenshot of the current item and save to file. Image format: JPEG.
     * @param file File path.
     * @throws IOException
     */
    public void saveJpegScreenshotFile(String file) throws IOException {
        FileUtils.copyFile(getJpegScreenshotFile(), new File(file));
    }

    /**
     * Drags and drops the mouse from the starting point (Base point of element bounding rectangle + x, y coordinates)
     * with the given distance.
     *
     * @param basePoint {@link BasePoint} of element bounding rectangle.
     * @param x X Coordinate relative to base point of element bounding rectangle.
     * @param y Y Coordinate relative to base point of element bounding rectangle.
     * @param dx The x distance to drag and drop, + for right, - for left.
     * @param dy The y distance to drag and drop, + for down, - for up.
     */
    public void dragAndDrop(BasePoint basePoint, int x, int y, int dx, int dy) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("id", this.getId());
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("dx", dx);
        parameters.put("dy", dy);
        parameters.put("basePoint", basePoint.toString());

        this.execute(ELEMENT_DRAG_AND_DROP, parameters);
    }
}
