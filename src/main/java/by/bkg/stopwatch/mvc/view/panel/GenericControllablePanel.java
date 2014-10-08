package by.bkg.stopwatch.mvc.view.panel;

import by.bkg.stopwatch.mvc.controller.IComponentController;
import by.bkg.stopwatch.mvc.view.IControllable;
import by.bkg.stopwatch.mvc.view.IInitializable;

import javax.swing.*;

/**
 * JPanel with controller.
 *
 * @author Alexey Baryshnev
 */
public abstract class GenericControllablePanel<T extends IComponentController> extends JPanel
        implements IControllable<T>, IInitializable {

    private T controller;

    private Boolean initialized = false;

    public GenericControllablePanel() {
        setInitialized(false);
    }

    @Override
    public void setController(T controller) {
        this.controller = controller;
    }

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

    /**
     * All panel initializations here
     */
    @Override
    public abstract void makeInit();
}
