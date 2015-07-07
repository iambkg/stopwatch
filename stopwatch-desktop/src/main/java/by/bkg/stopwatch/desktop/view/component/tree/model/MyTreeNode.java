package by.bkg.stopwatch.desktop.view.component.tree.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Deprecated
public class MyTreeNode {
    private String name;
    private String description;
    private List<MyTreeNode> children = new ArrayList<MyTreeNode>();

    public MyTreeNode() {
    }

    public MyTreeNode(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MyTreeNode> getChildren() {
        return children;
    }

    public String toString() {
        return "MyTreeNode: " + name + ", " + description;
    }

    public void removeAllChildren() {
        for (MyTreeNode child : children) {
            child.removeAllChildren();
        }
        children.clear();
    }
}
