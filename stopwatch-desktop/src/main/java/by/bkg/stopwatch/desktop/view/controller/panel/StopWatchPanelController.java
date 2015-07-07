package by.bkg.stopwatch.desktop.view.controller.panel;

import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.model.business.Person;
import by.bkg.stopwatch.desktop.model.business.Split;
import by.bkg.stopwatch.desktop.view.model.enums.TimerStatus;
import by.bkg.stopwatch.desktop.view.model.paneldata.StopWatchPanelData;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.component.panel.IStopWatchPanel;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import javax.inject.Inject;

import static by.bkg.stopwatch.desktop.view.model.enums.TimerStatus.PAUSED;
import static by.bkg.stopwatch.desktop.view.model.enums.TimerStatus.RUNNING;
import static by.bkg.stopwatch.desktop.view.model.enums.TimerStatus.STOPPED;

/**
 * StopWatch controller.
 *
 * @author Alexey Baryshnev
 */
@Deprecated
public class StopWatchPanelController extends GenericPanelController<IStopWatchPanel>
        implements IStopWatchPanelController {

    private static final Logger LOG = Logger.getLogger(StopWatchPanelController.class.getName());

    public static final String ZERO_TIME = "00:00:00.000";

    private StopWatchPanelData data;

    private AppMessages appMessages;

//    @Inject
    public StopWatchPanelController(AppMessages appMessages) {
        this.appMessages = appMessages;
        this.data = new StopWatchPanelData();
    }

    @Override
    public void onStart() {
        switch (getPanel().getTimerStatus()) {
            case STOPPED:
                getEventBus().handleNewTimerState(TimerStatus.RUNNING);
                getPanel().getStopWatch().start();
                break;
            case RUNNING:
                getEventBus().handleNewTimerState(PAUSED);
                getPanel().getStopWatch().suspend();
                break;
            case PAUSED:
                getEventBus().handleNewTimerState(TimerStatus.RUNNING);
                getPanel().getStopWatch().resume();
                break;
        }
        updatePanelByMode();
    }

    @Override
    public void onStop() {
        switch (getPanel().getTimerStatus()) {
            case STOPPED:
                break;
            case RUNNING:
            case PAUSED:
                getEventBus().handleNewTimerState(STOPPED);
                getPanel().getStopWatch().stop();
                getPanel().getStopWatch().reset();
                getEventBus().resetAllData();
                break;
        }
        updatePanelByMode();
    }

    @Override
    public void onSplit() {
        if (RUNNING.equals(getPanel().getTimerStatus())) {
            // call bus as not all required data available from this controller
            getEventBus().proceedAddPersonRequest();
        }
        updatePanelByMode();
    }

    @Override
    public void makeSplit(String startNumber) {
        StopWatch stopWatch = getPanel().getStopWatch();
        stopWatch.split();
        Split split = new Split(startNumber, stopWatch.getSplitTime(), stopWatch.toSplitString());
        stopWatch.unsplit();

        getData().addSplit(split);
        getEventBus().showSplits();
    }

    @Override
    public String getCurrentTime() {
        if (getPanel().getStopWatch() == null) {
            return ZERO_TIME;
        }
        return getPanel().getStopWatch().toString();
    }

    @Override
    public void updatePanelByMode() {
        updateEnabledByMode();
        updateBtnTextByMode();
    }

    private void updateEnabledByMode() {
        boolean stopEnabled = false;
        boolean splitEnabled = false;

        switch (getPanel().getTimerStatus()) {
            case RUNNING:
                stopEnabled = true;
                splitEnabled = true;
                break;
            case PAUSED:
                stopEnabled = true;
                break;
        }
        getPanel().getStartBtn().setEnabled(true);
        getPanel().getStopBtn().setEnabled(stopEnabled);
        getPanel().getSplitBtn().setEnabled(splitEnabled);
    }

    private void updateBtnTextByMode() {
        String startText = AppConstants.EMPTY_STRING;
        String stopText = AppConstants.EMPTY_STRING;
        switch (getPanel().getTimerStatus()) {
            case STOPPED:
                startText = appMessages.getString("btn.start");
                stopText = appMessages.getString("btn.stop");
                break;
            case RUNNING:
                startText = appMessages.getString("btn.pause");
                stopText = appMessages.getString("btn.stop");
                break;
            case PAUSED:
                startText = appMessages.getString("btn.start");
                stopText = appMessages.getString("btn.stop");
                break;
        }
        getPanel().getStartBtn().setText(startText);
        getPanel().getStopBtn().setText(stopText);
    }

    @Override
    public StopWatchPanelData getData() {
        return data;
    }

    @Override
    public void addPerson(Person person) {
        getData().addPerson(person);
    }

    @Override
    public void clearData() {
        getData().clear();
    }
}
