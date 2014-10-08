package by.bkg.stopwatch.mvc.view;

import by.bkg.stopwatch.mvc.controller.IStopWatchAppController;
import by.bkg.stopwatch.mvc.controller.panel.IRegisteredPersonsPanelController;
import by.bkg.stopwatch.mvc.controller.panel.IStopWatchPanelController;
import by.bkg.stopwatch.mvc.model.AppConstants;
import by.bkg.stopwatch.mvc.view.factory.ComponentFactory;
import by.bkg.stopwatch.mvc.view.panel.IStopWatchPanel;
import by.bkg.stopwatch.mvc.view.panel.RegisteredPersonsPanel;
import by.bkg.stopwatch.mvc.view.panel.StopWatchPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Application main frame
 *
 * @author Alexey Baryshnev
 */
@Component
public class StopWatchFrame extends JFrame {

    private JLabel resultsComponent;

    private StopWatchPanel stopWatchPanel;

    @Autowired
    private IStopWatchAppController controller;

    private RegisteredPersonsPanel registeredPersonsPanel;

    private JTextField startNumber;

    @Autowired
    private ComponentFactory componentFactory;

    public StopWatchFrame() {
        super("Stop-Watch");  // TODO ABA: i18n
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void init() {
        createPanels();
        setupFrameSize();
        pack();
        setVisible(true);
    }

    private void createPanels() {
        setLayout(new BorderLayout());

        Container myPane = getContentPane();
        myPane.add(createToolBar(), BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(createListOfRegisteredPersonsComponent());
        splitPane.setRightComponent(createCenterComponent());
        myPane.add(splitPane, BorderLayout.CENTER);
    }

    private void setupFrameSize() {
        setSize(AppConstants.DEFAULT_WIDTH, AppConstants.DEFAULT_HEIGHT);
        setMinimumSize(new Dimension(AppConstants.DEFAULT_WIDTH, AppConstants.DEFAULT_HEIGHT));
        setPreferredSize(new Dimension(AppConstants.DEFAULT_WIDTH, AppConstants.DEFAULT_HEIGHT));
    }

    private JComponent createListOfRegisteredPersonsComponent() {
        registeredPersonsPanel = componentFactory.createRegisteredPersonsComponent();
        return new JScrollPane(registeredPersonsPanel);
    }

    private JComponent createCenterComponent() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        resultsComponent = new JLabel();
        resultsComponent.setText(AppConstants.EMPTY_STRING);
        resultsComponent.setVerticalAlignment(SwingConstants.TOP);

        centerPanel.add(resultsComponent, BorderLayout.CENTER);
        stopWatchPanel = componentFactory.createStopWatchComponent();

        JPanel smallTop = new JPanel();
        smallTop.add(stopWatchPanel);
        smallTop.add(createStartNumberInput());

        centerPanel.add(smallTop, BorderLayout.NORTH);
        return new JScrollPane(centerPanel);
    }

    private JComponent createStartNumberInput() {
        startNumber = new JTextField();
        startNumber.setPreferredSize(new Dimension(100, 25));
        JLabel label = new JLabel("Start #");  // TODO ABA: i18n
        label.setLabelFor(startNumber);

        startNumber.setEnabled(false);
        startNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                    getController().onEnterStartNumber();
                }
            }
        });
        return startNumber;
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
                getController().onAddPersonClick();
            }
        });
        return addPersonBtn;
    }

    // TODO ABA: refactor to JComponent
    public JLabel getResultsLabel() {
        return resultsComponent;
    }

    public IStopWatchPanelController getStopWatchController() {
        return getStopWatchPanel().getController();
    }

    public IStopWatchAppController getController() {
        return controller;
    }

    public IStopWatchPanel getStopWatchPanel() {
        return stopWatchPanel;
    }

    public IRegisteredPersonsPanelController getRegisteredPersonsController() {
        return registeredPersonsPanel.getController();
    }

    public String getTypedStartNumber() {
        return startNumber.getText();
    }

    public void clearTypedStartNumber() {
        startNumber.setText(AppConstants.EMPTY_STRING);
    }

    public void setStartNumberFieldEnabled(boolean enabled) {
        if (!enabled) {
            startNumber.setText(AppConstants.EMPTY_STRING);
        }
        startNumber.setEnabled(enabled);
    }
}
