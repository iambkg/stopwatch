package by.bkg.stopwatch.desktop.view.controller;

import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.model.business.Person;
import by.bkg.stopwatch.desktop.view.model.enums.TimerStatus;
import by.bkg.stopwatch.desktop.view.StopWatchFrame;

import javax.swing.*;

/**
 * Event bus for app.
 *
 * @author Alexey Baryshnev
 */
@Deprecated
public class EventBus implements IEventBus {

    private StopWatchFrame stopWatchFrame;

    public void setFrame(StopWatchFrame stopWatchFrame) {
        this.stopWatchFrame = stopWatchFrame;
    }

    public void showSplits() {
        JLabel resultsLabel = stopWatchFrame.getResultsLabel();
        resultsLabel.setText(stopWatchFrame.getStopWatchController().getData().getDataAsString());
    }

    public void onAddPersonConfirmed(Person person) {
        stopWatchFrame.getStopWatchController().addPerson(person);
        stopWatchFrame.getRegisteredPersonsController().addPerson(person);
    }

    public void resetAllData() {
        stopWatchFrame.getResultsLabel().setText(AppConstants.EMPTY_STRING);
        stopWatchFrame.getStopWatchController().clearData();
    }

    public void proceedAddPersonRequest() {
        stopWatchFrame.getStopWatchController().makeSplit(stopWatchFrame.getTypedStartNumber());
        stopWatchFrame.clearTypedStartNumber();
    }

    public void handleNewTimerState(TimerStatus currentStatus) {
        stopWatchFrame.getStopWatchPanel().setTimerStatus(currentStatus);
        stopWatchFrame.getStopWatchController().updatePanelByMode();
        stopWatchFrame.setStartNumberFieldEnabled(TimerStatus.RUNNING.equals(currentStatus));
    }
}
