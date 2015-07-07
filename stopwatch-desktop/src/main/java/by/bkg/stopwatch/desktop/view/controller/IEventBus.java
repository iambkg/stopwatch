package by.bkg.stopwatch.desktop.view.controller;

import by.bkg.stopwatch.desktop.model.business.Person;
import by.bkg.stopwatch.desktop.view.model.enums.TimerStatus;
import by.bkg.stopwatch.desktop.view.StopWatchFrame;

/**
 * @author Alexey Baryshnev
 */
@Deprecated
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
