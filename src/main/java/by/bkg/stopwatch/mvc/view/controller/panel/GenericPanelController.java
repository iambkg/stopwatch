package by.bkg.stopwatch.mvc.view.controller.panel;

import by.bkg.stopwatch.mvc.view.controller.IComponentController;
import by.bkg.stopwatch.mvc.view.controller.IEventBus;
import by.bkg.stopwatch.mvc.view.IControllable;

/**
 * Generic controller for IControllable Panels
 *
 * @author Alexey Baryshnev
 */
public class GenericPanelController<T extends IControllable> implements IComponentController<T> {

    private T panel;

    private IEventBus eventBus;

    @Override
    public T getPanel() {
        return panel;
    }

    public void setPanel(T panel) {
        this.panel = panel;
    }

    public IEventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(IEventBus eventBus) {
        this.eventBus = eventBus;
    }
}
