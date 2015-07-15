package by.bkg.stopwatch.desktop.view;

import by.bkg.stopwatch.core.model.ISplitRecord;
import by.bkg.stopwatch.desktop.model.AppConstants;
import by.bkg.stopwatch.desktop.view.component.StopWatchPanel;
import by.bkg.stopwatch.desktop.view.component.controller.StopwatchFrameController;
import by.bkg.stopwatch.desktop.view.component.dialog.EditSplitDialog;
import by.bkg.stopwatch.desktop.view.component.dialog.RegisteredSportsmanDialog;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.model.DefaultSplitFilter;
import by.bkg.stopwatch.desktop.view.model.ISplitFilter;
import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
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
    private EditSplitDialog editSplitDialog;

    @Autowired
    private StopwatchFrameController controller;

    @Autowired
    private ComponentFactory componentFactory;

    private JTextField startNumber;

    private JButton splitBtn;

    private JButton editSplitBtn;

    private JButton deleteSplitBtn;

    private JTable splitTable;

    private JList splitsList;

    private DefaultSplitFilter defaultFilter;

    public void init() {
        createPanels();
        defaultFilter = new DefaultSplitFilter(appMessages);
        registeredSportsmanDialog.init();
        registeredSportsmanDialog.setLocationRelativeTo(this);

        editSplitDialog.init();
        editSplitDialog.setLocationRelativeTo(this);

        setupFrame();
        pack();
        setVisible(true);
    }

    private void createPanels() {
        setLayout(new BorderLayout());
        Container myPane = getContentPane();
        myPane.add(createToolbar(), BorderLayout.NORTH);
        myPane.add(createContents(), BorderLayout.CENTER);
    }

    private JToolBar createToolbar() {
        JToolBar toolBar = componentFactory.createToolBar();
        toolBar.add(componentFactory.createBtn("icons/df_On_Stage_Icon_Set/PNG/Buddy.png", appMessages.getString("btn.view-sportsmen"), createViewSportsmenBtnListener()));
        toolBar.add(componentFactory.createBtn(appMessages.getString("btn.new-event"), createNewEventBtnListener()));
        return toolBar;
    }

    private ActionListener createNewEventBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                List<ISplitRecord> splits = controller.startNewEvent();
                showSplitsInList(splits);
                showSplitsInTable(splits, getSelectedFilter());
            }
        };
    }

    private ISplitFilter getSelectedFilter() {
        return defaultFilter;
    }

    private ActionListener createViewSportsmenBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                registeredSportsmanDialog.open();
            }
        };
    }

    private JSplitPane createContents() {
        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(createLeftComponent());
        splitPane.setRightComponent(createRightComponent());
        return splitPane;
    }

    private JComponent createLeftComponent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JToolBar toolBar = componentFactory.createToolBar();

        editSplitBtn = componentFactory.createBtn("icons/df_On_Stage_Icon_Set/PNG/Edit.png", appMessages.getString("btn.edit-split"), new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                startEditing((ISplitRecord) splitsList.getSelectedValue());
            }
        });
        editSplitBtn.setEnabled(false);

        deleteSplitBtn = componentFactory.createBtn("icons/df_On_Stage_Icon_Set/PNG/Symbol-Remove.png", appMessages.getString("btn.delete-split"), new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                List<ISplitRecord> refreshedSplits = controller.deleteSplit((ISplitRecord) splitsList.getSelectedValue());
                showSplitsInList(refreshedSplits);
                showSplitsInTable(refreshedSplits, getSelectedFilter());
            }
        });
        deleteSplitBtn.setEnabled(false);

        toolBar.add(editSplitBtn);
        toolBar.add(deleteSplitBtn);

        panel.add(toolBar, BorderLayout.NORTH);

        splitsList = new JList();
        splitsList.setLayoutOrientation(JList.VERTICAL);
        splitsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        splitsList.setModel(new DefaultListModel());
        splitsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(final ListSelectionEvent e) {
                boolean valueSelected = !splitsList.isSelectionEmpty();
                editSplitBtn.setEnabled(valueSelected);
                deleteSplitBtn.setEnabled(valueSelected);
            }
        });

        panel.add(new JScrollPane(splitsList), BorderLayout.CENTER);
        return panel;
    }

    private void startEditing(final ISplitRecord splitToEdit) {
        editSplitDialog.open(splitToEdit, new Callback<List<ISplitRecord>>() {
            @Override
            public void execute(final List<ISplitRecord> refreshedSplits) {
                showSplitsInList(refreshedSplits);
                showSplitsInTable(refreshedSplits, getSelectedFilter());
            }
        });
    }

    private JComponent createRightComponent() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        centerPanel.add(stopWatchPanel.init(createOnStartCallback(), createOnPauseCallback(), createOnStopCallback()), BorderLayout.NORTH);
        centerPanel.add(createSplitFunctionalityPanel(), BorderLayout.CENTER);

        return centerPanel;
    }

    private Callback<Void> createOnStartCallback() {
        return new Callback<Void>() {
            @Override
            public void execute(final Void param) {
                splitBtn.setEnabled(true);
                startNumber.setEnabled(true);
            }
        };
    }

    private Callback<Void> createOnPauseCallback() {
        return new Callback<Void>() {
            @Override
            public void execute(final Void param) {
                splitBtn.setEnabled(false);
                startNumber.setEnabled(false);
            }
        };
    }

    private Callback<Void> createOnStopCallback() {
        return new Callback<Void>() {
            @Override
            public void execute(final Void param) {
                splitBtn.setEnabled(false);
                startNumber.setEnabled(false);
            }
        };
    }

    /**
     * Builds Splits Table Panel
     *
     * @return Splits Table Panel
     */
    private JPanel createSplitFunctionalityPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(createSplitFormPanel(), BorderLayout.NORTH);
        panel.add(createSplitTable(), BorderLayout.CENTER);
        return panel;
    }

    private JComponent createSplitTable() {
        splitTable = new JTable();
        splitTable.setFillsViewportHeight(true);
        splitTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        splitTable.setDragEnabled(false);
        splitTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        splitTable.getTableHeader().setReorderingAllowed(false);
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        splitTable.setModel(model);
        String[] colNames = {String.format("%s %d", appMessages.getString("label.lap"), 1)}; // TODO ABA: add start number as column
        model.setColumnIdentifiers(colNames);

//        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(splitTable.getModel());
//        splitTable.setRowSorter(sorter);
        return new JScrollPane(splitTable);
    }

    /**
     * Builds StopWatchPanel and startNumberInput for splitting
     *
     * @return StopWatchPanel and startNumberInput for splitting
     */
    private JPanel createSplitFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(createStartNumberInput());

        splitBtn = componentFactory.createBtn(appMessages.getString("btn.split"), createSplitBtnListener());
        splitBtn.setEnabled(false);
        panel.add(splitBtn);

        return panel;
    }

    private ActionListener createSplitBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                onSplit();
            }
        };
    }

    private JComponent createStartNumberInput() {
        startNumber = new JTextField();
        startNumber.setPreferredSize(new Dimension(100, 25));
//        JLabel label = new JLabel(appMessages.getString("label.start-number-short"));
//        label.setLabelFor(startNumber);

        startNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
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
        showSplitsInList(refreshedSplits);
        showSplitsInTable(refreshedSplits, getSelectedFilter());
        startNumber.setText("");
    }

    private void showSplitsInList(final List<ISplitRecord> refreshedSplits) {
        DefaultListModel splitsListModel = (DefaultListModel) splitsList.getModel();
        splitsListModel.clear();
        for (ISplitRecord refreshedSplit : refreshedSplits) {
            splitsListModel.addElement(refreshedSplit);
        }
    }

    private void showSplitsInTable(final List<ISplitRecord> refreshedSplits, ISplitFilter splitFilter) {
        DefaultTableModel model = (DefaultTableModel) splitTable.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        model.setDataVector(splitFilter.getDataVector(refreshedSplits), splitFilter.getColumnIdentifiers(refreshedSplits));
    }

    private void setupFrame() {
        setLocation(200, 200);
        setSize(AppConstants.DEFAULT_WIDTH, AppConstants.DEFAULT_HEIGHT);
        setMinimumSize(new Dimension(AppConstants.DEFAULT_WIDTH, AppConstants.DEFAULT_HEIGHT));
        setPreferredSize(new Dimension(AppConstants.DEFAULT_WIDTH, AppConstants.DEFAULT_HEIGHT));
        setTitle(appMessages.getString("label.app-name"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
