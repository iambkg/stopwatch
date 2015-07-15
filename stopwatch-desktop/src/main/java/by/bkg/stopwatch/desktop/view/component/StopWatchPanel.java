package by.bkg.stopwatch.desktop.view.component;

import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.component.controller.StopWatchPanelController;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.model.StopWatchPanelState;
import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
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
public class StopWatchPanel extends JToolBar {

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private StopWatchPanelController controller;

    @Autowired
    private ComponentFactory componentFactory;

    private JButton startBtn;
    private JButton stopBtn;

    private JLabel timeLabel;

    private Callback<Void> onStartCallback;
    private Callback<Void> onStopCallback;
    private Callback<Void> onPauseCallback;

    public StopWatchPanel init(Callback<Void> onStartCallback, Callback<Void> onPauseCallback, Callback<Void> onStopCallback) {
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
        setFloatable(false);

        startBtn = componentFactory.createBtn("icons/x24/Timer-Start.png", appMessages.getString("btn.start"), createStartBtnListener());
        stopBtn = componentFactory.createBtn("icons/x24/Timer-Stop.png", appMessages.getString("btn.stop"), createStopBtnListener());
        stopBtn.setEnabled(false);

//        splitBtn = new JButton(appMessages.getString("btn.split"));
//        getSplitBtn().addActionListener(getSplitBtnListener());

//        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

//        setLayout(new BorderLayout());
        add(getTimeLabel());
        add(startBtn);
        add(stopBtn);

        return this;
    }

    public void reset() {
//        onStop();     // TODO ABA: implement
    }

    /**
     * Listener for "Start" button
     *
     * @return action listener
     */
    private ActionListener createStartBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                getController().onStart(new Callback<StopWatchPanelState>() {
                    @Override
                    public void execute(StopWatchPanelState state) {
                        startBtn.setToolTipText(state.getStartBtnTooltipText());
                        componentFactory.refreshIcon(startBtn, state.getStartBtnIcon());
                        stopBtn.setEnabled(state.getStopBtnEnabled());
                        switch (state.getStatus()) {
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
    private ActionListener createStopBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                onStop();
            }
        };
    }

    private void onStop() {
        getController().onStop(new Callback<StopWatchPanelState>() {
            @Override
            public void execute(StopWatchPanelState state) {
                startBtn.setToolTipText(state.getStartBtnTooltipText());
                componentFactory.refreshIcon(startBtn, state.getStartBtnIcon());
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

    private JLabel getTimeLabel() {
        if (timeLabel == null) {
            timeLabel = new JLabel();
            timeLabel.setFont(new Font(timeLabel.getFont().getName(), Font.PLAIN, 25));
        }
        return timeLabel;
    }

    public StopWatchPanelController getController() {
        return controller;
    }
}
