package by.bkg.stopwatch.desktop.view.model;

import by.bkg.stopwatch.desktop.view.model.enums.TimerStatus;

/**
 * @author Alexey Baryshnev
 */
public class StopWatchPanelState {

    private TimerStatus status;

    private String startBtnText;

    private Boolean stopBtnEnabled;

    public TimerStatus getStatus() {
        return status;
    }

    public void setStatus(TimerStatus status) {
        this.status = status;
    }

    public String getStartBtnText() {
        return startBtnText;
    }

    public void setStartBtnText(String startBtnText) {
        this.startBtnText = startBtnText;
    }

    public Boolean getStopBtnEnabled() {
        return stopBtnEnabled;
    }

    public void setStopBtnEnabled(Boolean stopBtnEnabled) {
        this.stopBtnEnabled = stopBtnEnabled;
    }
}
