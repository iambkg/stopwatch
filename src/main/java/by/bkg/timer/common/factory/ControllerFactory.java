package by.bkg.timer.common.factory;

import by.bkg.timer.mvc.controller.impl.panel.StopWatchPanelController;
import by.bkg.timer.mvc.view.impl.panel.StopWatchPanel;

/**
 * Controller factory. TODO remove after Spring integration
 *
 * @author Alexey Baryshnev
 */
public final class ControllerFactory {

    private ControllerFactory() {
    }

    public static StopWatchPanelController getStopWatchController(StopWatchPanel panel) {
        return new StopWatchPanelController(panel);
    }
}
