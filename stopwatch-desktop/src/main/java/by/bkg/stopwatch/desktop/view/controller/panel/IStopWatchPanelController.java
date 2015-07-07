package by.bkg.stopwatch.desktop.view.controller.panel;

import by.bkg.stopwatch.desktop.view.controller.IComponentController;
import by.bkg.stopwatch.desktop.model.business.Person;
import by.bkg.stopwatch.desktop.view.model.paneldata.StopWatchPanelData;
import by.bkg.stopwatch.desktop.view.component.panel.IStopWatchPanel;

/**
 * @author Alexey Baryshnev
 */
@Deprecated
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
