package by.bkg.stopwatch.mvc.controller;

import by.bkg.stopwatch.mvc.view.dialog.AddPersonDialog;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Controller for the whole app.
 *
 * @author Alexey Baryshnev
 */
@Component
public class StopWatchAppController implements IStopWatchAppController {

    @Inject
    private AddPersonDialog addPersonDialog;

    @Inject
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
