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

    public void setStatus(final TimerStatus status) {
        this.status = status;
    }

    public String getStartBtnText() {
        return startBtnText;
    }

    public void setStartBtnText(final String startBtnText) {
        this.startBtnText = startBtnText;
    }

    public Boolean getStopBtnEnabled() {
        return stopBtnEnabled;
    }

    public void setStopBtnEnabled(final Boolean stopBtnEnabled) {
        this.stopBtnEnabled = stopBtnEnabled;
    }
}
