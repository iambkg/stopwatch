package by.bkg.stopwatch.mvc.view.panel;

import by.bkg.stopwatch.mvc.controller.IComponentController;
import by.bkg.stopwatch.mvc.view.IControllable;

import javax.swing.*;

/**
 * JPanel with controller.
 *
 * @author Alexey Baryshnev
 */
public class GenericControllablePanel<T extends IComponentController> extends JPanel implements IControllable<T> {

    private T controller;

    @Override
    public void setController(T controller) {
        this.controller = controller;
    }

    public T getController() {
        return controller;
    }
}
