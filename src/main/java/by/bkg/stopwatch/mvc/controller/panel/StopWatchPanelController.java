package by.bkg.stopwatch.mvc.controller.panel;

import by.bkg.stopwatch.mvc.model.AppConstants;
import by.bkg.stopwatch.mvc.model.enums.TimerStatus;
import by.bkg.stopwatch.mvc.controller.IEventBus;
import by.bkg.stopwatch.mvc.model.business.Person;
import by.bkg.stopwatch.mvc.model.business.Split;
import by.bkg.stopwatch.mvc.model.paneldata.StopWatchPanelData;
import by.bkg.stopwatch.mvc.view.panel.IStopWatchPanel;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import static by.bkg.stopwatch.mvc.model.enums.TimerStatus.PAUSED;
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

    private final StopWatchPanelData data;

    public StopWatchPanelController(IEventBus eventBus, IStopWatchPanel panel) {
        setPanel(panel);
        this.data = new StopWatchPanelData();
        setEventBus(eventBus);
    }

    public void onStart() {
        switch (getPanel().getTimerStatus()) {
            case STOPPED:
                getPanel().setTimerStatus(TimerStatus.RUNNING);
                getPanel().getStopWatch().start();
                break;
            case RUNNING:
                // already in this state
                break;
            case PAUSED:
                getPanel().setTimerStatus(TimerStatus.RUNNING);
                getPanel().getStopWatch().resume();
                break;
        }
        updatePanelByMode();
    }

    public void onStop() {
        switch (getPanel().getTimerStatus()) {
            case STOPPED:
                break;
            case RUNNING:
                getPanel().setTimerStatus(PAUSED);
                getPanel().getStopBtn().setText("Reset");
                getPanel().getStopWatch().suspend();
                break;
            case PAUSED:
                getPanel().setTimerStatus(STOPPED);
                getPanel().getStopWatch().stop();
                getPanel().getStopWatch().reset();
                getEventBus().resetAllData();
                break;
        }
        updatePanelByMode();
    }

    public void onSplit() {
        switch (getPanel().getTimerStatus()) {
            case STOPPED:
                break;
            case RUNNING:
                StopWatch stopWatch = getPanel().getStopWatch();
                stopWatch.split();
                Split split = new Split(getStartNumber(), stopWatch.getSplitTime(), stopWatch.toSplitString());
                stopWatch.unsplit();

                getData().addSplit(split);
                getEventBus().showSplits();

                break;
            case PAUSED:
                break;
        }
        updatePanelByMode();
    }

    private int getStartNumber() {
        // TODO ABA: read start number for split from input
        return AppConstants.NOT_ASSIGNED_START_NUMBER;
    }

    public String getCurrentTime() {
        if (getPanel().getStopWatch() == null) {
            return ZERO_TIME;
        }
        return getPanel().getStopWatch().toString();
    }

    public void updatePanelByMode() {
        updateEnabledByMode();
        updateBtnTextByMode();
    }

    private void updateEnabledByMode() {
        boolean startEnabled = false;
        boolean stopEnabled = false;
        boolean splitEnabled = false;

        switch (getPanel().getTimerStatus()) {
            case STOPPED:
                startEnabled = true;
                stopEnabled = false;
                splitEnabled = false;
                break;
            case RUNNING:
                startEnabled = false;
                stopEnabled = true;
                splitEnabled = true;
                break;
            case PAUSED:
                startEnabled = true;
                stopEnabled = true;
                splitEnabled = false;
                break;
        }
        getPanel().getStartBtn().setEnabled(startEnabled);
        getPanel().getStopBtn().setEnabled(stopEnabled);
        getPanel().getSplitBtn().setEnabled(splitEnabled);
    }

    private void updateBtnTextByMode() {
        String startText = "";
        String stopText = "";
        switch (getPanel().getTimerStatus()) {
            case STOPPED:
                startText = "Start";         // TODO ABA: i18n
                stopText = "Stop";           // TODO ABA: i18n
                break;
            case RUNNING:
                startText = "Resume";       // TODO ABA: i18n
                stopText = "Stop";          // TODO ABA: i18n
                break;
            case PAUSED:
                startText = "Resume";       // TODO ABA: i18n
                stopText = "Reset";         // TODO ABA: i18n
                break;
        }
        getPanel().getStartBtn().setText(startText);
        getPanel().getStopBtn().setText(stopText);
    }

    public StopWatchPanelData getData() {
        return data;
    }

    public void addPerson(Person person) {
        getData().addPerson(person);
    }

    public void clearData() {
        getData().clear();
    }
}
