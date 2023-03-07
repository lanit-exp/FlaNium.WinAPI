package FlaNium.WinAPI;

import FlaNium.WinAPI.actions.TouchActions;
import FlaNium.WinAPI.elements.Window;
import FlaNium.WinAPI.enums.BasePoint;
import FlaNium.WinAPI.enums.KeyCombination;
import FlaNium.WinAPI.enums.KeyboardLayout;
import FlaNium.WinAPI.webdriver.FlaNiumDriver;


public class CoordinateElement {

    private DesktopElement baseDesktopElement;
    private BasePoint basePointOfBaseDesktopElement;
    private int dx;
    private int dy;
    private int width;
    private int height;

    public CoordinateElement(DesktopElement baseDesktopElement, BasePoint basePointOfBaseDesktopElement, int dx, int dy, int width, int height) {
        this.baseDesktopElement = baseDesktopElement;
        this.basePointOfBaseDesktopElement = basePointOfBaseDesktopElement;
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
    }

    public CoordinateElement(FlaNiumDriver driver, int x, int y, int width, int height) {
        this.dx = x;
        this.dy = y;
        this.width = width;
        this.height = height;
        this.baseDesktopElement = new Window(driver.getActiveWindow());
        this.basePointOfBaseDesktopElement = BasePoint.ZERO_POINT;
    }

    public void moveMouseToElementPoint(BasePoint basePoint, int dx, int dy) {
        int x = basePoint.getXCoordinate(this.dx, width) + dx;
        int y = basePoint.getYCoordinate(this.dy, height) + dy;

        baseDesktopElement.mouseActions().mouseMove(basePointOfBaseDesktopElement, x, y);
    }

    public void clickToElementPoint(BasePoint basePoint, int dx, int dy) {
        int x = basePoint.getXCoordinate(this.dx, width) + dx;
        int y = basePoint.getYCoordinate(this.dy, height) + dy;

        baseDesktopElement.mouseActions().mouseClick(basePointOfBaseDesktopElement, x, y);
    }

    public void rightClickToElementPoint(BasePoint basePoint, int dx, int dy) {
        int x = basePoint.getXCoordinate(this.dx, width) + dx;
        int y = basePoint.getYCoordinate(this.dy, height) + dy;

        baseDesktopElement.mouseActions().mouseRightClick(basePointOfBaseDesktopElement, x, y);
    }

    public void doubleClickToElementPoint(BasePoint basePoint, int dx, int dy) {
        int x = basePoint.getXCoordinate(this.dx, width) + dx;
        int y = basePoint.getYCoordinate(this.dy, height) + dy;

        baseDesktopElement.mouseActions().mouseDoubleClick(basePointOfBaseDesktopElement, x, y);
    }

    public void clickAndClear() {
        clickToElementPoint(BasePoint.CENTER, 0, 0);
        ((FlaNiumDriver) baseDesktopElement.getWrappedDriver()).keyboardActions().performKeyCombination(KeyCombination.CTRL_A_DELETE);
    }

    public String getTextValue() {
        clickToElementPoint(BasePoint.CENTER, 0, 0);
        ((FlaNiumDriver) baseDesktopElement.getWrappedDriver()).keyboardActions().performKeyCombination(KeyCombination.CTRL_A);
        ((FlaNiumDriver) baseDesktopElement.getWrappedDriver()).keyboardActions().performKeyCombination(KeyCombination.CTRL_C);
        clickToElementPoint(BasePoint.CENTER, 0, 0);
        return ((FlaNiumDriver) baseDesktopElement.getWrappedDriver()).keyboardActions().getClipboardText();
    }

    public void inputTextValue(String text) {
        clickAndClear();
        if (((FlaNiumDriver) baseDesktopElement.getWrappedDriver()).keyboardActions().getKeyboardLayout() != KeyboardLayout.ENG) {
            ((FlaNiumDriver) baseDesktopElement.getWrappedDriver()).keyboardActions().setKeyboardLayout(KeyboardLayout.ENG);
        }
        ((FlaNiumDriver) baseDesktopElement.getWrappedDriver()).keyboardActions().sendChars(text);
    }

    public TouchActions touchActions() {
        return new TouchActions(baseDesktopElement, dx, dy);
    }
}
