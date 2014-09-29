package by.bkg.stopwatch.mvc.view.impl.panel;

import by.bkg.stopwatch.mvc.controller.IPanelController;
import by.bkg.stopwatch.mvc.view.IControllable;

import javax.swing.*;

/**
 * JPanel with controller.
 *
 * @author Alexey Baryshnev
 */
public class GenericControllablePanel<T extends IPanelController> extends JPanel implements IControllable<T> {

    private T controller;

    @Override
    public void setController(T controller) {
        this.controller = controller;
    }

    public T getController() {
        return controller;
    }
}
