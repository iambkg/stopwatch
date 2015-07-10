package by.bkg.stopwatch.desktop.view;

import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.component.StopWatchPanel;
import by.bkg.stopwatch.desktop.view.component.controller.StopwatchFrameController;
import by.bkg.stopwatch.desktop.view.component.dialog.RegisteredSportsmanDialog;
import by.bkg.stopwatch.desktop.view.model.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Component
public class StopwatchFrame extends JFrame {

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private StopWatchPanel stopWatchPanel;

    @Autowired
    private RegisteredSportsmanDialog registeredSportsmanDialog;

    @Autowired
    private StopwatchFrameController controller;

    private JTextField startNumber;

    private JButton splitBtn;

    // TODO ABA: use table instead of JLabel
    private JLabel splitResults;

    public void init() {
        createPanels();
        registeredSportsmanDialog.init();
        setupFrame();
        pack();
        setVisible(true);
    }

    private void createPanels() {
        setLayout(new BorderLayout());

        Container myPane = getContentPane();
        myPane.add(createToolBar(), BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane();
//        splitPane.setLeftComponent(createListOfRegisteredPersonsComponent());
        splitPane.setRightComponent(createCenterComponent());
        myPane.add(splitPane, BorderLayout.CENTER);
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        toolBar.add(createViewSportsmenBtn());

        return toolBar;
    }

    private JComponent createCenterComponent() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JLabel resultsComponent = new JLabel();
        resultsComponent.setText(AppConstants.EMPTY_STRING);
        resultsComponent.setVerticalAlignment(SwingConstants.TOP);

        centerPanel.add(resultsComponent, BorderLayout.CENTER);

        // TODO ABA: think about it. Looks like there is too much code here
        Callback<Void> onStartCallback = new Callback<Void>() {
            @Override
            public void execute(Void param) {
                splitBtn.setEnabled(true);
                startNumber.setEnabled(true);
            }
        };
        Callback<Void> onPauseCallback = new Callback<Void>() {
            @Override
            public void execute(Void param) {
                splitBtn.setEnabled(false);
                startNumber.setEnabled(false);
            }
        };
        Callback<Void> onStopCallback = new Callback<Void>() {
            @Override
            public void execute(Void param) {
                splitBtn.setEnabled(false);
                startNumber.setEnabled(false);
            }
        };
        stopWatchPanel.init(onStartCallback, onPauseCallback, onStopCallback);

        centerPanel.add(createSmallTop(), BorderLayout.NORTH);
        centerPanel.add(createSmallCenter(), BorderLayout.CENTER);
        return centerPanel;
    }

    /**
     * Builds Splits Table Panel
     *
     * @return Splits Table Panel
     */
    private JPanel createSmallCenter() {
        JPanel smallCenter = new JPanel();

        splitResults = new JLabel();
        splitResults.setVerticalAlignment(SwingConstants.TOP);

        smallCenter.add(splitResults); // TODO ABA: add ScrollPanel
        return smallCenter;
    }

    /**
     * Builds StopWatchPanel and startNumberInput for splitting
     *
     * @return StopWatchPanel and startNumberInput for splitting
     */
    private JPanel createSmallTop() {
        JPanel smallTop = new JPanel();
        smallTop.setLayout(new BoxLayout(smallTop, BoxLayout.Y_AXIS));
        smallTop.add(stopWatchPanel);

        JPanel smallCenter = new JPanel();
        smallCenter.setLayout(new BoxLayout(smallCenter, BoxLayout.X_AXIS));
        smallCenter.add(createStartNumberInput());

        splitBtn = new JButton(appMessages.getString("btn.split"));
        splitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSplit();
            }
        });
        splitBtn.setEnabled(false);
        smallCenter.add(splitBtn);

        smallTop.add(smallCenter);
        return smallTop;
    }

    private JComponent createStartNumberInput() {
        startNumber = new JTextField();
        startNumber.setPreferredSize(new Dimension(100, 25));
//        JLabel label = new JLabel(appMessages.getString("label.start-number-short"));
//        label.setLabelFor(startNumber);

//        startNumber.setEnabled(false);
        startNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                    onSplit();
                }
            }
        });
        startNumber.setEnabled(false);
        return startNumber;
    }

    private void onSplit() {
        List<ISplitRecord> refreshedSplits = controller.onSplit(startNumber.getText());
        showSplits(refreshedSplits);
        startNumber.setText("");
    }

    private void showSplits(List<ISplitRecord> refreshedSplits) {
        String splits = "<html><body>";
        for (ISplitRecord split : refreshedSplits) {
            splits += String.format("%s. %s<br/>", split.getStartNumber(), split.getSplitTimeAsString());
        }
        splits += "</body></html>";
        splitResults.setText(splits);
    }

    private JButton createViewSportsmenBtn() {
        JButton viewSportsmenBtn = new JButton();
        viewSportsmenBtn.setText(appMessages.getString("btn.view-sportsmen"));
        viewSportsmenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registeredSportsmanDialog.setVisible(true);
            }
        });
        return viewSportsmenBtn;
    }

    private void setupFrame() {
        setSize(AppConstants.DEFAULT_WIDTH, AppConstants.DEFAULT_HEIGHT);
        setMinimumSize(new Dimension(AppConstants.DEFAULT_WIDTH, AppConstants.DEFAULT_HEIGHT));
        setPreferredSize(new Dimension(AppConstants.DEFAULT_WIDTH, AppConstants.DEFAULT_HEIGHT));
        setTitle(appMessages.getString("label.app-name"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
