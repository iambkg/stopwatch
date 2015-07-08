package by.bkg.stopwatch.desktop.view.v2.component;

import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.v2.component.controller.StopWatchPanelV2Controller;
import by.bkg.stopwatch.desktop.view.v2.model.Callback;
import by.bkg.stopwatch.desktop.view.v2.model.StopWatchPanelState;
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
public class StopWatchPanelV2 extends JPanel {

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private StopWatchPanelV2Controller controller;

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 100;

    private JButton startBtn;
    private JButton stopBtn;

    private JLabel timeLabel;

    public void init() {
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

    public StopWatchPanelV2Controller getController() {
        return controller;
    }
}
