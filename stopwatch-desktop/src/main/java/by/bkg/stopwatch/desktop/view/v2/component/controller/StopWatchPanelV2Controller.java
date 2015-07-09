package by.bkg.stopwatch.desktop.view.v2.component.controller;

import by.bkg.stopwatch.core.service.TimingService;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.enums.TimerStatus;
import by.bkg.stopwatch.desktop.view.v2.model.Callback;
import by.bkg.stopwatch.desktop.view.v2.model.StopWatchPanelState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static by.bkg.stopwatch.desktop.view.model.enums.TimerStatus.PAUSED;
import static by.bkg.stopwatch.desktop.view.model.enums.TimerStatus.RUNNING;
import static by.bkg.stopwatch.desktop.view.model.enums.TimerStatus.STOPPED;

/**
 * @author Alexey Baryshnev
 */
@Controller
public class StopWatchPanelV2Controller {

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private TimingService timingService;

    private TimerStatus timerStatus;

    public void init() {
        timingService.init();
        setTimerStatus(STOPPED);
    }

    //    @Override
    public void onStart(Callback<StopWatchPanelState> callback) {
        StopWatchPanelState state = new StopWatchPanelState();

        switch (getTimerStatus()) {
            case STOPPED:
                state.setStopBtnEnabled(false);
                state.setStartBtnText(appMessages.getString("btn.pause"));
                setTimerStatus(RUNNING);
                timingService.start();
                break;
            case RUNNING:
                state.setStopBtnEnabled(true);
                state.setStartBtnText(appMessages.getString("btn.start"));
                setTimerStatus(PAUSED);
                timingService.suspend();
                break;
            case PAUSED:
                state.setStopBtnEnabled(false);
                state.setStartBtnText(appMessages.getString("btn.pause"));
                setTimerStatus(RUNNING);
                timingService.resume();
                break;
        }
        callback.execute(state);
    }

    //    @Override
    public void onStop(Callback<StopWatchPanelState> callback) {
        StopWatchPanelState state = new StopWatchPanelState();

        switch (getTimerStatus()) {
            case STOPPED:
                state.setStopBtnEnabled(true);
                state.setStartBtnText(appMessages.getString("btn.start"));
                break;
            case RUNNING:
                state.setStopBtnEnabled(false);
                state.setStartBtnText(appMessages.getString("btn.pause"));
                setTimerStatus(STOPPED);
                timingService.stop();
                timingService.reset();
//                    getEventBus().resetAllData();
                break;
            case PAUSED:
                state.setStopBtnEnabled(true);
                state.setStartBtnText(appMessages.getString("btn.start"));
                setTimerStatus(STOPPED);
                timingService.stop();
                timingService.reset();
//                    getEventBus().resetAllData();
                break;
        }
        callback.execute(state);
    }

    //    @Override
    public String getCurrentTime() {
        return timingService.getCurrentTime();
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
