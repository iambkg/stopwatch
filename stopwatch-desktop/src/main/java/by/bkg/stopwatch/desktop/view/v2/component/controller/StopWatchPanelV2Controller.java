package by.bkg.stopwatch.desktop.view.v2.component.controller;

import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.enums.TimerStatus;
import by.bkg.stopwatch.desktop.view.v2.model.Callback;
import by.bkg.stopwatch.desktop.view.v2.model.StopWatchPanelState;
import org.apache.commons.lang.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static by.bkg.stopwatch.desktop.view.model.enums.TimerStatus.*;

/**
 * @author Alexey Baryshnev
 */
@Controller
public class StopWatchPanelV2Controller {

    @Autowired
    private AppMessages appMessages;

    public static final String ZERO_TIME = "00:00:00.000";

    private TimerStatus timerStatus;

    private StopWatch stopWatch;

    public void init() {
        stopWatch = new StopWatch();
        setTimerStatus(STOPPED);
    }

    //    @Override
    public void onStart(Callback<StopWatchPanelState> callback) {
        StopWatchPanelState state = new StopWatchPanelState();

        switch (getTimerStatus()) {
            case STOPPED:
                state.setStopBtnEnabled(false);
                state.setStartBtnText(appMessages.getString("btn.start"));
                setTimerStatus(RUNNING);
                stopWatch.start();
                break;
            case RUNNING:
                state.setStopBtnEnabled(true);
                state.setStartBtnText(appMessages.getString("btn.pause"));
                setTimerStatus(PAUSED);
                stopWatch.suspend();
                break;
            case PAUSED:
                state.setStopBtnEnabled(true);
                state.setStartBtnText(appMessages.getString("btn.start"));
                setTimerStatus(RUNNING);
                stopWatch.resume();
                break;
        }
        callback.execute(state);
    }

    //    @Override
    public void onStop(Callback<StopWatchPanelState> callback) {
        StopWatchPanelState state = new StopWatchPanelState();

        switch (getTimerStatus()) {
            case STOPPED:
//                state.setStopBtnEnabled(false);
                state.setStartBtnText(appMessages.getString("btn.start"));
                break;
            case RUNNING:
//                state.setStopBtnEnabled(true);
                state.setStartBtnText(appMessages.getString("btn.pause"));
                setTimerStatus(STOPPED);
                stopWatch.stop();
                stopWatch.reset();
//                    getEventBus().resetAllData();
                break;
            case PAUSED:
//                state.setStopBtnEnabled(false);
                state.setStartBtnText(appMessages.getString("btn.start"));
                setTimerStatus(STOPPED);
                stopWatch.stop();
                stopWatch.reset();
//                    getEventBus().resetAllData();
                break;
        }
        callback.execute(state);
    }

    //    @Override
    public String getCurrentTime() {
        if (stopWatch == null) {
            return ZERO_TIME;
        }
        return stopWatch.toString();
    }

    //    @Override
    public TimerStatus getTimerStatus() {
        return timerStatus;
    }

    //    @Override
    public void setTimerStatus(TimerStatus timerStatus) {
        this.timerStatus = timerStatus;
    }
}
