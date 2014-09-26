package by.bkg.timer.mvc.controller.impl.panel;

import by.bkg.timer.mvc.controller.IPanelController;
import by.bkg.timer.mvc.view.IControllable;

/**
 * Generic controller for IControllable Panels
 *
 * @author Alexey Baryshnev
 */
public class GenericPanelController<T extends IControllable> implements IPanelController<T> {

    private T panel;

    @Override
    public T getPanel() {
        return panel;
    }

    public void setPanel(T panel) {
        this.panel = panel;
    }
}
