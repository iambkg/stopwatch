package by.bkg.stopwatch.mvc.view.panel;

import by.bkg.stopwatch.mvc.model.enums.TimerStatus;
import by.bkg.stopwatch.mvc.controller.panel.IStopWatchPanelController;
import by.bkg.stopwatch.mvc.view.IControllable;
import org.apache.commons.lang.time.StopWatch;

import javax.swing.*;

/**
 * @author Alexey Baryshnev
 */
public interface IStopWatchPanel extends IControllable<IStopWatchPanelController> {

    void init();

    TimerStatus getTimerStatus();

    void setTimerStatus(TimerStatus timerStatus);

    JButton getStartBtn();

    JButton getStopBtn();

    JButton getSplitBtn();

    StopWatch getStopWatch();
}
