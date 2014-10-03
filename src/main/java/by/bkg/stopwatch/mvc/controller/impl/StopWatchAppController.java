package by.bkg.stopwatch.mvc.controller.impl;

import by.bkg.stopwatch.common.factory.view.ComponentFactory;
import by.bkg.stopwatch.mvc.controller.EventBus;

/**
 * Controller for the whole app.
 *
 * @author Alexey Baryshnev
 */
public class StopWatchAppController {

    public void onAddPersonClick(EventBus eventBus) {
        ComponentFactory.createAddPersonDialog(eventBus).setVisible(true);
    }
}
