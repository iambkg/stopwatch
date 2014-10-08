package by.bkg.stopwatch.mvc.view.panel;

import by.bkg.stopwatch.mvc.controller.IComponentController;
import by.bkg.stopwatch.mvc.view.IControllable;
import by.bkg.stopwatch.mvc.view.IInitializable;

import javax.swing.*;
import javax.swing.tree.TreeModel;

/**
 * JTree with controller
 *
 * @author Alexey Baryshnev
 */
public abstract class GenericControllableTree<T extends IComponentController> extends JTree
        implements IControllable<T>, IInitializable {

    private T controller;

    private Boolean initialized = false;

    public GenericControllableTree(TreeModel treeModel) {
        super(treeModel);
    }

    public GenericControllableTree() {
        setInitialized(false);
    }

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
