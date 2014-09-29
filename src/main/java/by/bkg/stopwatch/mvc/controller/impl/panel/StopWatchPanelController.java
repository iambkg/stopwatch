package by.bkg.stopwatch.mvc.controller.impl.panel;

import by.bkg.stopwatch.common.enums.TimerStatus;
import by.bkg.stopwatch.mvc.model.Split;
import by.bkg.stopwatch.mvc.view.impl.panel.StopWatchPanel;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import static by.bkg.stopwatch.common.enums.TimerStatus.PAUSED;
import static by.bkg.stopwatch.common.enums.TimerStatus.STOPPED;

/**
 * StopWatch controller.
 *
 * @author Alexey Baryshnev
 */
public class StopWatchPanelController extends GenericPanelController<StopWatchPanel> {

    private static final Logger LOG = Logger.getLogger(StopWatchPanel.class.getName());

    public static final String ZERO_TIME = "00:00:00.000";

    public StopWatchPanelController(StopWatchPanel panel) {
        setPanel(panel);
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

                Split split = new Split(0, stopWatch.getSplitTime(), stopWatch.toSplitString());
                getPanel().showSplitTime(split);

                stopWatch.unsplit();
                break;
            case PAUSED:
                break;
        }
        updatePanelByMode();
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

    public void updateEnabledByMode() {
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

    public void updateBtnTextByMode() {
        String startText = "";
        String stopText = "";
        switch (getPanel().getTimerStatus()) {
            case STOPPED:
                startText = "Start";
                stopText = "Stop";
                break;
            case RUNNING:
                startText = "Resume";
                stopText = "Stop";
                break;
            case PAUSED:
                startText = "Resume";
                stopText = "Reset";
                break;
        }
        getPanel().getStartBtn().setText(startText);
        getPanel().getStopBtn().setText(stopText);
    }
}
