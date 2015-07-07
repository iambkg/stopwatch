package by.bkg.stopwatch.desktop.view.v2.model;

/**
 * @author Alexey Baryshnev
 */
public class StopWatchPanelState {

    private String startBtnText;

    private Boolean stopBtnEnabled;

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
