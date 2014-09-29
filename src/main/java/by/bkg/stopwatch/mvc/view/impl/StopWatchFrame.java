package by.bkg.stopwatch.mvc.view.impl;

import by.bkg.stopwatch.common.factory.ControllerFactory;
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
    public static final int DEFAULT_HEIGHT = 200;

    public StopWatchFrame() {
        super("Stop-Watch");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createPanels();
        setupFrameSize();
        pack();
        setVisible(true);
    }

    private void createPanels() {
        setLayout(new BorderLayout());

        Container myPane = getContentPane();
        myPane.add(createEastPanel(), BorderLayout.EAST);
        myPane.add(createNorthPanel(), BorderLayout.NORTH);
        myPane.add(createWestPanel(), BorderLayout.WEST);
        myPane.add(createSouthPanel(), BorderLayout.SOUTH);
        myPane.add(createCenterPanel(), BorderLayout.CENTER);
    }

    private void setupFrameSize() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    private JPanel createEastPanel() {
        StopWatchPanel stopWatchPanel = new StopWatchPanel();
        stopWatchPanel.setController(ControllerFactory.getStopWatchController(stopWatchPanel));
        stopWatchPanel.init();
        return stopWatchPanel;
    }

    private JPanel createNorthPanel() {
        JPanel nLabelPanel = new JPanel();
        JLabel nLabel = new JLabel();
        nLabel.setText("N-label");
        nLabelPanel.add(nLabel);
        return nLabelPanel;
    }

    private JPanel createWestPanel() {
        JPanel wLabelPanel = new JPanel();
        JLabel wLabel = new JLabel();
        wLabel.setText("W-label");
        wLabelPanel.add(wLabel);
        return wLabelPanel;
    }

    private JPanel createSouthPanel() {
        JPanel sLabelPanel = new JPanel();
        JLabel sLabel = new JLabel();
        sLabel.setText("S-label");
        sLabelPanel.add(sLabel);
        return sLabelPanel;
    }

    private JPanel createCenterPanel() {
        JPanel cLabelPanel = new JPanel();
        JLabel cLabel = new JLabel();
        cLabel.setText("C-label");
        cLabelPanel.add(cLabel);
        return cLabelPanel;
    }
}
