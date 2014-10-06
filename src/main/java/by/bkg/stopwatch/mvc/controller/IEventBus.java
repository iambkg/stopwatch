package by.bkg.stopwatch.mvc.controller;

import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.model.enums.TimerStatus;
import by.bkg.stopwatch.mvc.view.StopWatchFrame;

/**
 * @author Alexey Baryshnev
 */
public interface IEventBus {

    void setFrame(StopWatchFrame stopWatchFrame);

    void showSplits();

    void onAddPersonConfirmed(Person person);

    /**
     * Reset UI components and controllers data
     */
    void resetAllData();

    void proceedAddPersonRequest();

    void handleNewTimerState(TimerStatus currentStatus);
}
