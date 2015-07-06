package by.bkg.stopwatch.mvc.view.controller.panel;

import by.bkg.stopwatch.mvc.view.controller.IComponentController;
import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.view.model.paneldata.StopWatchPanelData;
import by.bkg.stopwatch.mvc.view.component.panel.IStopWatchPanel;

/**
 * @author Alexey Baryshnev
 */
public interface IStopWatchPanelController extends IComponentController<IStopWatchPanel> {

    /**
     * What happens on "Start" button click. Generally - should make call to bus
     */
    void onStart();

    /**
     * What happens on "Stop" button click. Generally - should make call to bus
     */
    void onStop();

    /**
     * What happens on "Split" button click. Generally - should make call to bus
     */
    void onSplit();

    /**
     * Add split into data
     *
     * @param startNumber number for which split is made
     */
    void makeSplit(String startNumber);

    String getCurrentTime();

    void updatePanelByMode();

    StopWatchPanelData getData();

    void addPerson(Person person);

    void clearData();
}
