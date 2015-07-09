package by.bkg.stopwatch.desktop.view.v2.component.controller;

import by.bkg.stopwatch.core.service.ILogicService;
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
    private ILogicService logicService;

    private TimerStatus timerStatus;

    public void init() {
        logicService.init();
        setTimerStatus(STOPPED);
    }

    //    @Override
    public void onStart(Callback<StopWatchPanelState> callback) {
        StopWatchPanelState state = new StopWatchPanelState();

        switch (getTimerStatus()) {
            case STOPPED:
                state.setStopBtnEnabled(false);
                state.setStartBtnText(appMessages.getString("btn.pause"));
                state.setStatus(RUNNING);
                setTimerStatus(RUNNING);
                logicService.startEvent();
                break;
            case RUNNING:
                state.setStopBtnEnabled(true);
                state.setStartBtnText(appMessages.getString("btn.start"));
                state.setStatus(PAUSED);
                setTimerStatus(PAUSED);
                logicService.suspendEvent();
                break;
            case PAUSED:
                state.setStopBtnEnabled(false);
                state.setStartBtnText(appMessages.getString("btn.pause"));
                state.setStatus(RUNNING);
                setTimerStatus(RUNNING);
                logicService.resumeEvent();
                break;
        }
        callback.execute(state);
    }

    //    @Override
    public void onStop(Callback<StopWatchPanelState> callback) {
        StopWatchPanelState state = new StopWatchPanelState();

        switch (getTimerStatus()) {
            case PAUSED:
                state.setStopBtnEnabled(false);
                state.setStartBtnText(appMessages.getString("btn.start"));
                state.setStatus(STOPPED);
                setTimerStatus(STOPPED);
                logicService.stopEvent();
//                    getEventBus().resetAllData();
                break;
        }
        callback.execute(state);
    }

    //    @Override
    public String getCurrentTime() {
        return logicService.getCurrentTime();
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
