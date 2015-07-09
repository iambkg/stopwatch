package by.bkg.stopwatch.desktop.view.component.panel;

import javax.swing.*;
import javax.swing.tree.TreeModel;

//import org.jdesktop.swingx.JXTreeTable;
//import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 * JTree with controller
 *
 * @author Alexey Baryshnev
 */
@Deprecated
public abstract class GenericControllableTree extends JTree
//public abstract class GenericControllableTree<M extends TreeTableModel, T extends IComponentController> extends JXTreeTable
        {

//    private T controller;

    private Boolean initialized = false;

//    public GenericControllableTree(M treeModel) {
    public GenericControllableTree(TreeModel treeModel) {
        super(treeModel);
    }

//    @SuppressWarnings(value = "unchecked")
//    public M getXTreeTableModel() {
//        return (M) super.getTreeTableModel();
//    }

//    @Override
//    public void setController(T controller) {
//        this.controller = controller;
//    }
//
//    @Override
//    public T getController() {
//        return controller;
//    }

    public void expandAll() {
        for (int i = 0; i < getRowCount(); i++) {
            expandRow(i);
        }
    }

    public void collapseAll() {
        for (int i = 0; i < getRowCount(); i++) {
            collapseRow(i);
        }
    }
}
