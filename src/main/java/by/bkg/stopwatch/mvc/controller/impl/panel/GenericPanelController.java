package by.bkg.stopwatch.mvc.controller.impl.panel;

import by.bkg.stopwatch.mvc.controller.EventBus;
import by.bkg.stopwatch.mvc.controller.IComponentController;
import by.bkg.stopwatch.mvc.view.IControllable;

/**
 * Generic controller for IControllable Panels
 *
 * @author Alexey Baryshnev
 */
public class GenericPanelController<T extends IControllable> implements IComponentController<T> {

    private T panel;

    private EventBus eventBus;

    @Override
    public T getPanel() {
        return panel;
    }

    public void setPanel(T panel) {
        this.panel = panel;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
