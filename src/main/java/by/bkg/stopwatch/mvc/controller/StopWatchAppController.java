package by.bkg.stopwatch.mvc.controller;

import by.bkg.stopwatch.mvc.view.dialog.AddPersonDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controller for the whole app.
 *
 * @author Alexey Baryshnev
 */
@Component
public class StopWatchAppController implements IStopWatchAppController {

    @Autowired
    private AddPersonDialog addPersonDialog;

    @Autowired
    private IEventBus eventBus;

    @Override
    public void onAddPersonClick() {
        addPersonDialog.setVisible(true);
    }

    @Override
    public void onEnterStartNumber() {
        eventBus.proceedAddPersonRequest();
    }
}
