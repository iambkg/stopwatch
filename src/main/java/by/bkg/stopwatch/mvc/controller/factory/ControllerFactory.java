package by.bkg.stopwatch.mvc.controller.factory;

import by.bkg.stopwatch.mvc.controller.IEventBus;
import by.bkg.stopwatch.mvc.controller.IStopWatchAppController;
import by.bkg.stopwatch.mvc.controller.EventBus;
import by.bkg.stopwatch.mvc.controller.StopWatchAppController;
import by.bkg.stopwatch.mvc.controller.panel.RegisteredPersonsPanelController;
import by.bkg.stopwatch.mvc.controller.panel.StopWatchPanelController;
import by.bkg.stopwatch.mvc.controller.panel.IRegisteredPersonsPanelController;
import by.bkg.stopwatch.mvc.controller.panel.IStopWatchPanelController;
import by.bkg.stopwatch.mvc.view.panel.IRegisteredPersonsPanel;
import by.bkg.stopwatch.mvc.view.panel.IStopWatchPanel;

/**
 * Controller factory. TODO remove after Spring integration
 *
 * @author Alexey Baryshnev
 */
public final class ControllerFactory {

    private ControllerFactory() {
    }

    public static IStopWatchPanelController getStopWatchController(IEventBus eventBus, IStopWatchPanel panel) {
        return new StopWatchPanelController(eventBus, panel);
    }

    public static IRegisteredPersonsPanelController getRegisteredPersonsController(IEventBus eventBus, IRegisteredPersonsPanel panel) {
        return new RegisteredPersonsPanelController(eventBus, panel);
    }

    public static IEventBus getEventBus() {
        return new EventBus();
    }

    public static IStopWatchAppController getAppController() {
        return new StopWatchAppController();
    }
}
