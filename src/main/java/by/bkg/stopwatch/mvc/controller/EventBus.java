package by.bkg.stopwatch.mvc.controller;

import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.view.StopWatchFrame;

import javax.swing.*;

/**
 * Event bus for app.
 *
 * @author Alexey Baryshnev
 */
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
        stopWatchFrame.getResultsLabel().setText("C-label");
        stopWatchFrame.getStopWatchController().clearData();
    }
}
