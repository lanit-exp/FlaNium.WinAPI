package FlaNium.WinAPI;


import FlaNium.WinAPI.elements.*;
import org.openqa.selenium.WebElement;

public class WebElementExtensions {

    private WebElementExtensions() {
    }

    public static Button toButton(WebElement element) {
        return new Button(element);
    }
    public static Calendar toCalendar(WebElement element) {
        return new Calendar(element);
    }
    public static CheckBox toCheckBox(WebElement element) {
        return new CheckBox(element);
    }
    public static ComboBox toComboBox(WebElement element) {
        return new ComboBox(element);
    }
    public static DataGridView toDataGridView(WebElement element) {
        return new DataGridView(element);
    }
    public static DateTimePicker toDateTimePicker(WebElement element) {
        return new DateTimePicker(element);
    }
    public static Grid toGrid(WebElement element) {
        return new Grid(element);
    }
    public static GridCell toGridCell(WebElement element) {
        return new GridCell(element);
    }
    public static GridHeader toGridHeader(WebElement element) {
        return new GridHeader(element);
    }
    public static GridHeaderItem toGridHeaderItem(WebElement element) {
        return new GridHeaderItem(element);
    }
    public static GridRow toGridRow(WebElement element) {
        return new GridRow(element);
    }
    public static HorizontalScrollBar toHorizontalScrollBar(WebElement element) {
        return new HorizontalScrollBar(element);
    }
    public static Label toLabel(WebElement element) {
        return new Label(element);
    }
    public static ListBox toListBox(WebElement element) {
        return new ListBox(element);
    }
    public static ListBoxItem toListBoxItem(WebElement element) {
        return new ListBoxItem(element);
    }
    public static Menu toMenu(WebElement element) {
        return new Menu(element);
    }
    public static MenuItem toMenuItem(WebElement element) {
        return new MenuItem(element);
    }
    public static ProgressBar toProgressBar(WebElement element) {
        return new ProgressBar(element);
    }
    public static RadioButton toRadioButton(WebElement element) {
        return new RadioButton(element);
    }
    public static Slider toSlider(WebElement element) {
        return new Slider(element);
    }
    public static Spinner toSpinner(WebElement element) {
        return new Spinner(element);
    }
    public static Tab toTab(WebElement element) {
        return new Tab(element);
    }
    public static TabItem toTabItem(WebElement element) {
        return new TabItem(element);
    }
    public static TextBox toTextBox(WebElement element) {
        return new TextBox(element);
    }
    public static Thumb toThumb(WebElement element) {
        return new Thumb(element);
    }
    public static TitleBar toTitleBar(WebElement element) {
        return new TitleBar(element);
    }
    public static ToggleButton toToggleButton(WebElement element) {
        return new ToggleButton(element);
    }
    public static Tree toTree(WebElement element) {
        return new Tree(element);
    }
    public static TreeItem toTreeItem(WebElement element) {
        return new TreeItem(element);
    }
    public static VerticalScrollBar toVerticalScrollBar(WebElement element) {
        return new VerticalScrollBar(element);
    }
    public static Window toWindow(WebElement element) {
        return new Window(element);
    }



}
