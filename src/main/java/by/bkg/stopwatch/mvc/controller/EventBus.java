package by.bkg.stopwatch.mvc.controller;

import by.bkg.stopwatch.mvc.model.Person;
import by.bkg.stopwatch.mvc.view.impl.StopWatchFrame;

import javax.swing.*;

/**
 * Event bus for app.
 *
 * @author Alexey Baryshnev
 */
public class EventBus {

    private StopWatchFrame stopWatchFrame;

    public void setFrame(StopWatchFrame stopWatchFrame) {
        this.stopWatchFrame = stopWatchFrame;
    }

    public void showSplits() {
        JLabel resultsLabel = stopWatchFrame.getResultsLabel();
        resultsLabel.setText(stopWatchFrame.getStopWatchController().getData().getDataAsString());
    }

    public void addPerson(Person person) {
        stopWatchFrame.getStopWatchController().getData().addPerson(person);
    }

    public void resetAll() {
        stopWatchFrame.getResultsLabel().setText("C-label");
    }
}
