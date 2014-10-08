package by.bkg.stopwatch.mvc.view.panel;

import by.bkg.stopwatch.mvc.controller.panel.IStopWatchPanelController;
import by.bkg.stopwatch.mvc.model.enums.TimerStatus;
import by.bkg.stopwatch.mvc.view.i18n.AppMessages;
import org.apache.commons.lang.time.StopWatch;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import static by.bkg.stopwatch.mvc.model.enums.TimerStatus.STOPPED;

/**
 * Panel with stopwatch and control buttons
 *
 * @author Alexey Baryshnev
 */
public class StopWatchPanel extends GenericControllablePanel<IStopWatchPanelController> implements IStopWatchPanel {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 100;

    private org.apache.commons.lang.time.StopWatch stopWatch;
    private JButton startBtn;
    private JButton stopBtn;
    private JButton splitBtn;
    private JLabel timeLabel;
    private TimerStatus timerStatus;

    @Inject
    public StopWatchPanel(AppMessages appMessages) {
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (getController() != null) {
                            getTimeLabel().setText(getController().getCurrentTime());
                        }
                    }
                });
            }
        }, 0, 1);
        setTimerStatus(STOPPED);

//        Font myClockFont = new Font("Serif", Font.PLAIN, 30);
//        getTimeLabel().setFont(myClockFont);

        startBtn = new JButton(appMessages.getString("btn.start"));
        stopBtn = new JButton(appMessages.getString("btn.stop"));
        splitBtn = new JButton(appMessages.getString("btn.split"));

        stopWatch = new org.apache.commons.lang.time.StopWatch();

        getStartBtn().addActionListener(getStartBtnListener());
        getStopBtn().addActionListener(getStopBtnListener());
        getSplitBtn().addActionListener(getSplitBtnListener());
    }

    /**
     * Listener for "Split" button
     *
     * @return action listener
     */
    private ActionListener getSplitBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                getController().onSplit();
            }
        };
    }

    /**
     * Listener for "Stop" button
     *
     * @return action listener
     */
    private ActionListener getStopBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                getController().onStop();
            }
        };
    }

    /**
     * Listener for "Start" button
     *
     * @return action listener
     */
    private ActionListener getStartBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                getController().onStart();
            }
        };
    }

    @Override
    public void makeInit() {
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
//        setBackground(Color.orange);

        setLayout(new BorderLayout());
        add(buildNorthPanel(), BorderLayout.NORTH);
        add(buildSouthPanel(), BorderLayout.SOUTH);

        getController().updatePanelByMode();
    }

    private JPanel buildNorthPanel() {
        JPanel topPanel = new JPanel();
//        topPanel.setBackground(Color.orange);
        topPanel.add(getTimeLabel());
        return topPanel;
    }

    private JPanel buildSouthPanel() {
        JPanel bottomPanel = new JPanel();
//        bottomPanel.setBackground(Color.yellow);
        bottomPanel.add(getStartBtn());
        bottomPanel.add(getStopBtn());
        bottomPanel.add(getSplitBtn());
        return bottomPanel;
    }

    @Override
    public TimerStatus getTimerStatus() {
        return timerStatus;
    }

    @Override
    public void setTimerStatus(TimerStatus timerStatus) {
        this.timerStatus = timerStatus;
    }

    @Override
    public JButton getStartBtn() {
        return startBtn;
    }

    @Override
    public JButton getStopBtn() {
        return stopBtn;
    }

    @Override
    public JButton getSplitBtn() {
        return splitBtn;
    }

    @Override
    public StopWatch getStopWatch() {
        return stopWatch;
    }

    private JLabel getTimeLabel() {
        if (timeLabel == null) {
            timeLabel = new JLabel();
        }
        return timeLabel;
    }
}
