package FlaNium.WinAPI;


import FlaNium.WinAPI.elements.*;
import org.openqa.selenium.WebElement;

public class WebElementCast {

    private WebElement webElement;

    public WebElementCast(WebElement webElement) {
        this.webElement = webElement;
    }

    public Button toButton() {
        return new Button(webElement);
    }
    public Calendar toCalendar() {
        return new Calendar(webElement);
    }
    public CheckBox toCheckBox() {
        return new CheckBox(webElement);
    }
    public ComboBox toComboBox() {
        return new ComboBox(webElement);
    }
    public DataGridView toDataGridView() {
        return new DataGridView(webElement);
    }
    public DateTimePicker toDateTimePicker() {
        return new DateTimePicker(webElement);
    }
    public Grid toGrid() {
        return new Grid(webElement);
    }
    public GridCell toGridCell() {
        return new GridCell(webElement);
    }
    public GridHeader toGridHeader() {
        return new GridHeader(webElement);
    }
    public GridHeaderItem toGridHeaderItem() {
        return new GridHeaderItem(webElement);
    }
    public GridRow toGridRow() {
        return new GridRow(webElement);
    }
    public HorizontalScrollBar toHorizontalScrollBar() {
        return new HorizontalScrollBar(webElement);
    }
    public Label toLabel() {
        return new Label(webElement);
    }
    public ListBox toListBox() {
        return new ListBox(webElement);
    }
    public ListBoxItem toListBoxItem() {
        return new ListBoxItem(webElement);
    }
    public Menu toMenu() {
        return new Menu(webElement);
    }
    public MenuItem toMenuItem() {
        return new MenuItem(webElement);
    }
    public ProgressBar toProgressBar() {
        return new ProgressBar(webElement);
    }
    public RadioButton toRadioButton() {
        return new RadioButton(webElement);
    }
    public Slider toSlider() {
        return new Slider(webElement);
    }
    public Spinner toSpinner() {
        return new Spinner(webElement);
    }
    public Tab toTab() {
        return new Tab(webElement);
    }
    public TabItem toTabItem() {
        return new TabItem(webElement);
    }
    public TextBox toTextBox() {
        return new TextBox(webElement);
    }
    public Thumb toThumb() {
        return new Thumb(webElement);
    }
    public TitleBar toTitleBar() {
        return new TitleBar(webElement);
    }
    public ToggleButton toToggleButton() {
        return new ToggleButton(webElement);
    }
    public Tree toTree() {
        return new Tree(webElement);
    }
    public TreeItem toTreeItem() {
        return new TreeItem(webElement);
    }
    public VerticalScrollBar toVerticalScrollBar() {
        return new VerticalScrollBar(webElement);
    }
    public Window toWindow() {
        return new Window(webElement);
    }



}
