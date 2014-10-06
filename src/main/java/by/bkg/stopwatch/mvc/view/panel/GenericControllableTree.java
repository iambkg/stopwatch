package by.bkg.stopwatch.mvc.view.panel;

import by.bkg.stopwatch.mvc.controller.IComponentController;
import by.bkg.stopwatch.mvc.view.IControllable;

import javax.swing.*;
import javax.swing.tree.TreeModel;

/**
 * JTree with controller
 *
 * @author Alexey Baryshnev
 */
public class GenericControllableTree<T extends IComponentController> extends JTree implements IControllable<T> {

    private T controller;

    public GenericControllableTree(TreeModel treeModel) {
        super(treeModel);
    }

    @Override
    public void setController(T controller) {
        this.controller = controller;
    }

    public T getController() {
        return controller;
    }
}
