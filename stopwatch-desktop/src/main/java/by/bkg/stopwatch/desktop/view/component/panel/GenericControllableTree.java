package by.bkg.stopwatch.desktop.view.component.panel;

import by.bkg.stopwatch.desktop.view.controller.IComponentController;
import by.bkg.stopwatch.desktop.view.IControllable;
import by.bkg.stopwatch.desktop.view.IInitializable;
//import org.jdesktop.swingx.JXTreeTable;
//import org.jdesktop.swingx.treetable.TreeTableModel;

import javax.swing.*;
import javax.swing.tree.TreeModel;

/**
 * JTree with controller
 *
 * @author Alexey Baryshnev
 */
@Deprecated
public abstract class GenericControllableTree<T extends IComponentController> extends JTree
//public abstract class GenericControllableTree<M extends TreeTableModel, T extends IComponentController> extends JXTreeTable
        implements IControllable<T>, IInitializable {

    private T controller;

    private Boolean initialized = false;

//    public GenericControllableTree(M treeModel) {
    public GenericControllableTree(TreeModel treeModel) {
        super(treeModel);
    }

    public GenericControllableTree() {
        setInitialized(false);
    }

//    @SuppressWarnings(value = "unchecked")
//    public M getXTreeTableModel() {
//        return (M) super.getTreeTableModel();
//    }

    @Override
    public void setController(T controller) {
        this.controller = controller;
    }

    @Override
    public T getController() {
        return controller;
    }

    @Override
    public void setInitialized(Boolean initialized) {
        this.initialized = initialized;
    }

    @Override
    public Boolean isInitialized() {
        return initialized;
    }

    /**
     * Checks if component initialized. If not - makes init
     */
    @Override
    public void init() {
        if (isInitialized()) {
            return;
        }
        makeInit();
        setInitialized(true);
    }

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

    /**
     * All panel initializations here
     */
    @Override
    public abstract void makeInit();
}
