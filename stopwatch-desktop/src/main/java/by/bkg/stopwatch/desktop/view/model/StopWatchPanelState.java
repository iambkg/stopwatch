package by.bkg.stopwatch.desktop.view.model;

import by.bkg.stopwatch.desktop.view.model.enums.TimerStatus;

/**
 * @author Alexey Baryshnev
 */
public class StopWatchPanelState {

    private TimerStatus status;

    private String startBtnTooltipText;

    private Boolean stopBtnEnabled;

    private String startBtnIcon;

    public TimerStatus getStatus() {
        return status;
    }

    public void setStatus(final TimerStatus status) {
        this.status = status;
    }

    public String getStartBtnTooltipText() {
        return startBtnTooltipText;
    }

    public void setStartBtnTooltipText(final String startBtnTooltipText) {
        this.startBtnTooltipText = startBtnTooltipText;
    }

    public Boolean getStopBtnEnabled() {
        return stopBtnEnabled;
    }

    public void setStopBtnEnabled(final Boolean stopBtnEnabled) {
        this.stopBtnEnabled = stopBtnEnabled;
    }

    public String getStartBtnIcon() {
        return startBtnIcon;
    }

    public void setStartBtnIcon(String startBtnIcon) {
        this.startBtnIcon = startBtnIcon;
    }
}
