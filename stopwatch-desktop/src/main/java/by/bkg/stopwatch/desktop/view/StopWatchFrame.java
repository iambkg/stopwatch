package by.bkg.stopwatch.desktop.view;

import by.bkg.stopwatch.desktop.view.controller.IStopWatchAppController;
import by.bkg.stopwatch.desktop.view.controller.panel.IRegisteredPersonsPanelController;
import by.bkg.stopwatch.desktop.view.controller.panel.IStopWatchPanelController;
import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.component.panel.IStopWatchPanel;
import by.bkg.stopwatch.desktop.view.component.panel.RegisteredPersonsPanel;
import by.bkg.stopwatch.desktop.view.component.panel.StopWatchPanel;

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
@Deprecated
public class StopWatchFrame extends JFrame {

    private JLabel resultsComponent;

    private StopWatchPanel stopWatchPanel;

    private RegisteredPersonsPanel registeredPersonsPanel;

    private JTextField startNumber;

    private IStopWatchAppController controller;

//    private ComponentFactory componentFactory;

    private AppMessages appMessages;

//    @Inject
    public StopWatchFrame(AppMessages appMessages, /*ComponentFactory componentFactory, */IStopWatchAppController controller) {
        this.appMessages = appMessages;
//        this.componentFactory = componentFactory;
        this.controller = controller;
        setTitle(appMessages.getString("label.app-name"));
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
//        registeredPersonsPanel = componentFactory.createRegisteredPersonsComponent();
        return new JScrollPane(registeredPersonsPanel);
    }

    private JComponent createCenterComponent() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        resultsComponent = new JLabel();
        resultsComponent.setText(AppConstants.EMPTY_STRING);
        resultsComponent.setVerticalAlignment(SwingConstants.TOP);

        centerPanel.add(resultsComponent, BorderLayout.CENTER);
//        stopWatchPanel = componentFactory.createStopWatchComponent();

        JPanel smallTop = new JPanel();
        smallTop.add(stopWatchPanel);
        smallTop.add(createStartNumberInput());

        centerPanel.add(smallTop, BorderLayout.NORTH);
        return new JScrollPane(centerPanel);
    }

    private JComponent createStartNumberInput() {
        startNumber = new JTextField();
        startNumber.setPreferredSize(new Dimension(100, 25));
        JLabel label = new JLabel(appMessages.getString("label.start-number-short"));
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
        addPersonBtn.setText(appMessages.getString("btn.add-person"));
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
