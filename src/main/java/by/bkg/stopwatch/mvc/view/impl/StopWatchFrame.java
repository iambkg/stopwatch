package by.bkg.stopwatch.mvc.view.impl;

import by.bkg.stopwatch.common.factory.ControllerFactory;
import by.bkg.stopwatch.mvc.controller.EventBus;
import by.bkg.stopwatch.mvc.controller.impl.panel.StopWatchPanelController;
import by.bkg.stopwatch.mvc.view.impl.panel.StopWatchPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Application main frame
 *
 * @author Alexey Baryshnev
 */
public class StopWatchFrame extends JFrame {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;

    private EventBus eventBus;

    private StopWatchPanelController stopWatchController;

    private JLabel cLabel;

    public StopWatchFrame(EventBus eventBus) {
        super("Stop-Watch");
        this.eventBus = eventBus;
        eventBus.setFrame(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createPanels();
        setupFrameSize();
        pack();
        setVisible(true);
    }

    private void createPanels() {
        setLayout(new BorderLayout());

        Container myPane = getContentPane();
        myPane.add(createEastComponent(), BorderLayout.EAST);
        myPane.add(createNorthComponent(), BorderLayout.NORTH);
        myPane.add(createWestComponent(), BorderLayout.WEST);
        myPane.add(createSouthComponent(), BorderLayout.SOUTH);
        myPane.add(createCenterComponent(), BorderLayout.CENTER);
    }

    private void setupFrameSize() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    private JComponent createEastComponent() {
        JPanel eLabelPanel = new JPanel();
        JLabel eLabel = new JLabel();
        eLabel.setText("E-label");
        eLabelPanel.add(eLabel);
        return eLabelPanel;
    }

    private JComponent createNorthComponent() {
        StopWatchPanel stopWatchPanel = new StopWatchPanel();
        stopWatchController = ControllerFactory.getStopWatchController(eventBus, stopWatchPanel);
        stopWatchPanel.setController(stopWatchController);
        stopWatchPanel.init();
        return stopWatchPanel;
    }

    private JComponent createWestComponent() {
        JPanel wLabelPanel = new JPanel();
        JLabel wLabel = new JLabel();
        wLabel.setText("W-label");
        wLabelPanel.add(wLabel);
        return wLabelPanel;
    }

    private JComponent createSouthComponent() {
        JPanel sLabelPanel = new JPanel();
        JLabel sLabel = new JLabel();
        sLabel.setText("S-label");
        sLabelPanel.add(sLabel);
        return sLabelPanel;
    }

    private JComponent createCenterComponent() {
        // TODO ABA: add scrollpanel
//        JScrollPane cScrollPanel = new JScrollPane();
        JPanel cLabelPanel = new JPanel();
        cLabel = new JLabel();
        cLabel.setText("C-label");
        cLabelPanel.add(cLabel);
//        cScrollPanel.add(cLabelPanel);
//        return cScrollPanel;
        return cLabelPanel;
    }

    public JLabel getResultsLabel() {
        return cLabel;
    }

    public StopWatchPanelController getStopWatchController() {
        return stopWatchController;
    }
}
