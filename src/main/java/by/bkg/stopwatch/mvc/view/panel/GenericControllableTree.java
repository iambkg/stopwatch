package by.bkg.stopwatch.mvc.view.panel;

import by.bkg.stopwatch.mvc.controller.IComponentController;
import by.bkg.stopwatch.mvc.view.IControllable;

import javax.swing.*;

/**
 * JTree with controller
 *
 * @author Alexey Baryshnev
 */
public class GenericControllableTree<T extends IComponentController> extends JTree implements IControllable<T> {

    private T controller;

    @Override
    public void setController(T controller) {
        this.controller = controller;
    }

    public T getController() {
        return controller;
    }
}
