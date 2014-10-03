package by.bkg.stopwatch.mvc.view.impl.panel;

import by.bkg.stopwatch.mvc.controller.impl.panel.RegisteredPersonsPanelController;
import by.bkg.stopwatch.mvc.model.Category;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Panel for registered persons.
 *
 * @author Alexey Baryshnev
 */
public class RegisteredPersonsPanel extends GenericControllableTree<RegisteredPersonsPanelController> {

    private DefaultMutableTreeNode root;

    public RegisteredPersonsPanel() {
        root = new DefaultMutableTreeNode("Root Node");
        root.removeAllChildren();
        treeModel = new DefaultTreeModel(root);
        buildCategoriesTree();
        setShowsRootHandles(false);
    }

    private void buildCategoriesTree() { // TODO ABA: NPE here
//        Set<Category> categories = getController().getCategories();
//        for (Category category : categories) {
//
//        }
    }

    public void init() {
//        addTreeSelectionListener(getController().getSelectionListener());
        for (Category category : getController().getCategories()) {
            DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(category.getName());
            root.add(categoryNode);
        }
    }


    /**
     * Remove all nodes except the root node.
     */
    public void clear() {
//        get
//
//        for(int i = 0; i < root.getChildCount(); i++) {
//            root.getChildAt(i).
//        }
//        rootNode.removeAllChildren();
//        treeModel.reload();
    }

//    /**
//     * Remove the currently selected node.
//     */
//    public void removeCurrentNode() {
//        TreePath currentSelection = tree.getSelectionPath();
//        if (currentSelection != null) {
//            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)
//                    (currentSelection.getLastPathComponent());
//            MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
//            if (parent != null) {
//                treeModel.removeNodeFromParent(currentNode);
//                return;
//            }
//        }
//
//        // Either there was no selection, or the root was selected.
//        toolkit.beep();
//    }
//
//    /**
//     * Add child to the currently selected node.
//     */
//    public DefaultMutableTreeNode addObject(Object child) {
//        DefaultMutableTreeNode parentNode = null;
//        TreePath parentPath = tree.getSelectionPath();
//
//        if (parentPath == null) {
//            parentNode = rootNode;
//        } else {
//            parentNode = (DefaultMutableTreeNode)
//                    (parentPath.getLastPathComponent());
//        }
//
//        return addObject(parentNode, child, true);
//    }
//
//    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
//                                            Object child) {
//        return addObject(parent, child, false);
//    }
//
//    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
//                                            Object child,
//                                            boolean shouldBeVisible) {
//        DefaultMutableTreeNode childNode =
//                new DefaultMutableTreeNode(child);
//
//        if (parent == null) {
//            parent = rootNode;
//        }
//
//        //It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
//        treeModel.insertNodeInto(childNode, parent,
//                parent.getChildCount());
//
//        //Make sure the user can see the lovely new node.
//        if (shouldBeVisible) {
//            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
//        }
//        return childNode;
//    }
}
