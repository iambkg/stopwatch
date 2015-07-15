package by.bkg.stopwatch.desktop.view.component.dialog;

import by.bkg.stopwatch.core.model.ISportsman;
import by.bkg.stopwatch.desktop.view.component.controller.RegisteredSportsmanDialogController;
import by.bkg.stopwatch.desktop.view.i18n.AppMessages;
import by.bkg.stopwatch.desktop.view.model.Callback;
import by.bkg.stopwatch.desktop.view.utilities.ComponentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Alexey Baryshnev
 */
@Component
public class RegisteredSportsmanDialog extends JDialog {    // TODO ABA: make it to extend AbstractDialog

    @Autowired
    private SportsmanDialog sportsmanDialog;

    @Autowired
    private AppMessages appMessages;

    @Autowired
    private RegisteredSportsmanDialogController controller;

    @Autowired
    private ComponentFactory componentFactory;

    private static final int MIN_WIDTH = 300;

    private static final int MIN_HEIGHT = 220;

    private JList sportsmenList;

    private JButton editPersonBtn;

    private JButton deletePersonBtn;

    public void init() {
        sportsmanDialog.init();
        sportsmanDialog.setLocationRelativeTo(this);

        sportsmenList = new JList();
        sportsmenList.setModel(new DefaultListModel());
        sportsmenList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sportsmenList.setLayoutOrientation(JList.VERTICAL);
        sportsmenList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean sportsmanSelected = !sportsmenList.isSelectionEmpty();
                editPersonBtn.setEnabled(sportsmanSelected);
                deletePersonBtn.setEnabled(sportsmanSelected);
            }
        });

        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        setTitle(appMessages.getString("label.sportsmen"));

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        add(createLogicButtonPanel(), BorderLayout.NORTH);
        add(new JScrollPane(sportsmenList), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
    }

    private JComponent createLogicButtonPanel() {
        JToolBar toolBar = componentFactory.createToolBar();
        editPersonBtn = componentFactory.createBtn("icons/df_On_Stage_Icon_Set/PNG/Edit.png", appMessages.getString("btn.edit-person"), createEditBtnActionListener());
        editPersonBtn.setEnabled(false);
        deletePersonBtn = componentFactory.createBtn("icons/df_On_Stage_Icon_Set/PNG/Symbol-Remove.png", appMessages.getString("btn.delete-person"), createDeleteBtnActionListener());
        deletePersonBtn.setEnabled(false);

        toolBar.add(componentFactory.createBtn("icons/df_On_Stage_Icon_Set/PNG/Symbol-Add.png", appMessages.getString("btn.add-person"), createAddPersonBtnListener()));
        toolBar.add(editPersonBtn);
        toolBar.add(deletePersonBtn);
        return toolBar;
    }

    private ActionListener createAddPersonBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsmanDialog.open(SportsmanDialog.Mode.ADD, new Callback<List<ISportsman>>() {
                    @Override
                    public void execute(List<ISportsman> refreshedSportsmenList) {
                        showSportsmenList(refreshedSportsmenList);
                    }
                });
            }
        };
    }

    private ActionListener createEditBtnActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sportsmanDialog.open(SportsmanDialog.Mode.EDIT, getSelectedSportsman(), new Callback<List<ISportsman>>() {
                    @Override
                    public void execute(List<ISportsman> refreshedSportsmenList) {
                        showSportsmenList(refreshedSportsmenList);
                    }
                });
            }
        };
    }

    private ActionListener createDeleteBtnActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().delete(getSelectedSportsman(), new Callback<List<ISportsman>>() {
                    @Override
                    public void execute(List<ISportsman> refreshedSportsmenList) {
                        showSportsmenList(refreshedSportsmenList);
                    }
                });
            }
        };
    }

    private void showSportsmenList(final List<ISportsman> sportsmen) {
        DefaultListModel model = (DefaultListModel) sportsmenList.getModel();
        model.clear();
        for (ISportsman sportsman : sportsmen) {
            model.addElement(sportsman);
        }
    }

    private JComponent createButtonPanel() {
        JPanel btnPanel = new JPanel();

        btnPanel.add(componentFactory.createBtn(appMessages.getString("btn.close"), createCloseBtnListener()));
        return btnPanel;
    }

    private ActionListener createCloseBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCloseClick();
            }
        };
    }

    private void onCloseClick() {
        setVisible(false);
    }

    public ISportsman getSelectedSportsman() {
        return (ISportsman) sportsmenList.getSelectedValue();
    }

    public RegisteredSportsmanDialogController getController() {
        return controller;
    }

    public void open() {
        showSportsmenList(controller.getSportsmen());
        setVisible(true);
    }
}
