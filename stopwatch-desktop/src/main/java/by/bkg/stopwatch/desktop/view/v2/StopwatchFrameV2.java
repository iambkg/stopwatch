package by.bkg.stopwatch.desktop.view.v2;

import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.v2.component.StopWatchPanelV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Alexey Baryshnev
 */
@Component
public class StopwatchFrameV2 extends JFrame {

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private StopWatchPanelV2 stopWatchPanel;

    public void init() {
        createPanels();
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

        stopWatchPanel.init();

        JPanel smallTop = new JPanel();
        smallTop.setLayout(new BoxLayout(smallTop, BoxLayout.Y_AXIS));
        smallTop.add(stopWatchPanel);

        JPanel smallCenter = new JPanel();
        smallCenter.setLayout(new BoxLayout(smallCenter, BoxLayout.X_AXIS));
        smallCenter.add(createStartNumberInput());

        JButton splitBtn = new JButton(appMessages.getString("btn.split"));
        splitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO ABA: implement
            }
        });
        smallCenter.add(splitBtn);

        smallTop.add(smallCenter);

        centerPanel.add(smallTop, BorderLayout.NORTH);
        return new JScrollPane(centerPanel);
    }

    private JComponent createStartNumberInput() {
        JTextField startNumber = new JTextField();
        startNumber.setPreferredSize(new Dimension(100, 25));
//        JLabel label = new JLabel(appMessages.getString("label.start-number-short"));
//        label.setLabelFor(startNumber);

//        startNumber.setEnabled(false);
        startNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                if (KeyEvent.VK_ENTER == e.getKeyCode()) {
//                        getController().onEnterStartNumber();
//                }
            }
        });
        return startNumber;
    }

    private JButton createViewSportsmenBtn() {
        JButton viewSportsmenBtn = new JButton();
        viewSportsmenBtn.setText(appMessages.getString("btn.view-sportsmen"));
        viewSportsmenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                    getController().onAddPersonClick();
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
