package by.bkg.stopwatch.common.factory;

import by.bkg.stopwatch.mvc.controller.EventBus;
import by.bkg.stopwatch.mvc.controller.impl.panel.StopWatchPanelController;
import by.bkg.stopwatch.mvc.view.impl.panel.StopWatchPanel;

/**
 * Controller factory. TODO remove after Spring integration
 *
 * @author Alexey Baryshnev
 */
public final class ControllerFactory {

    private ControllerFactory() {
    }

    public static StopWatchPanelController getStopWatchController(EventBus eventBus, StopWatchPanel panel) {
        return new StopWatchPanelController(eventBus, panel);
    }
}
