package FlaNium.WinAPI.actions;

import FlaNium.WinAPI.DesktopElement;
import FlaNium.WinAPI.enums.ImageFormat;
import FlaNium.WinAPI.exceptions.FlaNiumDriverException;
import FlaNium.WinAPI.webdriver.FlaNiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ScreenshotActions {

    private static final String ELEMENT_SCREENSHOT = "elementScreenshot";
    private static final String CUSTOM_SCREENSHOT = "customScreenshot";

    private String command;
    private String id = "";
    private FlaNiumDriver driver;

    public ScreenshotActions(FlaNiumDriver flaNiumDriver) {
        this.driver = flaNiumDriver;
        this.command = CUSTOM_SCREENSHOT;
    }

    public ScreenshotActions(DesktopElement desktopElement) {
        this.driver = (FlaNiumDriver) desktopElement.getWrappedDriver();;
        this.id = desktopElement.getId();
        this.command = ELEMENT_SCREENSHOT;
    }


    /**
     * Taking a screenshot of the current item or the entire screen (if current item is driver).
     *
     * @param outputType  Return type BASE64, BYTES or FILE.
     * @param imageFormat Image format: BMP, EMF, WMF, GIF, JPEG, PNG, TIFF, EXIF, ICON.
     * @param foreground  If the parameter is set to false, it allows you to take a screenshot of an object that is not in the foreground (not for driver as current item).
     * @return Screenshot of the current item or the entire screen.
     * @throws WebDriverException
     */
    public <X> X getScreenshot(OutputType<X> outputType, ImageFormat imageFormat, boolean foreground) throws WebDriverException {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("format", imageFormat.toString());
        parameters.put("foreground", foreground);

        Response response = driver.execute(command, parameters);

        Object result = response.getValue();
        String base64EncodedPng;
        if (result instanceof String) {
            base64EncodedPng = (String) result;
            return outputType.convertFromBase64Png(base64EncodedPng);
        } else if (result instanceof byte[]) {
            base64EncodedPng = new String((byte[]) ((byte[]) result));
            return outputType.convertFromBase64Png(base64EncodedPng);
        } else {
            throw new FlaNiumDriverException(String.format("Unexpected result for %s command: %s", "screenshot", result == null ? "null" : result.getClass().getName() + " instance"));
        }
    }

    /**
     * Taking a screenshot of the current item.
     *
     * @param imageFormat Image format: BMP, EMF, WMF, GIF, JPEG, PNG, TIFF, EXIF, ICON.
     * @return Screenshot file of the current item.
     */
    public File getScreenshotFile(ImageFormat imageFormat) {
        return getScreenshot(OutputType.FILE, imageFormat, true);
    }

    /**
     * Taking a screenshot of the not foreground current item.
     * (not for driver as current item)
     *
     * @param imageFormat Image format: BMP, EMF, WMF, GIF, JPEG, PNG, TIFF, EXIF, ICON.
     * @return Screenshot file of the current item.
     */
    public File getScreenshotFileNotForeground(ImageFormat imageFormat) {
        return getScreenshot(OutputType.FILE, imageFormat, false);
    }

    /**
     * Taking a screenshot of the current item. Image format: PNG.
     *
     * @return Screenshot file of the current item.
     */
    public File getPngScreenshotFile() {
        return getScreenshotFile(ImageFormat.PNG);
    }

    /**
     * Taking a screenshot of the current item. Image format: JPEG.
     *
     * @return Screenshot file of the current item.
     */
    public File getJpegScreenshotFile() {
        return getScreenshotFile(ImageFormat.JPEG);
    }

    /**
     * Taking a screenshot of the not foreground current item. Image format: PNG.
     * (not for driver as current item)
     *
     * @return Screenshot file of the current item.
     */
    public File getPngScreenshotFileNotForeground() {
        return getScreenshotFileNotForeground(ImageFormat.PNG);
    }

    /**
     * Taking a screenshot of the not foreground current item. Image format: JPEG.
     * (not for driver as current item)
     *
     * @return Screenshot file of the current item.
     */
    public File getJpegScreenshotFileNotForeground() {
        return getScreenshotFileNotForeground(ImageFormat.JPEG);
    }

    /**
     * Taking a screenshot of the current item and save to file. Image format: PNG.
     *
     * @param file File path.
     * @throws IOException
     */
    public void savePngScreenshotFile(String file) throws IOException {
        FileUtils.copyFile(getPngScreenshotFile(), new File(file));
    }

    /**
     * Taking a screenshot of the current item and save to file. Image format: JPEG.
     *
     * @param file File path.
     * @throws IOException
     */
    public void saveJpegScreenshotFile(String file) throws IOException {
        FileUtils.copyFile(getJpegScreenshotFile(), new File(file));
    }

    /**
     * Taking a screenshot of the not foreground current item and save to file. Image format: PNG.
     * (not for driver as current item)
     *
     * @param file File path.
     * @throws IOException
     */
    public void savePngScreenshotFileNotForeground(String file) throws IOException {
        FileUtils.copyFile(getPngScreenshotFileNotForeground(), new File(file));
    }

    /**
     * Taking a screenshot of the not foreground current item and save to file. Image format: JPEG.
     * (not for driver as current item)
     *
     * @param file File path.
     * @throws IOException
     */
    public void saveJpegScreenshotFileNotForeground(String file) throws IOException {
        FileUtils.copyFile(getJpegScreenshotFileNotForeground(), new File(file));
    }
}
