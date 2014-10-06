package by.bkg.stopwatch.mvc.controller;

import by.bkg.stopwatch.mvc.view.factory.ComponentFactory;

/**
 * Controller for the whole app.
 *
 * @author Alexey Baryshnev
 */
public class StopWatchAppController implements IStopWatchAppController {

    public void onAddPersonClick(IEventBus eventBus) {
        ComponentFactory.createAddPersonDialog(eventBus).setVisible(true);
    }
}
