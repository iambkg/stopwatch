package by.bkg.stopwatch.mvc.view.impl;

import by.bkg.stopwatch.common.factory.view.ComponentFactory;
import by.bkg.stopwatch.mvc.controller.EventBus;
import by.bkg.stopwatch.mvc.controller.impl.StopWatchAppController;
import by.bkg.stopwatch.mvc.controller.impl.panel.StopWatchPanelController;
import by.bkg.stopwatch.mvc.view.impl.panel.RegisteredPersonsPanel;
import by.bkg.stopwatch.mvc.view.impl.panel.StopWatchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Application main frame
 *
 * @author Alexey Baryshnev
 */
public class StopWatchFrame extends JFrame {

    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 300;

    private EventBus eventBus;

    private JLabel resultsComponent;

    private StopWatchPanel stopWatchPanel;

    private StopWatchAppController controller;

    private RegisteredPersonsPanel registeredPersonsPanel;

    public StopWatchFrame(EventBus eventBus, StopWatchAppController controller) {
        super("Stop-Watch");  // TODO ABA: i18n
        this.eventBus = eventBus;
        this.controller = controller;
        eventBus.setFrame(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createPanels();
        setupFrameSize();
        pack();
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

    private void setupFrameSize() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    private JComponent createListOfRegisteredPersonsComponent() {
        registeredPersonsPanel = ComponentFactory.createRegisteredPersonsComponent(eventBus);
        return new JScrollPane(registeredPersonsPanel);
    }

    private JComponent createCenterComponent() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        resultsComponent = new JLabel();
        resultsComponent.setText("C-label");  // TODO ABA: i18n
        resultsComponent.setVerticalAlignment(SwingConstants.TOP);

        centerPanel.add(resultsComponent, BorderLayout.CENTER);
        stopWatchPanel = ComponentFactory.createStopWatchComponent(eventBus);
        centerPanel.add(stopWatchPanel, BorderLayout.NORTH);
        return new JScrollPane(centerPanel);
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        toolBar.add(createAddPersonBtn());

        return toolBar;
    }

    private JButton createAddPersonBtn() {
        JButton addPersonBtn = new JButton();
        addPersonBtn.setText("Add Person");     // TODO ABA: i18n
        addPersonBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().onAddPersonClick(eventBus);
            }
        });
        return addPersonBtn;
    }

    // TODO ABA: refactor to JComponent
    public JLabel getResultsLabel() {
        return resultsComponent;
    }

    public StopWatchPanelController getStopWatchController() {
        return getStopWatchPanel().getController();
    }

    public StopWatchAppController getController() {
        return controller;
    }

    public StopWatchPanel getStopWatchPanel() {
        return stopWatchPanel;
    }

//    public RegisteredPersonsPanelController getRegisteredPersonsController() {
//        return registeredPersonsPanel.getController();
//    }
}
