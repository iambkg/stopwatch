package by.bkg.stopwatch.mvc.view.panel;

import by.bkg.stopwatch.mvc.controller.panel.IRegisteredPersonsPanelController;
import by.bkg.stopwatch.mvc.model.business.Category;
import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.view.i18n.AppMessages;

import javax.inject.Inject;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * Panel for registered persons.
 *
 * @author Alexey Baryshnev
 */
public class RegisteredPersonsPanel extends GenericControllableTree<IRegisteredPersonsPanelController> implements IRegisteredPersonsPanel {

    private DefaultMutableTreeNode root;

    @Inject
    public RegisteredPersonsPanel(AppMessages appMessages) {
        setModel(new DefaultTreeModel(new DefaultMutableTreeNode(appMessages.getString("label.categories"))));
        treeModel = getModel();
        root = (DefaultMutableTreeNode) treeModel.getRoot();

        setEditable(true);
        setRootVisible(false);
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        setShowsRootHandles(true);

        //Enable tool tips.
//        ToolTipManager.sharedInstance().registerComponent(this);
    }

    @Override
    public void makeInit() {
        populateTree();
    }

    @Override
    public void populateTree() {
        clear();
//        addTreeSelectionListener(getController().getSelectionListener());
        for (Category category : getController().getCategories()) {
            DefaultMutableTreeNode categoryNode = addCategory(category);
            for (Person person : getController().getPersonsByCategory(category)) {
                addObject(categoryNode, person);
            }
        }
        expandAll();
    }

    /**
     * Remove all nodes except the root node.
     */
    public void clear() {
        ((DefaultMutableTreeNode) getModel().getRoot()).removeAllChildren();
        ((DefaultTreeModel) getModel()).reload();
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

    /**
     * Add child
     */
    public DefaultMutableTreeNode addCategory(Category category) {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = getSelectionPath();

        if (parentPath == null) {
            parentNode = root;
        } else {
            parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        }

        return addObject(parentNode, category, true);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
        return addObject(parent, child, false);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

        if (parent == null) {
            parent = (DefaultMutableTreeNode) getModel().getRoot();
        }

        //It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
        ((DefaultTreeModel) getModel()).insertNodeInto(childNode, parent, parent.getChildCount());

        //Make sure the user can see the lovely new node.
        if (shouldBeVisible) {
            scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }
}
