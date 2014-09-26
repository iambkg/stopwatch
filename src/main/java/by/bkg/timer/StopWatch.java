package by.bkg.timer;

import by.bkg.timer.enums.TimerStatus;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import static by.bkg.timer.enums.TimerStatus.PAUSED;
import static by.bkg.timer.enums.TimerStatus.STOPPED;

/**
 * @author Alexey Baryshnev
 */
public class StopWatch extends JPanel {

    private static final Logger LOG = Logger.getLogger(StopWatch.class.getName());

    public static final String ZERO_TIME = "00:00:00.000";
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 100;
    private java.util.Timer timer = new java.util.Timer();
    private org.apache.commons.lang.time.StopWatch stopWatch;
    private Font myClockFont;
    private JButton startBtn, stopBtn, splitBtn;
    private JLabel timeLbl;
    private JPanel topPanel, bottomPanel;
    private TimerStatus timerStatus;

    public StopWatch() {
        timer.schedule(new UpdateUITask(this), 0, 1);
        timerStatus = STOPPED;

        myClockFont = new Font("Serif", Font.PLAIN, 50);

        timeLbl = new JLabel();
        timeLbl.setFont(myClockFont);

        startBtn = new JButton("Start");
        stopBtn = new JButton("Stop");
        splitBtn = new JButton("Split");

        updateEnabledByMode();

        stopWatch = new org.apache.commons.lang.time.StopWatch();

        startBtn.addActionListener(getStartBtnListener());
        stopBtn.addActionListener(getStopBtnListener());
        splitBtn.addActionListener(getSplitBtnListener());
    }

    private void updateEnabledByMode() {
        boolean startEnabled = false;
        boolean stopEnabled = false;
        boolean splitEnabled = false;

        switch (getTimerStatus()) {
            case STOPPED:
                startEnabled = true;
                stopEnabled = false;
                splitEnabled = false;
                break;
            case RUNNING:
                startEnabled = false;
                stopEnabled = true;
                splitEnabled = true;
                break;
            case PAUSED:
                startEnabled = true;
                stopEnabled = true;
                splitEnabled = false;
                break;
        }
        startBtn.setEnabled(startEnabled);
        stopBtn.setEnabled(stopEnabled);
        splitBtn.setEnabled(splitEnabled);
    }

    /**
     * Listener for "Split" button
     *
     * @return action listener
     */
    private ActionListener getSplitBtnListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                switch (getTimerStatus()) {
                    case STOPPED:
                        break;
                    case RUNNING:
                        stopWatch.split();
                        JOptionPane.showMessageDialog(null, stopWatch.toSplitString());
                        stopWatch.unsplit();
                        break;
                    case PAUSED:
                        break;
                }
                updateEnabledByMode();
                updateBtnTextByMode();
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
                switch (getTimerStatus()) {
                    case STOPPED:
                        break;
                    case RUNNING:
                        setTimerStatus(PAUSED);
                        stopBtn.setText("Reset");
                        stopWatch.suspend();
                        break;
                    case PAUSED:
                        setTimerStatus(STOPPED);
                        stopWatch.stop();
                        stopWatch.reset();
                        break;
                }
                updateEnabledByMode();
                updateBtnTextByMode();
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
                switch (getTimerStatus()) {
                    case STOPPED:
                        setTimerStatus(TimerStatus.RUNNING);
                        stopWatch.start();
                        break;
                    case RUNNING:
                        // already in this state
                        break;
                    case PAUSED:
                        setTimerStatus(TimerStatus.RUNNING);
                        stopWatch.resume();
                        break;
                }
                updateEnabledByMode();
                updateBtnTextByMode();
            }
        };
    }

    private void updateBtnTextByMode() {
        String startText = "";
        String stopText = "";
        switch (getTimerStatus()) {
            case STOPPED:
                startText = "Start";
                stopText = "Stop";
                break;
            case RUNNING:
                startText = "Resume";
                stopText = "Stop";
                break;
            case PAUSED:
                startText = "Resume";
                stopText = "Reset";
                break;
        }
        startBtn.setText(startText);
        stopBtn.setText(stopText);
    }

    public void initStopWatch() {
        topPanel = new JPanel();
        topPanel.setBackground(Color.orange);
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.yellow);
        topPanel.add(timeLbl);
        bottomPanel.add(startBtn);
        bottomPanel.add(stopBtn);
        bottomPanel.add(splitBtn);

        this.setLayout(new BorderLayout());

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        setBackground(Color.orange);
    }

    private String getCurrentTime() {
        if (stopWatch == null) {
            return ZERO_TIME;
        }
//        return SIMPLE_DATE_FORMAT.format(new Date(stopWatch.getTime()));
        return stopWatch.toString();
    }

    public TimerStatus getTimerStatus() {
        return timerStatus;
    }

    public void setTimerStatus(TimerStatus timerStatus) {
        this.timerStatus = timerStatus;
    }

    private class UpdateUITask extends TimerTask {

        private final StopWatch stopWatch;

        public UpdateUITask(StopWatch stopWatch) {
            this.stopWatch = stopWatch;
        }

        @Override
        public void run() {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    timeLbl.setText(stopWatch.getCurrentTime());
                }
            });
        }
    }
}
