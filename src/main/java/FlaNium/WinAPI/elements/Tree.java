package FlaNium.WinAPI.elements;

import FlaNium.WinAPI.DesktopElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.List;
import java.util.stream.Collectors;

public class Tree extends DesktopElement {

    private static final String TREE_SELECTED_TREE_ITEM = "treeSelectedTreeItem";
    private static final String TREE_ITEMS = "treeItems";

    public Tree(WebElement element) {
        super(element);
    }

    /**
     * The currently selected "TreeItem".
     * @return
     */
    public TreeItem selectedTreeItem(){
        Response response = callVoidCommand(TREE_SELECTED_TREE_ITEM);
        if (response == null) return null;
        return new TreeItem(createRemoteWebElementFromResponse(response));
    }

    /**
     * All child "TreeItem" objects from this "Tree".
     * @return
     */
    public List<TreeItem> items() {
        Response response = callVoidCommand(TREE_ITEMS);
        return createRemoteWebElementsFromResponse(response)
                .stream()
                .map(TreeItem::new)
                .collect(Collectors.toList());
    }
}
