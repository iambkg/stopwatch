package by.bkg.stopwatch.desktop.view.component.controller;

import by.bkg.stopwatch.core.service.ILogicService;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.enums.TimerStatus;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.model.StopWatchPanelState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static by.bkg.stopwatch.desktop.view.model.enums.TimerStatus.PAUSED;
import static by.bkg.stopwatch.desktop.view.model.enums.TimerStatus.RUNNING;
import static by.bkg.stopwatch.desktop.view.model.enums.TimerStatus.STOPPED;

/**
 * @author Alexey Baryshnev
 */
@Controller
public class StopWatchPanelController {    // TODO ABA: add "implenets"

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
    public void onStart(final Callback<StopWatchPanelState> callback) {
        StopWatchPanelState state = new StopWatchPanelState();

        switch (getTimerStatus()) {
            case STOPPED:
                state.setStopBtnEnabled(false);
                state.setStartBtnTooltipText(appMessages.getString("btn.pause"));
                state.setStartBtnIcon("icons/x24/Timer-Pause.png");
                state.setStatus(RUNNING);
                setTimerStatus(RUNNING);
                logicService.startEvent();
                break;
            case RUNNING:
                state.setStopBtnEnabled(true);
                state.setStartBtnTooltipText(appMessages.getString("btn.start"));
                state.setStartBtnIcon("icons/x24/Timer-Start.png");
                state.setStatus(PAUSED);
                setTimerStatus(PAUSED);
                logicService.suspendEvent();
                break;
            case PAUSED:
                state.setStopBtnEnabled(false);
                state.setStartBtnTooltipText(appMessages.getString("btn.pause"));
                state.setStartBtnIcon("icons/x24/Timer-Pause.png");
                state.setStatus(RUNNING);
                setTimerStatus(RUNNING);
                logicService.resumeEvent();
                break;
            default:
                break;
        }
        callback.execute(state);
    }

//    @Override
    public void onStop(final Callback<StopWatchPanelState> callback) {
        StopWatchPanelState state = new StopWatchPanelState();

        switch (getTimerStatus()) {
            case PAUSED:
                state.setStopBtnEnabled(false);
                state.setStartBtnTooltipText(appMessages.getString("btn.start"));
                state.setStartBtnIcon("icons/x24/Timer-Start.png");
                state.setStatus(STOPPED);
                setTimerStatus(STOPPED);
                logicService.stopEvent();
                break;
            default:
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
    public void setTimerStatus(final TimerStatus timerStatus) {
        this.timerStatus = timerStatus;
    }
}
