package by.bkg.stopwatch.mvc.view.panel;

import by.bkg.stopwatch.mvc.model.enums.TimerStatus;
import by.bkg.stopwatch.mvc.controller.panel.IStopWatchPanelController;
import org.apache.commons.lang.time.StopWatch;

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

    public StopWatchPanel() {
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

        startBtn = new JButton("Start");    // TODO ABA: i18n
        stopBtn = new JButton("Stop");      // TODO ABA: i18n
        splitBtn = new JButton("Split");    // TODO ABA: i18n

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
            public void actionPerformed(ActionEvent evt) {
                getController().onStart();
            }
        };
    }

    public void init() {
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

    public TimerStatus getTimerStatus() {
        return timerStatus;
    }

    public void setTimerStatus(TimerStatus timerStatus) {
        this.timerStatus = timerStatus;
    }

    public JButton getStartBtn() {
        return startBtn;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }

    public JButton getSplitBtn() {
        return splitBtn;
    }

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
