package by.bkg.stopwatch.mvc.controller.panel;

import by.bkg.stopwatch.mvc.controller.IComponentController;
import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.model.paneldata.StopWatchPanelData;
import by.bkg.stopwatch.mvc.view.panel.IStopWatchPanel;

/**
 * @author Alexey Baryshnev
 */
public interface IStopWatchPanelController extends IComponentController<IStopWatchPanel> {

    void onStart();

    void onStop();

    void onSplit();

    String getCurrentTime();

    void updatePanelByMode();

    StopWatchPanelData getData();

    void addPerson(Person person);

    void clearData();
}
