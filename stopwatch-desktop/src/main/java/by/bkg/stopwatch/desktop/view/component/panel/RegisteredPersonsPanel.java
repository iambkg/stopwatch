package by.bkg.stopwatch.desktop.view.component.panel;

import by.bkg.stopwatch.desktop.view.i18n.AppMessages;

/**
 * Panel for registered persons.
 *
 * @author Alexey Baryshnev
 */
@Deprecated
public class RegisteredPersonsPanel {
//public class RegisteredPersonsPanel extends GenericControllableTree<MyTreeTableModel, IRegisteredPersonsPanelController>
//        implements IRegisteredPersonsPanel {

//    private DefaultMutableTreeNode root;

//    private AppMessages appMessages;

    public RegisteredPersonsPanel(AppMessages appMessages) {
//        super(new MyTreeTableModel());
//        this.appMessages = appMessages;
//        getColumnModel().getColumn(2).setCellRenderer(createCellRenderer());
//        getColumnModel().getColumn(2).setCellEditor(createCellEditor());

//        setModel(new DefaultTreeModel(new DefaultMutableTreeNode(appMessages.getString("label.categories"))));
//        treeModel = getModel();
//        root = (DefaultMutableTreeNode) treeModel.getRoot();

//        setEditable(true);
//        setRootVisible(false);
//        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//        setShowsRootHandles(true);

        //Enable tool tips.
//        ToolTipManager.sharedInstance().registerComponent(this);
    }

//    private TableCellRenderer createCellRenderer() {
//        return new TableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                return createDeleteButton();
//            }
//        };
//    }
//
//    private TableCellEditor createCellEditor() {
//        return new TableCellEditor() {
//            @Override
//            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//                return createDeleteButton();
//            }
//
//            @Override
//            public Object getCellEditorValue() {
//                return "";
//            }
//
//            @Override
//            public boolean isCellEditable(EventObject anEvent) {
//                return true;
//            }
//
//            @Override
//            public boolean shouldSelectCell(EventObject anEvent) {
//                return true;
//            }
//
//            @Override
//            public boolean stopCellEditing() {
//                return true;
//            }
//
//            @Override
//            public void cancelCellEditing() {
//            }
//
//            @Override
//            public void addCellEditorListener(CellEditorListener l) {
//            }
//
//            @Override
//            public void removeCellEditorListener(CellEditorListener l) {
//            }
//        };
//    }
//
//    private JButton createDeleteButton() {
//        JButton button = new JButton(appMessages.getString("table.btn.remove-person"));
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                getController().removePerson();
//            }
//        });
//        return button;
//    }

//    public void makeInit() {
//        populateTree();
//    }

//    public void populateTree() {
//        clear();
////        addTreeSelectionListener(getController().getSelectionListener());
////        for (ICategory category : getController().getCategories()) {
////            DefaultMutableTreeNode categoryNode = addCategory(category);
////            for (Person person : getController().getPersonsByCategory(category)) {
////                addObject(categoryNode, person);
////            }
////        }
//        expandAll();
//    }

//    /**
//     * Remove all nodes except the root node.
//     */
//    public void clear() {
////        getXTreeTableModel().getXRoot().removeAllChildren();
//
//        ((DefaultMutableTreeNode) getModel().getRoot()).removeAllChildren();
//        ((DefaultTreeModel) getModel()).reload();
//    }

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
//     * Add child
//     */
//    public DefaultMutableTreeNode addCategory(ICategory category) {
//        DefaultMutableTreeNode parentNode = null;
//        TreePath parentPath = getSelectionPath();
//
//        if (parentPath == null) {
//            parentNode = root;
//        } else {
//            parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
//        }
//
//        return addObject(parentNode, category, true);
//    }
//
//    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
//        return addObject(parent, child, false);
//    }
//
//    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
//        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
//
//        if (parent == null) {
//            parent = (DefaultMutableTreeNode) getModel().getRoot();
//        }
//
//        //It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
//        ((DefaultTreeModel) getModel()).insertNodeInto(childNode, parent, parent.getChildCount());
//
//        //Make sure the user can see the lovely new node.
//        if (shouldBeVisible) {
//            scrollPathToVisible(new TreePath(childNode.getPath()));
//        }
//        return childNode;
//    }
}
