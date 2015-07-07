package by.bkg.stopwatch.desktop.view.component.panel;

import by.bkg.stopwatch.desktop.view.model.enums.TimerStatus;
import by.bkg.stopwatch.desktop.view.controller.panel.IStopWatchPanelController;
import by.bkg.stopwatch.desktop.view.IControllable;
import org.apache.commons.lang.time.StopWatch;

import javax.swing.*;

/**
 * @author Alexey Baryshnev
 */
@Deprecated
public interface IStopWatchPanel extends IControllable<IStopWatchPanelController> {

    void init();

    TimerStatus getTimerStatus();

    void setTimerStatus(TimerStatus timerStatus);

    JButton getStartBtn();

    JButton getStopBtn();

    JButton getSplitBtn();

    StopWatch getStopWatch();
}
