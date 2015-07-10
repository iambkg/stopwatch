package by.bkg.stopwatch.desktop.view.component;

import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.component.controller.StopWatchPanelController;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.model.StopWatchPanelState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/**
 * @author Alexey Baryshnev
 */
@Component
public class StopWatchPanel extends JPanel {

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private StopWatchPanelController controller;

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 100;

    private JButton startBtn;
    private JButton stopBtn;

    private JLabel timeLabel;

    private Callback<Void> onStartCallback;
    private Callback<Void> onStopCallback;
    private Callback<Void> onPauseCallback;

    public void init(Callback<Void> onStartCallback, Callback<Void> onPauseCallback, Callback<Void> onStopCallback) {
        this.onStartCallback = onStartCallback;
        this.onStopCallback = onStopCallback;
        this.onPauseCallback = onPauseCallback;
        controller.init();
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

        startBtn = new JButton(appMessages.getString("btn.start"));
        stopBtn = new JButton(appMessages.getString("btn.stop"));
//                splitBtn = new JButton(appMessages.getString("btn.split"));

        stopBtn.setEnabled(false);

        startBtn.addActionListener(getStartBtnListener());
        stopBtn.addActionListener(getStopBtnListener());
//        getSplitBtn().addActionListener(getSplitBtnListener());

        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        setLayout(new BorderLayout());
        add(buildNorthPanel(), BorderLayout.NORTH);
        add(buildSouthPanel(), BorderLayout.SOUTH);
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
                getController().onStart(new Callback<StopWatchPanelState>() {
                    @Override
                    public void execute(StopWatchPanelState state) {
                        startBtn.setText(state.getStartBtnText());
                        stopBtn.setEnabled(state.getStopBtnEnabled());
                        switch(state.getStatus()) {
                            case RUNNING:
                                onStartCallback.execute(null);
                                break;
                            case PAUSED:
                                onPauseCallback.execute(null);
                                break;
                        }
                    }
                });
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
                getController().onStop(new Callback<StopWatchPanelState>() {
                    @Override
                    public void execute(StopWatchPanelState state) {
                        startBtn.setText(state.getStartBtnText());
                        stopBtn.setEnabled(state.getStopBtnEnabled());
                        switch (state.getStatus()) {
                            case PAUSED:
                                onPauseCallback.execute(null);
                                break;
                            case STOPPED:
                                onStopCallback.execute(null);
                                break;
                        }
                    }
                });
            }
        };
    }

    private JPanel buildNorthPanel() {
        JPanel topPanel = new JPanel();
        topPanel.add(getTimeLabel());
        return topPanel;
    }

    private JPanel buildSouthPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(startBtn);
        bottomPanel.add(stopBtn);
        return bottomPanel;
    }

    private JLabel getTimeLabel() {
        if (timeLabel == null) {
            timeLabel = new JLabel();
        }
        return timeLabel;
    }

    public StopWatchPanelController getController() {
        return controller;
    }
}
