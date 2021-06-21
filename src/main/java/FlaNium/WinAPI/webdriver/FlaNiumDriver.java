package FlaNium.WinAPI.webdriver;

import FlaNium.WinAPI.enums.ImageFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FlaNiumDriver extends RemoteWebDriver {

    private static final String CUSTOM_SCREENSHOT = "customScreenshot";
    private static final String DRAG_AND_DROP = "dragAndDrop";
    private static final String GET_ACTIVE_WINDOW = "getActiveWindow";
    private static final String SEND_CHARS_TO_ACTIVE_ELEMENT = "sendCharsToActiveElement";

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} class using the specified options
     *
     * @param options Thre {@link FlaNiumOptions} to be used with the FlaNium driver.
     */
    public FlaNiumDriver(FlaNiumOptions options) {
        this(createDefaultService(options.getClass()), options);
    }

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} class using the specified {@link FlaNiumDriverService}
     * and options.
     *
     * @param service The {@link FlaNiumDriverService} to use.
     * @param options The {@link FlaNiumOptions} used to initialize the driver.
     */
    public FlaNiumDriver(FlaNiumDriverService service, FlaNiumOptions options) {
        super(new FlaNiumDriverCommandExecutor(service), options.toCapabilities());
    }

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} class using the specified {@link FlaNiumDriverService}
     * and options.
     *
     * @param service The {@link FlaNiumDriverService} to use.
     * @param dc      The {@link DesiredCapabilities} used to initialize the driver.
     */
    public FlaNiumDriver(FlaNiumDriverService service, DesiredCapabilities dc) {
        super(new FlaNiumDriverCommandExecutor(service), dc);
    }

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} lass using the specified remote address and options.
     *
     * @param remoteAddress URL containing the address of the FlaNiumDriver remote server.
     * @param options       The {@link FlaNiumOptions} object to be used with the FlaNium driver.
     */
    public FlaNiumDriver(URL remoteAddress, FlaNiumOptions options) {
        super(new FlaNiumDriverCommandExecutor(remoteAddress), options.toCapabilities());
    }

    /**
     * Initializes a new instance of the {@link FlaNiumDriver} lass using the specified remote address and options.
     *
     * @param remoteAddress URL containing the address of the FlaNiumDriver remote server.
     * @param dc            The {@link DesiredCapabilities} object to be used with the FlaNium driver.
     */
    public FlaNiumDriver(URL remoteAddress, DesiredCapabilities dc) {
        super(new FlaNiumDriverCommandExecutor(remoteAddress), dc);
    }


    private static FlaNiumDriverService createDefaultService(Class<? extends FlaNiumOptions> optionsType) {
        if (optionsType == DesktopOptions.class)
            return FlaNiumDriverService.createDesktopService();

        throw new IllegalArgumentException(
                "Option type must be type of DesktopOptions");
    }

    /**
     * Taking a screenshot of the entire screen.
     *
     * @param outputType  Return type BASE64, BYTES or FILE.
     * @param imageFormat Image format: BMP, EMF, WMF, GIF, JPEG, PNG, TIFF, EXIF, ICON.
     * @return Screenshot of the entire screen.
     * @throws WebDriverException
     */
    public <X> X getScreenshot(OutputType<X> outputType, ImageFormat imageFormat) throws WebDriverException {

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("format", imageFormat.toString());

        Response response = this.execute(CUSTOM_SCREENSHOT, parameters);

        Object result = response.getValue();
        String base64EncodedPng;
        if (result instanceof String) {
            base64EncodedPng = (String) result;
            return outputType.convertFromBase64Png(base64EncodedPng);
        } else if (result instanceof byte[]) {
            base64EncodedPng = new String((byte[]) ((byte[]) result));
            return outputType.convertFromBase64Png(base64EncodedPng);
        } else {
            throw new RuntimeException(String.format("Unexpected result for %s command: %s", "screenshot", result == null ? "null" : result.getClass().getName() + " instance"));
        }
    }

    /**
     * Taking a screenshot of the entire screen.
     *
     * @param imageFormat Image format: BMP, EMF, WMF, GIF, JPEG, PNG, TIFF, EXIF, ICON.
     * @return Full Screen Screenshot File.
     */
    public File getScreenshotFile(ImageFormat imageFormat) {
        return getScreenshot(OutputType.FILE, imageFormat);
    }

    /**
     * Taking a screenshot of the entire screen. Image format: PNG.
     *
     * @return Full Screen Screenshot File.
     */
    public File getPngScreenshotFile() {
        return getScreenshotFile(ImageFormat.PNG);
    }

    /**
     * Taking a screenshot of the entire screen. Image format: JPEG.
     *
     * @return Full Screen Screenshot File.
     */
    public File getJpegScreenshotFile() {
        return getScreenshotFile(ImageFormat.JPEG);
    }

    /**
     * Taking a screenshot of the entire screen and save to file. Image format: PNG.
     *
     * @param file File path.
     * @throws IOException
     */
    public void savePngScreenshotFile(String file) throws IOException {
        FileUtils.copyFile(getPngScreenshotFile(), new File(file));
    }

    /**
     * Taking a screenshot of the entire screen and save to file. Image format: JPEG.
     *
     * @param file File path.
     * @throws IOException
     */
    public void saveJpegScreenshotFile(String file) throws IOException {
        FileUtils.copyFile(getJpegScreenshotFile(), new File(file));
    }

    /**
     * Drags and drops the mouse from the starting point with the given distance.
     * @param x X coordinate of the start point.
     * @param y Y coordinate of the start point.
     * @param dx The x distance to drag and drop, + for right, - for left.
     * @param dy The y distance to drag and drop, + for down, - for up.
     */
    public void dragAndDrop(int x, int y, int dx, int dy) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("x", x);
        parameters.put("y", y);
        parameters.put("dx", dx);
        parameters.put("dy", dy);

        this.execute(DRAG_AND_DROP, parameters);
    }

    /**
     * Get the active window.
     * @return The active window.
     */
    public RemoteWebElement getActiveWindow(){
        try {
            Response  response = this.execute(GET_ACTIVE_WINDOW);

            Object value = response.getValue();

            if (value instanceof RemoteWebElement) {
                return (RemoteWebElement) value;
            }

            if (!(value instanceof Map<?, ?>)) {
                return null;
            }

            Map<?, ?> elementDictionary = (Map<?, ?>) value;
            RemoteWebElement result = new RemoteWebElement();
            result.setParent(this);
            result.setId((String) elementDictionary.get("ELEMENT"));
            return result;

        } catch (NoSuchElementException e) {
           return null;
        }
    }


    /**
     * Simulate keystrokes. Send chars to active element.
     * @param chars String of chars
     */
    public void sendChars(String chars) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("value", chars);

        this.execute(SEND_CHARS_TO_ACTIVE_ELEMENT, parameters);
    }


}
