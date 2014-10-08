package by.bkg.stopwatch.mvc.controller.panel;

import by.bkg.stopwatch.mvc.model.AppConstants;
import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.model.business.Split;
import by.bkg.stopwatch.mvc.model.enums.TimerStatus;
import by.bkg.stopwatch.mvc.model.paneldata.StopWatchPanelData;
import by.bkg.stopwatch.mvc.view.panel.IStopWatchPanel;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import static by.bkg.stopwatch.mvc.model.enums.TimerStatus.PAUSED;
import static by.bkg.stopwatch.mvc.model.enums.TimerStatus.RUNNING;
import static by.bkg.stopwatch.mvc.model.enums.TimerStatus.STOPPED;

/**
 * StopWatch controller.
 *
 * @author Alexey Baryshnev
 */
public class StopWatchPanelController extends GenericPanelController<IStopWatchPanel>
        implements IStopWatchPanelController {

    private static final Logger LOG = Logger.getLogger(StopWatchPanelController.class.getName());

    public static final String ZERO_TIME = "00:00:00.000";

    private StopWatchPanelData data;

    public StopWatchPanelController() {
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
                startText = "►";         // TODO ABA: i18n
                stopText = "■";           // TODO ABA: i18n
                break;
            case RUNNING:
                startText = "❙❙";       // TODO ABA: i18n
                stopText = "■";          // TODO ABA: i18n
                break;
            case PAUSED:
                startText = "►";       // TODO ABA: i18n
                stopText = "■";         // TODO ABA: i18n
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
