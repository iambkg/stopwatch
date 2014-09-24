package by.bkg.timer;

import by.bkg.timer.enums.Mode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import static by.bkg.timer.enums.Mode.PAUSED;
import static by.bkg.timer.enums.Mode.STOPPED;

/**
 * @author Alexey Baryshnev
 */
public class StopWatch extends JPanel {

    public static final String TIME_STAMP_PATTERN = "HH:mm:ss:SSS";
    private java.util.Timer timer = new java.util.Timer();
    private org.apache.commons.lang.time.StopWatch myTimer2;
    private Font myClockFont;
    private JButton startBtn, stopBtn, splitBtn;
    private JLabel timeLbl;
    private JPanel topPanel, bottomPanel;
    private int clockTick;      //number of clock ticks; tick can be 1.0 s or 0.1 s
    private double clockTime;      //time in seconds
    private String clockTimeString;
    private Mode timerMode;

    public StopWatch() {
        timer.schedule(new UpdateUITask(this), 0, 1);
        timerMode = STOPPED;

        clockTick = 0;          //initial clock setting in clock ticks
        clockTime = ((double) clockTick) / 10.0;

        clockTimeString = new Double(clockTime).toString();
        myClockFont = new Font("Serif", Font.PLAIN, 50);

        timeLbl = new JLabel();
        timeLbl.setFont(myClockFont);
        timeLbl.setText(clockTimeString);

        startBtn = new JButton("Start");
        stopBtn = new JButton("Stop");
        splitBtn = new JButton("Split");

        updateEnabledByMode();

        myTimer2 = new org.apache.commons.lang.time.StopWatch();

        startBtn.addActionListener(getStartBtnListener());
        stopBtn.addActionListener(getStopBtnListener());
        splitBtn.addActionListener(getSplitBtnListener());
    }

    private void updateEnabledByMode() {
        boolean startEnabled = false;
        boolean stopEnabled = false;
        boolean splitEnabled = false;

        switch (getTimerMode()) {
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
                switch (getTimerMode()) {
                    case STOPPED:
                        break;
                    case RUNNING:
                        myTimer2.split();
                        JOptionPane.showMessageDialog(null, myTimer2.toSplitString());
                        myTimer2.unsplit();
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
                switch (getTimerMode()) {
                    case STOPPED:
                        break;
                    case RUNNING:
                        setTimerMode(PAUSED);
                        stopBtn.setText("Reset");
                        myTimer2.suspend();
                        break;
                    case PAUSED:
                        setTimerMode(STOPPED);
                        myTimer2.stop();
                        myTimer2.reset();
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
                switch (getTimerMode()) {
                    case STOPPED:
                        setTimerMode(Mode.RUNNING);
                        myTimer2.start();
                        break;
                    case RUNNING:
                        // already in his state
                        break;
                    case PAUSED:
                        setTimerMode(Mode.RUNNING);
                        myTimer2.resume();
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
        switch (getTimerMode()) {
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

    public void launchStopWatch() {
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

        setSize(400, 100);
        setMinimumSize(new Dimension(400, 100));
        setPreferredSize(new Dimension(400, 100));
        setBackground(Color.orange);
    }

    private String getCurrentTime() {
        long time = myTimer2 != null ? myTimer2.getTime() : 0;
        return new SimpleDateFormat(TIME_STAMP_PATTERN).format(new Date(time));
    }

    public Mode getTimerMode() {
        return timerMode;
    }

    public void setTimerMode(Mode timerMode) {
        this.timerMode = timerMode;
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
