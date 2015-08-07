package by.bkg.stopwatch.desktop.view.component;

import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.view.component.controller.StopWatchPanelController;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
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

    public StopWatchPanel init(final Callback<Void> onStartCallback, final Callback<Void> onPauseCallback, final Callback<Void> onStopCallback) {
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
                    public void execute(final StopWatchPanelState state) {
                        startBtn.setToolTipText(state.getStartBtnTooltipText());
                        startBtn.setIcon(componentFactory.getImageIcon(state.getStartBtnIcon()));
                        stopBtn.setEnabled(state.getStopBtnEnabled());
                        switch (state.getStatus()) {
                            case RUNNING:
                                onStartCallback.execute(null);
                                break;
                            case PAUSED:
                                onPauseCallback.execute(null);
                                break;
                            default:
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
            public void actionPerformed(final ActionEvent evt) {
                onStop();
            }
        };
    }

    private void onStop() {
        getController().onStop(new Callback<StopWatchPanelState>() {
            @Override
            public void execute(final StopWatchPanelState state) {
                startBtn.setToolTipText(state.getStartBtnTooltipText());
                startBtn.setIcon(componentFactory.getImageIcon(state.getStartBtnIcon()));
                stopBtn.setEnabled(state.getStopBtnEnabled());
                switch (state.getStatus()) {
                    case PAUSED:
                        onPauseCallback.execute(null);
                        break;
                    case STOPPED:
                        onStopCallback.execute(null);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private JLabel getTimeLabel() {
        if (timeLabel == null) {
            timeLabel = new JLabel();
            timeLabel.setFont(new Font(timeLabel.getFont().getName(), Font.PLAIN, AppConstants.TIME_LABEL_FONT_SIZE));
        }
        return timeLabel;
    }

    public StopWatchPanelController getController() {
        return controller;
    }
}
